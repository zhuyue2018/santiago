package com.santiago.core.entity.exception;


import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.enums.ErrorCodeEnum;

public class UserBizException extends BizException {
    public UserBizException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public static final UserBizException USER_PAY_CONFIG_ERRPR = new UserBizException(ErrorCodeEnum.USER_PAY_CONFIG_ERRPR.getCode(), ErrorCodeEnum.USER_PAY_CONFIG_ERRPR.getMsg());
}
