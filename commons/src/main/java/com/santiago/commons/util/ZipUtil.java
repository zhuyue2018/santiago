package com.santiago.commons.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.codec.binary.Base64;

public class ZipUtil {
    public ZipUtil() {
    }

    public static String zipBytesToString(byte[] input, boolean gzip) throws IOException {
        return new String(zipBytesToBytes(input, true, gzip));
    }

    public static byte[] unzipBytesFromString(String input, boolean gzip) throws IOException {
        return unzipBytesFromBytes(input.getBytes(), true, gzip);
    }

    public static byte[] zipBytesToBytes(byte[] input, boolean base64, boolean gzip) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStream os = bos;
        if (gzip) {
            os = new GZIPOutputStream(bos);
        }

        BufferedOutputStream bufos = new BufferedOutputStream((OutputStream)os);
        bufos.write(input);
        bufos.close();
        byte[] retval = bos.toByteArray();
        bos.close();
        return !base64 ? retval : Base64.encodeBase64(retval);
    }

    public static byte[] unzipBytesFromBytes(byte[] bytes, boolean base64, boolean gzip) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(!base64 ? bytes : Base64.decodeBase64(bytes));
        InputStream is = bis;
        if (gzip) {
            is = new GZIPInputStream(bis);
        }

        BufferedInputStream bufis = new BufferedInputStream((InputStream)is);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        int len;
        while((len = bufis.read(buf)) > 0) {
            bos.write(buf, 0, len);
        }

        bis.close();
        bufis.close();
        bos.close();
        return bos.toByteArray();
    }
}