package com.santiago.portal.controller;

import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.dto.resp.BaseResponse;
import com.santiago.commons.dto.resp.SimpleResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.zhuyue.pay0929.portal.controller")
public class SpringControllerAdvice {
  @ExceptionHandler(BizException.class)
  public BaseResponse bizException(BizException e) {
    return new SimpleResponse(e.getErrCode(), e.getErrMsg());
  }
}