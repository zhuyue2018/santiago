package com.santiago.commons.dto.exception;

public class BizException extends BaseException {
    public BizException() {
        super();
    }

    public BizException(String errMsg) {
        super(errMsg);
    }
    public BizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}