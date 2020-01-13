package com.santiago.commons.security;

public interface DataSecurity {
    String encrypt(String var1, String var2, boolean var3, String var4) throws Exception;

    String decrypt(String var1, String var2, boolean var3, String var4) throws Exception;

    String sign(String var1, String var2) throws Exception;

    boolean verifySign(String var1, String var2, String var3) throws Exception;

    String algorithmName();
}