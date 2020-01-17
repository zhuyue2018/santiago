package com.santiago.commons.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

public class SecurityUtil {
    private static final String SHA_256 = "SHA-256";
    private static final String MD5 = "MD5";
    private static final int KEY_LENGTH = 16;
    public static final String DEFAULT_AES_KEY = "%01#g34h&B7c89i^";

    public SecurityUtil() {
    }

    public static String getSalt(int size) {
        byte[] salt = new byte[size];

        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
            return Base64.encodeBase64String(salt);
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static void main(String[] args) {
        String key = "0123456789abcdef";
        String abc = aesEncrypt("abcdefghijklmnopq", "%01#g34h&B7c89i^");
        System.out.println(abc);
        String aaaa = aesDecrypt("1D25821C3E311EEA2D4DD8633A25C1B5", key);
        System.out.println(aaaa);
    }

    public static String encryptPassword(String password, String key) {
        if (password != null && key != null) {
            if (key.getBytes().length != 16) {
                throw new IllegalArgumentException("Key must be 16 bytes long!");
            } else {
                try {
                    return Base64.encodeBase64String(AES.encrypt(password.getBytes(), key.getBytes()));
                } catch (Exception var3) {
                    throw new RuntimeException(var3);
                }
            }
        } else {
            throw new IllegalArgumentException("password or key can't be null!");
        }
    }

    public static String decryptPassword(String password, String key) {
        if (password != null && key != null) {
            if (key.getBytes().length != 16) {
                throw new IllegalArgumentException("Key must be 16 bytes long!");
            } else {
                try {
                    return new String(AES.decrypt(Base64.decodeBase64(password), key.getBytes()));
                } catch (Exception var3) {
                    throw new RuntimeException(var3);
                }
            }
        } else {
            throw new IllegalArgumentException("password or key can't be null!");
        }
    }

    public static String aesEncrypt(String password, String key) {
        if (password != null && key != null) {
            if (key.getBytes().length != 16) {
                throw new IllegalArgumentException("Key must be 16 bytes long!");
            } else {
                try {
                    return parseByte2HexStr(AES.encrypt(password.getBytes(), key.getBytes()));
                } catch (Exception var3) {
                    throw new RuntimeException(var3);
                }
            }
        } else {
            throw new IllegalArgumentException("password or key can't be null!");
        }
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static String aesDecrypt(String password, String key) {
        if (password != null && key != null) {
            if (key.getBytes().length != 16) {
                throw new IllegalArgumentException("Key must be 16 bytes long!");
            } else {
                try {
                    return new String(AES.decrypt(parseHexStr2Byte(password), key.getBytes()));
                } catch (Exception var3) {
                    throw new RuntimeException(var3);
                }
            }
        } else {
            throw new IllegalArgumentException("password or key can't be null!");
        }
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for(int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }

    public static String doSHA256(String str) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = str.getBytes();

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            strDes = bytes2Hex(md.digest());
            return strDes;
        } catch (NoSuchAlgorithmException var5) {
            return null;
        }
    }

    public static String doSHA256(String str, String charset) {
        MessageDigest md = null;
        String strDes = null;

        try {
            byte[] bt = str.getBytes(charset);
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            strDes = bytes2Hex(md.digest());
            return strDes;
        } catch (Exception var5) {
            return null;
        }
    }

    public static String doMd5(String str, String charset) {
        MessageDigest messageDigest = null;
        String strDes = null;

        try {
            byte[] bt = str.getBytes(charset);
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bt);
            strDes = bytes2Hex(messageDigest.digest());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;

        for(int i = 0; i < bts.length; ++i) {
            tmp = Integer.toHexString(bts[i] & 255);
            if (tmp.length() == 1) {
                des = des + "0";
            }

            des = des + tmp;
        }

        return des;
    }
}
