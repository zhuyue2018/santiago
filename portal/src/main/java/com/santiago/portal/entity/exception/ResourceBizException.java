package com.santiago.portal.entity.exception;


import com.santiago.commons.dto.exception.BizException;

public class ResourceBizException extends BizException {
    public ResourceBizException(String errMsg) {
        super(errMsg);
    }

    public ResourceBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
