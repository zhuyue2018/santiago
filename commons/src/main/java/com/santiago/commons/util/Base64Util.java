package com.santiago.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    public Base64Util() {
    }

    public static byte[] encode(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }

    public static byte[] encode(String str) {
        try {
            return encode(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var2) {
            return encode(str.getBytes());
        }
    }

    public static String encodeStr(String str) {
        try {
            return new String(encode(str), "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return new String(encode(str));
        }
    }

    public static String encodeStr(byte[] bytes) {
        try {
            return new String(encode(bytes), "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return new String(encode(bytes));
        }
    }

    public static String gzipStr(String str) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes("UTF-8");
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(bytes);
            gzip.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return encodeStr(out.toByteArray());
    }

    public static String gzipStr(byte[] bytes) throws UnsupportedEncodingException {
        try {
            return gzipStr(new String(bytes, "UTF-8"));
        } catch (UnsupportedEncodingException var2) {
            return gzipStr(new String(bytes));
        }
    }

    public static byte[] decode(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

    public static byte[] decode(String str) {
        try {
            return decode(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var2) {
            return decode(str.getBytes());
        }
    }

    public static String decodeStr(String str) {
        try {
            return new String(decode(str), "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return new String(decode(str));
        }
    }

    public static String decodeStr(byte[] bytes) {
        try {
            return new String(decode(bytes), "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return new String(decode(bytes));
        }
    }

    public static String ungzipStr(String str) throws UnsupportedEncodingException {
        byte[] bytes = decode(str);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];

            int n;
            while((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return new String(out.toByteArray(), "UTF-8");
    }

    public static String ungzipStr(byte[] bytes) throws UnsupportedEncodingException {
        try {
            return ungzipStr(new String(bytes, "UTF-8"));
        } catch (UnsupportedEncodingException var2) {
            return ungzipStr(new String(bytes));
        }
    }
}
