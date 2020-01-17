package com.santiago.gateway.controller;

import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.dto.resp.UnionResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class GlobalControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);
//  /**
//   * 请求参数错误
//   */
//  private final static String BASE_PARAM_ERR_CODE = "BASE-PARAM-01";
//  private final static String BASE_PARAM_ERR_MSG = "参数校验不通过";
//  /**
//   * 无效的请求
//   */
//  private final static String BASE_BAD_REQUEST_ERR_CODE = "BASE-PARAM-02";
//  private final static String BASE_BAD_REQUEST_ERR_MSG = "无效的请求";

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BizException.class})
    public UnionResp handleException(BizException e) {
        logger.error("[handleException] ", e);
        return new UnionResp(e.getErrCode(), e.getErrMsg());
    }

    @ControllerAdvice(basePackages = "mvc")
    public class SpringControllerAdvice {
        @InitBinder
        public void globalInitBinder(WebDataBinder binder) {
            binder.addCustomFormatter(new DateFormatter("yyyyMMddHHmmss"));
        }
    }

//
//  /**
//   * 自定义的异常处理
//   *
//   * @param ex
//   * @return
//   */
//  @ResponseStatus(HttpStatus.OK)
//  @ExceptionHandler({BizException.class})
//  public BaseResult serviceExceptionHandler(BizServiceException ex) {
//    String errorCode = ex.getErrCode();
//    String msg = ex.getErrMsg() == null ? "" : ex.getErrMsg();
//    String innerErrMsg;
//    String outerErrMsg;
//    if (BASE_PARAM_ERR_CODE.equalsIgnoreCase(errorCode)) {
//      innerErrMsg = "参数校验不通过：" + msg;
//      outerErrMsg = BASE_PARAM_ERR_MSG;
//    } else if (ex.isInnerError()) {
//      innerErrMsg = ErrorCache.getInternalMsg(errorCode);
//      outerErrMsg = ErrorCache.getMsg(errorCode);
//      if (StringUtils.isNotBlank(msg)) {
//        innerErrMsg = innerErrMsg + "，" + msg;
//        outerErrMsg = outerErrMsg + "，" + msg;
//      }
//    } else {
//      innerErrMsg = msg;
//      outerErrMsg = msg;
//    }
//    log.info("【错误码】：{}，【错误码内部描述】：{}，【错误码外部描述】：{}", errorCode, innerErrMsg, outerErrMsg);
//    return ResultUtil.failure(errorCode, outerErrMsg);
//  }
//
//  /**
//   * 缺少servlet请求参数抛出的异常
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler({MissingServletRequestParameterException.class})
//  public BaseResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
//    log.warn("[handleMissingServletRequestParameterException] 参数错误: " + e.getParameterName());
//    return ResultUtil.failure(BASE_PARAM_ERR_CODE, BASE_PARAM_ERR_MSG);
//  }
//
//  /**
//   * 请求参数不能正确读取解析时，抛出的异常，比如传入和接受的参数类型不一致
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.OK)
//  @ExceptionHandler({HttpMessageNotReadableException.class})
//  public BaseResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//    log.warn("[handleHttpMessageNotReadableException] 参数解析失败：", e);
//    return ResultUtil.failure(BASE_PARAM_ERR_CODE, BASE_PARAM_ERR_MSG);
//  }
//
//  /**
//   * 请求参数无效抛出的异常
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler({MethodArgumentNotValidException.class})
//  public BaseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//    BindingResult result = e.getBindingResult();
//    String message = getBindResultMessage(result);
//    log.warn("[handleMethodArgumentNotValidException] 参数验证失败：" + message);
//    return ResultUtil.failure(BASE_PARAM_ERR_CODE, BASE_PARAM_ERR_MSG);
//  }
//
//  private String getBindResultMessage(BindingResult result) {
//    FieldError error = result.getFieldError();
//    String field = error != null ? error.getField() : "空";
//    String code = error != null ? error.getDefaultMessage() : "空";
//    return String.format("%s:%s", field, code);
//  }
//
//  /**
//   * 方法请求参数类型不匹配异常
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
//  public BaseResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
//    log.warn("[handleMethodArgumentTypeMismatchException] 方法参数类型不匹配异常: ", e);
//    return ResultUtil.failure(BASE_PARAM_ERR_CODE, BASE_PARAM_ERR_MSG);
//  }
//
//  /**
//   * 请求参数绑定到controller请求参数时的异常
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler({BindException.class})
//  public BaseResult handleHttpMessageNotReadableException(BindException e) {
//    BindingResult result = e.getBindingResult();
//    String message = getBindResultMessage(result);
//    log.warn("[handleHttpMessageNotReadableException] 参数绑定失败：" + message);
//    return ResultUtil.failure(BASE_PARAM_ERR_CODE, BASE_PARAM_ERR_MSG);
//  }
//
//  /**
//   * javax.validation:validation-api 校验参数抛出的异常
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler({ConstraintViolationException.class})
//  public BaseResult handleServiceException(ConstraintViolationException e) {
//    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//    ConstraintViolation<?> violation = violations.iterator().next();
//    String message = violation.getMessage();
//    log.warn("[handleServiceException] 参数验证失败：" + message);
//    return ResultUtil.failure(BASE_PARAM_ERR_CODE, BASE_PARAM_ERR_MSG);
//  }
//
//  /**
//   * javax.validation 下校验参数时抛出的异常
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler({ValidationException.class})
//  public BaseResult handleValidationException(ValidationException e) {
//    log.warn("[handleValidationException] 参数验证失败：", e);
//    return ResultUtil.failure(BASE_PARAM_ERR_CODE, BASE_PARAM_ERR_MSG);
//  }
//
//  /**
//   * 不支持该请求方法时抛出的异常
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//  public BaseResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//    log.warn("[handleHttpRequestMethodNotSupportedException] 不支持当前请求方法: ", e);
//    return ResultUtil.failure(BASE_BAD_REQUEST_ERR_CODE, BASE_BAD_REQUEST_ERR_MSG);
//  }
//
//  /**
//   * 不支持当前媒体类型抛出的异常
//   *
//   * @param e
//   * @return
//   */
//  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//  @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
//  public BaseResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
//    log.warn("[handleHttpMediaTypeNotSupportedException] 不支持当前媒体类型: ", e);
//    return ResultUtil.failure(BASE_BAD_REQUEST_ERR_CODE, BASE_BAD_REQUEST_ERR_MSG);
//  }

}

