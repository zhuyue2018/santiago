package com.santiago.core.entity.exception;

import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.enums.RespCodeEnum;

public class TradeBizException extends BizException {
    public TradeBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
    public static final TradeBizException IP_ERROR = new TradeBizException(RespCodeEnum.IP_ERROR.getCode(), RespCodeEnum.IP_ERROR.getMsg());
    public static final TradeBizException SIGN_ERROR = new TradeBizException(RespCodeEnum.SIGN_ERROR.getCode(), RespCodeEnum.SIGN_ERROR.getMsg());
    public static final TradeBizException PARAMS_ERROR = new TradeBizException(RespCodeEnum.PARAMS_ERROR.getCode(), RespCodeEnum.PARAMS_ERROR.getMsg());
    public static final TradeBizException SYSTEM_ERROR = new TradeBizException(RespCodeEnum.SYSTEM_ERROR.getCode(), RespCodeEnum.SYSTEM_ERROR.getMsg());
    public static final TradeBizException DUPLICATED_BIZ_NO = new TradeBizException(RespCodeEnum.DUPLICATED_BIZ_NO.getCode(), RespCodeEnum.DUPLICATED_BIZ_NO.getMsg());


}
