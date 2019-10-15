package com.santiago.core.entity.exception;

import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.enums.ErrorCodeEnum;

public class TradeBizException extends BizException {
    public TradeBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
    public static final TradeBizException IP_ERROR = new TradeBizException(ErrorCodeEnum.IP_ERROR.getCode(), ErrorCodeEnum.IP_ERROR.getMsg());
    public static final TradeBizException SIGN_ERROR = new TradeBizException(ErrorCodeEnum.SIGN_ERROR.getCode(), ErrorCodeEnum.SIGN_ERROR.getMsg());
    public static final TradeBizException PARAMS_ERROR = new TradeBizException(ErrorCodeEnum.PARAMS_ERROR.getCode(), ErrorCodeEnum.PARAMS_ERROR.getMsg());
    public static final TradeBizException SYSTEM_ERROR = new TradeBizException(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    public static final TradeBizException DUPLICATED_BIZ_NO = new TradeBizException(ErrorCodeEnum.DUPLICATED_BIZ_NO.getCode(), ErrorCodeEnum.DUPLICATED_BIZ_NO.getMsg());


}
