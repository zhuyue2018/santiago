package com.santiago.core.entity.exception;

import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.enums.RespCodeEnum;

public class ChannelReceiveBizException extends BizException {
    public ChannelReceiveBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
    public static final ChannelReceiveBizException PARAMS_ERROR = new ChannelReceiveBizException(RespCodeEnum.PARAMS_ERROR.getCode(), RespCodeEnum.PARAMS_ERROR.getMsg());
    public static final ChannelReceiveBizException TRADE_STATUS_ERROR = new ChannelReceiveBizException(RespCodeEnum.TRADE_STATUS_ERROR.getCode(), RespCodeEnum.TRADE_STATUS_ERROR.getMsg());

}
