package com.santiago.commons.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;

import com.santiago.commons.domain.RsaKeyPair;
import com.santiago.commons.domain.SM2;
import com.santiago.commons.domain.SM2Cipher;
import com.santiago.commons.domain.SM2Result;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Base64;

public class SM2Utils {
    private static final byte[] userId = "sm2Utils-userId".getBytes();

    public SM2Utils() {
    }

    public static RsaKeyPair generateKeyPair(String keyId) {
        SM2 sm2 = SM2.Instance();
        AsymmetricCipherKeyPair key = sm2.ecc_key_pair_generator.generateKeyPair();
        ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters)key.getPrivate();
        ECPublicKeyParameters ecpub = (ECPublicKeyParameters)key.getPublic();
        BigInteger privateKey = ecpriv.getD();
        ECPoint publicKey = ecpub.getQ();
        String publicKeyStr = SMUtil.byteToHex(publicKey.getEncoded(true));
        String privateKeyStr = SMUtil.byteToHex(privateKey.toByteArray());
        RsaKeyPair keyPair = new RsaKeyPair(keyId, privateKeyStr, publicKeyStr);
        return keyPair;
    }

    public static String encrypt(String pubk, String sourceStr) throws IOException {
        if (pubk != null && pubk.length() != 0) {
            byte[] publicKey = SMUtil.hexToByte(pubk);
            if (sourceStr != null && sourceStr.length() != 0) {
                byte[] data = sourceStr.getBytes("UTF-8");
                byte[] source = new byte[data.length];
                System.arraycopy(data, 0, source, 0, data.length);
                SM2 sm2 = SM2.Instance();
                ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);
                SM2Cipher cipher = new SM2Cipher();
                ECPoint c1 = cipher.initEnc(sm2, userKey);
                cipher.encrypt(source);
                byte[] c3 = new byte[32];
                cipher.doFinal(c3);
                String retData = SMUtil.byteToHex(c1.getEncoded(true)) + SMUtil.byteToHex(source) + SMUtil.byteToHex(c3);
                return Base64Util.gzipStr(retData);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String decrypt(String prik, String cipherText) throws IOException {
        if (prik != null && prik.length() != 0) {
            byte[] privateKey = SMUtil.hexToByte(prik);
            if (cipherText != null && cipherText.length() != 0) {
                byte[] encryptedData = SMUtil.hexToByte(Base64Util.ungzipStr(cipherText));
                String data = SMUtil.byteToHex(encryptedData);
                byte[] c1Bytes = SMUtil.hexToByte(data.substring(0, 130));
                int c2Len = encryptedData.length - 97;
                byte[] c2 = SMUtil.hexToByte(data.substring(130, 130 + 2 * c2Len));
                byte[] c3 = SMUtil.hexToByte(data.substring(130 + 2 * c2Len, 194 + 2 * c2Len));
                SM2 sm2 = SM2.Instance();
                BigInteger userD = new BigInteger(1, privateKey);
                ECPoint c1 = sm2.ecc_curve.decodePoint(c1Bytes);
                SM2Cipher cipher = new SM2Cipher();
                cipher.initDec(userD, c1);
                cipher.decrypt(c2);
                cipher.doFinal(c3);
                return new String(c2, "UTF-8");
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static void test(boolean isPrint) throws Exception {
        generateKeyPair("TEST");
        String plainText = "ererfeiisgod";
        String prik = "12A0EDE66E8DC7589E35CED51A1327C2FCA78A6B363916C5AB2D5E243FC265CA";
        String pubk = "047F397CC9D423DA9196A1AEFC02B6C177036B2B23973F256EF3F70C901F5EAE7077E7746D547A9B6496C4EC216F0C20029FB53CA1E947C5CB4781A243762C266F";
        String cipherText = encrypt(pubk, plainText);
        String plainTextNew = decrypt(prik, cipherText);
        if (isPrint) {
            System.out.println("原始：" + plainText);
            System.out.println("加密: ");
            System.out.println(cipherText);
            System.out.println("解密: ");
            System.out.println(plainTextNew);
        }

    }

    public static String sign(String prik, String plainText) throws IOException {
        if (prik != null && prik.length() != 0) {
            byte[] privateKey = Base64.decode(Base64.encode(SMUtil.hexToByte(prik)));
            if (plainText != null && plainText.length() != 0) {
                byte[] sourceData = plainText.getBytes();
                SM2 sm2 = SM2.Instance();
                BigInteger userD = new BigInteger(privateKey);
                ECPoint userKey = sm2.ecc_point_g.multiply(userD);
                SM3Digest sm3 = new SM3Digest();
                byte[] z = sm2.sm2GetZ(userId, userKey);
                sm3.update(z, 0, z.length);
                sm3.update(sourceData, 0, sourceData.length);
                byte[] md = new byte[32];
                sm3.doFinal(md, 0);
                SM2Result sm2Result = new SM2Result();
                sm2.sm2Sign(md, userD, userKey, sm2Result);
                ASN1Integer d_r = new ASN1Integer(sm2Result.r);
                ASN1Integer d_s = new ASN1Integer(sm2Result.s);
                ASN1EncodableVector v2 = new ASN1EncodableVector();
                v2.add(d_r);
                v2.add(d_s);
                byte[] signdata = (new DERSequence(v2)).getEncoded();
                return new String(Base64.encode(signdata));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static boolean verifySign(String pubk, String sourceStr, String signed) throws IOException {
        if (pubk != null && pubk.length() != 0) {
            byte[] signData = Base64.decode(signed);
            byte[] sourceData = sourceStr.getBytes();
            byte[] publicKey = SMUtil.hexToByte(pubk);
            if (sourceData != null && sourceData.length != 0) {
                SM2 sm2 = SM2.Instance();
                ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);
                SM3Digest sm3 = new SM3Digest();
                byte[] z = sm2.sm2GetZ(userId, userKey);
                sm3.update(z, 0, z.length);
                sm3.update(sourceData, 0, sourceData.length);
                byte[] md = new byte[32];
                sm3.doFinal(md, 0);
                ByteArrayInputStream bis = new ByteArrayInputStream(signData);
                ASN1InputStream dis = new ASN1InputStream(bis);
                Enumeration<ASN1Integer> e = ((ASN1Sequence)dis.readObject()).getObjects();
                dis.close();
                BigInteger r = ((ASN1Integer)e.nextElement()).getValue();
                BigInteger s = ((ASN1Integer)e.nextElement()).getValue();
                SM2Result sm2Result = new SM2Result();
                sm2Result.r = r;
                sm2Result.s = s;
                sm2.sm2Verify(md, userKey, sm2Result.r, sm2Result.s, sm2Result);
                return sm2Result.r.equals(sm2Result.R);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        String plainText = "message digest中文";
        String prik = "128B2FA8BD433C6C068C8D803DFF79792A519A55171B1B650C23661D15897263";
        String pubk = "040AE4C7798AA0F119471BEE11825BE46202BB79E2A5844495E97C04FF4DF2548A7C0240F88F1CD4E16352A73C17B7F16F07353E53A176D684A9FE0C6BB798E857";
        String signed = sign(prik, plainText);
        System.out.println("签名: " + signed);
        System.out.println("");
        boolean vs = verifySign(pubk, plainText, signed);
        System.out.println("验签结果: " + vs);
        System.out.println("");
        System.out.println("加密解密: ");
        String cipherText = encrypt(pubk, plainText);
        System.out.println("原始:" + plainText);
        System.out.println("加密:" + cipherText);
        String plainTextNew = decrypt(prik, cipherText);
        System.out.println("解密:" + plainTextNew);
    }
}
