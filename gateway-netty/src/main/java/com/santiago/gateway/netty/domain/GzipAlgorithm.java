package com.santiago.gateway.netty.domain;

import java.io.IOException;

import com.santiago.commons.util.ZipUtil;

public class GzipAlgorithm implements ZipAlgorithm {
    public static final String ALGORITHM_NAME = "GZIP";

    public GzipAlgorithm() {
    }
    @Override
    public String zip(String date, boolean isBase64) throws IOException {
        return new String(ZipUtil.zipBytesToBytes(date.getBytes("UTF-8"), isBase64, true), "UTF-8");
    }

    @Override
    public String unzip(String zipDate, boolean isBase64) throws IOException {
        return new String(ZipUtil.unzipBytesFromBytes(zipDate.getBytes("UTF-8"), isBase64, true), "UTF-8");
    }

    @Override
    public String algorithmName() {
        return "GZIP";
    }
}