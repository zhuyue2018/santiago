package com.santiago.commons.dto.exception;

public class TradeBizException extends BizException {
    public TradeBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
