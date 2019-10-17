package com.santiago.core.entity.exception;


import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.enums.ErrorCodeEnum;

public class UserBizException extends BizException {
    public UserBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public static final UserBizException SECURITY_RATING_ERROR = new UserBizException(ErrorCodeEnum.SECURITY_RATING_ERROR.getCode(), ErrorCodeEnum.SECURITY_RATING_ERROR.getMsg());
    public static final UserBizException USER_PAY_CONFIG_ERROR = new UserBizException(ErrorCodeEnum.USER_PAY_CONFIG_ERROR.getCode(), ErrorCodeEnum.USER_PAY_CONFIG_ERROR.getMsg());
    public static final UserBizException USER_PAY_INFO_ERRPR = new UserBizException(ErrorCodeEnum.USER_PAY_INFO_ERROR.getCode(), ErrorCodeEnum.USER_PAY_INFO_ERROR.getMsg());


}
