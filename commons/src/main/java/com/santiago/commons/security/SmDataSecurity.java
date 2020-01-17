package com.santiago.commons.security;

import java.util.Map;

public interface SmDataSecurity extends DataSecurity {
    Map<String, String> createRsaKey(String var1);
}