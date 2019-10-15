package com.santiago.commons.dto.resp;

/**
 * @author sean
 * @Description: ${TODO}
 * @date 18-10-11 下午5:23
 */
public abstract class BaseResponse {
	public BaseResponse(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	protected String code;

	protected String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
