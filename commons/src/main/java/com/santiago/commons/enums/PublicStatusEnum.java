package com.santiago.commons.enums;

/**
 * 公共状态枚举. 只有 (冻结) 与(激活) 两种状态
 * 
 * @company：广州领课网络科技有限公司（龙果学院 www.roncoo.com）.
 * @author along
 */
public enum PublicStatusEnum {
	INIT("0", "待激活"),
	ACTIVE("1", "激活"),
	UNACTIVE("2", "冻结"),
	CLOSED("3", "关闭");

	/** 描述 */
	private String code;
	private String msg;

	PublicStatusEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}