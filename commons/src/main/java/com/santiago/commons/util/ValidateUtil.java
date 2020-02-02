package com.santiago.commons.util;



import com.santiago.commons.dto.req.UnionInnerReq;
import com.santiago.commons.enums.HttpStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ValidateUtil {
    @Autowired
    private Validator _validator;
    private static Validator validator;

    public static <T> Map validateReq(UnionInnerReq req, BindingResult result, Class<T> protoType) {
        HashMap<Integer, String> errorMap = new HashMap<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                errorMap.put(HttpStatusEnum.BAD_REQUEST.code(), objectError.getDefaultMessage());
            });
        }
        if (StringUtils.isEmpty(req.getMsgContent())) {
            errorMap.put(HttpStatusEnum.BAD_REQUEST.code(), "msgContent不能为空!");
        } else {
            Set<ConstraintViolation<T>> constraintViolationSet = ValidateUtil.validate(req.getMsgContent(), protoType);
            if (constraintViolationSet.size() > 0) {
                constraintViolationSet.forEach(clearBookDTOConstraintViolation -> {
                    errorMap.put(HttpStatusEnum.BAD_REQUEST.code(), clearBookDTOConstraintViolation.getMessageTemplate());
                });
            }
        }
        return errorMap;
    }

    @PostConstruct
    public void init() {
        ValidateUtil.validator = this._validator;
    }

    public static <T> Set<ConstraintViolation<T>> validate(String content, Class<T> protoType) {
        T t = JsonUtil.parseJson(content, protoType);
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(t, protoType);
        return constraintViolationSet;
    }
}
