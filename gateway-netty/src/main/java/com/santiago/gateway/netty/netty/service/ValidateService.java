package com.santiago.gateway.netty.netty.service;

import com.santiago.commons.dto.req.UnionReq;
import com.santiago.gateway.netty.domain.ValidateResult;


public interface ValidateService {
    ValidateResult validate(UnionReq req, Class c);
}
