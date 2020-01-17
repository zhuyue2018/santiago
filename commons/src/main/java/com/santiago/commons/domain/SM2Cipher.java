package com.santiago.commons.domain;

import java.math.BigInteger;

import com.santiago.commons.util.SMUtil;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

public class SM2Cipher {
    private int ct = 1;
    private ECPoint p2;
    private SM3Digest sm3keybase;
    private SM3Digest sm3c3;
    private byte[] key = new byte[32];
    private byte keyOff = 0;

    public SM2Cipher() {
    }

    private void reset() {
        this.sm3keybase = new SM3Digest();
        this.sm3c3 = new SM3Digest();
        byte[] p = SMUtil.byteConvert32Bytes(this.p2.normalize().getXCoord().toBigInteger());
        this.sm3keybase.update(p, 0, p.length);
        this.sm3c3.update(p, 0, p.length);
        p = SMUtil.byteConvert32Bytes(this.p2.normalize().getYCoord().toBigInteger());
        this.sm3keybase.update(p, 0, p.length);
        this.ct = 1;
        this.nextKey();
    }

    private void nextKey() {
        SM3Digest sm3keycur = new SM3Digest(this.sm3keybase);
        sm3keycur.update((byte)(this.ct >> 24 & 255));
        sm3keycur.update((byte)(this.ct >> 16 & 255));
        sm3keycur.update((byte)(this.ct >> 8 & 255));
        sm3keycur.update((byte)(this.ct & 255));
        sm3keycur.doFinal(this.key, 0);
        this.keyOff = 0;
        ++this.ct;
    }

    public ECPoint initEnc(SM2 sm2, ECPoint userKey) {
        AsymmetricCipherKeyPair key = sm2.ecc_key_pair_generator.generateKeyPair();
        ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters)key.getPrivate();
        ECPublicKeyParameters ecpub = (ECPublicKeyParameters)key.getPublic();
        BigInteger k = ecpriv.getD();
        ECPoint c1 = ecpub.getQ();
        this.p2 = userKey.multiply(k);
        this.reset();
        return c1;
    }

    public void encrypt(byte[] data) {
        this.sm3c3.update(data, 0, data.length);

        for(int i = 0; i < data.length; ++i) {
            if (this.keyOff == this.key.length) {
                this.nextKey();
            }

            byte var10002 = data[i];
            byte[] var10003 = this.key;
            byte var10006 = this.keyOff;
            this.keyOff = (byte)(var10006 + 1);
            data[i] = (byte)(var10002 ^ var10003[var10006]);
        }

    }

    public void initDec(BigInteger userD, ECPoint c1) {
        this.p2 = c1.multiply(userD);
        this.reset();
    }

    public void decrypt(byte[] data) {
        for(int i = 0; i < data.length; ++i) {
            if (this.keyOff == this.key.length) {
                this.nextKey();
            }

            byte var10002 = data[i];
            byte[] var10003 = this.key;
            byte var10006 = this.keyOff;
            this.keyOff = (byte)(var10006 + 1);
            data[i] = (byte)(var10002 ^ var10003[var10006]);
        }

        this.sm3c3.update(data, 0, data.length);
    }

    public void doFinal(byte[] c3) {
        byte[] p = SMUtil.byteConvert32Bytes(this.p2.normalize().getYCoord().toBigInteger());
        this.sm3c3.update(p, 0, p.length);
        this.sm3c3.doFinal(c3, 0);
        this.reset();
    }
}
