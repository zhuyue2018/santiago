package com.santiago.commons.domain;

import java.util.Map;

public class ZipAlgorithmFactory {
    private final Map<String, ZipAlgorithm> zipAlgorithmMap;

    public ZipAlgorithmFactory(Map<String, ZipAlgorithm> zipAlgorithmMap) {
        this.zipAlgorithmMap = zipAlgorithmMap;
    }

    public ZipAlgorithm getInstance(String name) {
        return (ZipAlgorithm)this.zipAlgorithmMap.get(name);
    }

    public void register(String name, ZipAlgorithm zipAlgorithm) {
        this.zipAlgorithmMap.put(name, zipAlgorithm);
    }
}