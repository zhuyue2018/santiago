package com.santiago.gateway.netty.security;

import java.util.Map;

public interface RsaDataSecurity extends DataSecurity {
    Map<String, String> createRsaKey(String var1) throws Exception;
}