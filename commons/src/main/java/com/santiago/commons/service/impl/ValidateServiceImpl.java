package com.santiago.commons.service.impl;

import com.santiago.commons.domain.GZipAlgorithm;
import com.santiago.commons.domain.ValidateResult;
import com.santiago.commons.domain.ZipAlgorithm;
import com.santiago.commons.domain.ZipAlgorithmFactory;
import com.santiago.commons.dto.req.UnionReq;
import com.santiago.commons.security.DataSecurity;
import com.santiago.commons.security.impl.Sm2DataSecurity;
import com.santiago.commons.util.JsonUtil;
import com.santiago.commons.service.ValidateService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2020-01-14 20:47
 **/
@Service
public class ValidateServiceImpl implements ValidateService {
    private static final Logger logger = LoggerFactory.getLogger(ValidateServiceImpl.class);

    @Autowired
    Validator validator;

    @Override
    public ValidateResult validate(UnionReq req, Class c) {
        String msgContent = req.getMsgContent();
        Object o = JsonUtil.parseJson(msgContent, c);
        Set<ConstraintViolation<Object>> violationSet = validator.validate(o);
        ValidateResult result = new ValidateResult();
        HashMap<String, String> errorMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(violationSet)) {
            violationSet.stream().forEach(violation -> {
                errorMap.put(violation.getMessageTemplate(), "");
            });
            String signature = signature(msgContent, "key");
            if (!signature.equals(req.getSign())) {
                errorMap.put("签名错误", "");
            }
        }
        if (errorMap.size() > 0) {
            result.setCorrect(false);
            result.setErrorMap(errorMap);
            return result;
        }
        result.setCorrect(true);
        return result;
    }

    private String signature(String msgContent, String key) {
        Map<String, ZipAlgorithm> map = new HashMap<>(16);
        map.put(GZipAlgorithm.ALGORITHM_NAME, new GZipAlgorithm());
        ZipAlgorithmFactory zipAlgorithmFactory = new ZipAlgorithmFactory(map);
        DataSecurity dataSecurity = new Sm2DataSecurity(zipAlgorithmFactory);
        String sign = "";
        try {
            sign = dataSecurity.sign(msgContent, key);
        } catch (Exception e) {
            logger.error("构造签名错误");
        }
        return sign;
    }
}
