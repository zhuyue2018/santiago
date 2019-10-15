package com.santiago.portal.entity.exception;


import com.santiago.commons.dto.exception.BizException;

public class PmsBizException extends BizException {
    public PmsBizException(String errMsg) {
        super(errMsg);
    }

    public PmsBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
