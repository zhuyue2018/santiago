package com.santiago.commons.security.impl;

import java.util.HashMap;
import java.util.Map;

import com.santiago.commons.domain.RsaKeyPair;
import com.santiago.commons.util.SM2Utils;
import com.santiago.commons.domain.GZipAlgorithm;
import com.santiago.commons.domain.ZipAlgorithm;
import com.santiago.commons.domain.ZipAlgorithmFactory;
import com.santiago.commons.security.SmDataSecurity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class Sm2DataSecurity implements SmDataSecurity {
    private static final Logger log = LoggerFactory.getLogger(Sm2DataSecurity.class);
    public static final String ALGORITHM_NAME = "SM2";
    private final ZipAlgorithmFactory zipAlgorithmFactory;

    public Sm2DataSecurity(ZipAlgorithmFactory zipAlgorithmFactory) {
        this.zipAlgorithmFactory = zipAlgorithmFactory;
    }

    public static void main(String[] args) throws Exception {
        Map<String, ZipAlgorithm> map = new HashMap();
        map.put("GZIP", new GZipAlgorithm());
        ZipAlgorithmFactory factory = new ZipAlgorithmFactory(map);
        Sm2DataSecurity sm2DataSecurity = new Sm2DataSecurity(factory);
        Map<String, String> test123Sm2 = sm2DataSecurity.createRsaKey("Easipay-DF");
        Rsa256DataSecurity rsa256DataSecurity = new Rsa256DataSecurity(factory);
        Map<String, String> test123Rsa = rsa256DataSecurity.createRsaKey("Easipay-DF");
    }

    @Override
    public Map<String, String> createRsaKey(String keyId) {
        RsaKeyPair rsaKeyPair = SM2Utils.generateKeyPair(keyId);
        Map<String, String> result = new HashMap(4);
        result.put("keyId", rsaKeyPair.getKeyId());
        result.put("publicKey", rsaKeyPair.getPublicKey());
        result.put("privateKey", rsaKeyPair.getPrivateKey());
        log.info("生成SM2密钥对,keyId:{},publicKey:{},privateKey:{}", new Object[]{keyId, rsaKeyPair.getPublicKey(), rsaKeyPair.getPrivateKey()});
        return result;
    }

    @Override
    public String encrypt(String data, String publicKey, boolean isZipInBeforeEncrypt, String zipAlgorithmName) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(data), "要加密的数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(publicKey), "加密公钥不能为空");
        String zipData;
        if (isZipInBeforeEncrypt) {
            Assert.isTrue(StringUtils.isNotBlank(zipAlgorithmName), "压缩算法名称不能为空");
            zipData = this.zipAlgorithmFactory.getInstance(zipAlgorithmName).zip(data, true);
        } else {
            zipData = data;
        }

        return SM2Utils.encrypt(publicKey, zipData);
    }

    @Override
    public String decrypt(String encryptData, String privateKey, boolean isUnZipInAfterDecrypt, String zipAlgorithmName) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(encryptData), "要解密的数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(privateKey), "解密私钥不能为空");
        String date = SM2Utils.decrypt(privateKey, encryptData);
        if (isUnZipInAfterDecrypt) {
            Assert.isTrue(StringUtils.isNotBlank(zipAlgorithmName), "解压缩算法名称不能为空");
            return this.zipAlgorithmFactory.getInstance(zipAlgorithmName).unzip(date, true);
        } else {
            return date;
        }
    }

    @Override
    public String sign(String data, String privateKey) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(data), "签名数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(privateKey), "签名私钥不能为空");
        return SM2Utils.sign(privateKey, data);
    }
    @Override
    public boolean verifySign(String data, String sign, String publicKey) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(data), "验签数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(sign), "签名值不能为空");
        Assert.isTrue(StringUtils.isNotBlank(publicKey), "验签公钥不能为空");
        return SM2Utils.verifySign(publicKey, data, sign);
    }

    @Override
    public String algorithmName() {
        return "SM2";
    }
}