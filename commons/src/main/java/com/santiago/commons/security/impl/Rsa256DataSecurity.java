package com.santiago.commons.security.impl;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

import com.santiago.commons.util.EasiBase64Util;
import com.santiago.commons.domain.ZipAlgorithmFactory;
import com.santiago.commons.security.RsaDataSecurity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class Rsa256DataSecurity implements RsaDataSecurity {
    private static final Logger log = LoggerFactory.getLogger(Rsa256DataSecurity.class);
    public static final String ALGORITHM_NAME = "RSA2";
    private final ZipAlgorithmFactory zipAlgorithmFactory;

    public Rsa256DataSecurity(ZipAlgorithmFactory zipAlgorithmFactory) {
        this.zipAlgorithmFactory = zipAlgorithmFactory;
    }
    @Override
    public Map<String, String> createRsaKey(String keyId) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        Map<String, String> result = new HashMap(4);
        result.put("keyId", keyId);
        result.put("publicKey", EasiBase64Util.encode(publicKey.getEncoded()));
        result.put("privateKey", EasiBase64Util.encode(privateKey.getEncoded()));
        log.info("生成RSA256密钥对,keyId:{},publicKey:{},privateKey:{}", new Object[]{keyId, result.get("publicKey"), result.get("privateKey")});
        return result;
    }
    @Override
    public String encrypt(String data, String publicKey, boolean isZipInBeforeEncrypt, String zipAlgorithmName) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(data), "要加密的数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(publicKey), "加密公钥不能为空");
        String zipData;
        if (isZipInBeforeEncrypt) {
            Assert.isTrue(StringUtils.isNotBlank(zipAlgorithmName), "压缩算法名称不能为空");
            zipData = this.zipAlgorithmFactory.getInstance(zipAlgorithmName).zip(data, true);
        } else {
            zipData = data;
        }

        return this.encryptByPublicKey(zipData, publicKey);
    }
    @Override
    public String decrypt(String encryptData, String privateKey, boolean isUnZipInAfterDecrypt, String zipAlgorithmName) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(encryptData), "要解密的数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(privateKey), "解密私钥不能为空");
        String date = this.decryptByPrivateKey(encryptData, privateKey);
        if (isUnZipInAfterDecrypt) {
            Assert.isTrue(StringUtils.isNotBlank(zipAlgorithmName), "解压缩算法名称不能为空");
            return this.zipAlgorithmFactory.getInstance(zipAlgorithmName).unzip(date, true);
        } else {
            return date;
        }
    }

    @Override
    public String sign(String data, String privateKey) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(data), "签名数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(privateKey), "签名私钥不能为空");
        return this.rsaSign(data, privateKey);
    }

    @Override
    public boolean verifySign(String data, String sign, String publicKey) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(data), "验签数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(sign), "签名值不能为空");
        Assert.isTrue(StringUtils.isNotBlank(publicKey), "验签公钥不能为空");
        return this.rsaVerifySign(data, sign, publicKey);
    }

    @Override
    public String algorithmName() {
        return "RSA2";
    }

    protected String encryptByPublicKey(String sdata, String publicKey) throws Exception {
        byte[] data = sdata.getBytes("UTF-8");
        byte[] keyBytes = EasiBase64Util.decode(publicKey.getBytes("UTF-8"));
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 117) {
            byte[] cache;
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(data, offSet, 117);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] encryptedData = out.toByteArray();
        out.close();
        return EasiBase64Util.encode(encryptedData);
    }

    protected String decryptByPrivateKey(String sdata, String privateKey) throws Exception {
        byte[] encryptedData = EasiBase64Util.decode(sdata.getBytes("UTF-8"));
        byte[] keyBytes = EasiBase64Util.decode(privateKey.getBytes("UTF-8"));
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 128) {
            byte[] cache;
            if (inputLen - offSet > 128) {
                cache = cipher.doFinal(encryptedData, offSet, 128);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, "UTF-8");
    }

    protected String rsaSign(String data, String privateKey) throws Exception {
        PrivateKey priKey = this.getPrivateKey("RSA", privateKey);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(priKey);
        signature.update(data.getBytes("UTF-8"));
        byte[] signed = signature.sign();
        System.out.println("使用[RSA]的[SHA256withRSA]算法进行签名");
        return EasiBase64Util.encode(signed);
    }

    private PrivateKey getPrivateKey(String algorithm, String privateKey) throws Exception {
        if (algorithm == null) {
            return null;
        } else {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            byte[] encodedKey = privateKey.getBytes();
            encodedKey = EasiBase64Util.decode(encodedKey);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        }
    }

    protected boolean rsaVerifySign(String content, String signed, String publicKey) throws Exception {
        PublicKey pubKey = this.getPublicKey("RSA", publicKey);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(pubKey);
        signature.update(content.getBytes("UTF-8"));
        System.out.println("使用[RSA]的[SHA256withRSA]算法进行验签");
        return signature.verify(EasiBase64Util.decode(signed.getBytes()));
    }

    private PublicKey getPublicKey(String algorithm, String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] encodedKey = publicKey.getBytes();
        encodedKey = EasiBase64Util.decode(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }
}