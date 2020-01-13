package com.santiago.commons.domain;

public class RsaKeyPair {
    private String keyId;
    private String publicKey;
    private String privateKey;

    public RsaKeyPair() {
    }

    public RsaKeyPair(String keyId, String privateKey, String publicKey) {
        this.keyId = keyId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public String toString() {
        return "keyId=" + this.keyId + "\nprivateKey=" + this.privateKey + "\npublicKey=" + this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
