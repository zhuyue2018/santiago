package com.santiago.commons.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static final String ALGORITHM = "AES/ecb/PKCS5Padding";

    public AES() {
    }

    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ecb/PKCS5Padding");
        cipher.init(1, keySpec);
        return cipher.doFinal(src);
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ecb/PKCS5Padding");
        cipher.init(2, keySpec);
        return cipher.doFinal(src);
    }
}
