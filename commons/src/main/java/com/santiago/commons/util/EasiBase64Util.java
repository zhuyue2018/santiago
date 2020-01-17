package com.santiago.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class EasiBase64Util {
    public EasiBase64Util() {
    }

    public static String encodeStr(String data, boolean isGzip) throws Exception {
        return isGzip ? Base64.encode(gzip(data.getBytes("UTF-8"))).replaceAll("\r|\n", "") : Base64.encode(data.getBytes("UTF-8")).replaceAll("\r|\n", "");
    }

    public static String decodeStr(String enData, boolean isGzip) throws Exception {
        if (enData == null) {
            return null;
        } else if (enData.startsWith("{")) {
            return enData;
        } else {
            String retData = null;
            if (isGzip) {
                retData = new String(ungzip(Base64.decode(enData)), "UTF-8");
            } else {
                retData = new String(Base64.decode(enData), "UTF-8");
            }

            if (retData != null) {
                retData = retData.trim();
            }

            return retData;
        }
    }

    private static byte[] gzip(byte[] data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.finish();
        gzip.close();
        byte[] ret = bos.toByteArray();
        bos.close();
        return ret;
    }

    private static byte[] ungzip(byte[] data) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        GZIPInputStream gzip = new GZIPInputStream(bis);
        byte[] buf = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int num;
        while((num = gzip.read(buf, 0, buf.length)) != -1) {
            bos.write(buf, 0, num);
        }

        gzip.close();
        bis.close();
        byte[] ret = bos.toByteArray();
        bos.flush();
        bos.close();
        return ret;
    }

    public static String encode(byte[] src) {
        return Base64.encode(src).replaceAll("\r|\n", "");
    }

    public static byte[] decode(byte[] encoded) throws Exception {
        return Base64.decode(encoded.toString());
    }
}