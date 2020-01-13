package com.santiago.gateway.netty.domain;

import java.io.IOException;

public interface ZipAlgorithm {
    String zip(String var1, boolean var2) throws IOException;

    String unzip(String var1, boolean var2) throws IOException;

    String algorithmName();
}