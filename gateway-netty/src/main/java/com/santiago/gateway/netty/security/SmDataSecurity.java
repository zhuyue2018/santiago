package com.santiago.gateway.netty.security;

import java.util.Map;

public interface SmDataSecurity extends DataSecurity {
    Map<String, String> createRsaKey(String var1);
}