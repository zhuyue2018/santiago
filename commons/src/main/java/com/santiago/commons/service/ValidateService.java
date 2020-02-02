package com.santiago.commons.service;

import com.santiago.commons.domain.ValidateResult;
import com.santiago.commons.dto.req.UnionReq;


public interface ValidateService {
    ValidateResult validate(UnionReq req, Class c);
}
