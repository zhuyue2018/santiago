package com.santiago.core.entity.exception;

import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.enums.ErrorCodeEnum;

public class ChannelReceiveBizException extends BizException {
    public ChannelReceiveBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
    public static final ChannelReceiveBizException PARAMS_ERROR = new ChannelReceiveBizException(ErrorCodeEnum.PARAMS_ERROR.getCode(), ErrorCodeEnum.PARAMS_ERROR.getMsg());
    public static final ChannelReceiveBizException TRADE_STATUS_ERROR = new ChannelReceiveBizException(ErrorCodeEnum.TRADE_STATUS_ERROR.getCode(), ErrorCodeEnum.TRADE_STATUS_ERROR.getMsg());

}
