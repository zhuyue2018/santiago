package com.santiago.gateway.netty.netty.service;

import com.santiago.commons.dto.req.UnionReq;
import com.santiago.gateway.netty.domain.SttDetBookDTO;
import com.santiago.gateway.netty.domain.ValidateResult;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidateService {
    ValidateResult validate(UnionReq req, Class c);
}
