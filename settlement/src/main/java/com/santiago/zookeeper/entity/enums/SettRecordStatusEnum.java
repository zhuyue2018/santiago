/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.santiago.zookeeper.entity.enums;

/**
 * 结算记录，状态
 * 龙果学院：www.roncoo.com
 * @author：zenghao
 */
public enum SettRecordStatusEnum {

	/**
	 * 等待确认
	 */
	WAIT_CONFIRM("0", "等待审核"),

	/**
	 * 已审核
	 */
	CONFIRMED("1", "已审核"),

	/**
	 * 审核不通过
	 */
	CANCEL("2", "审核不通过"),

	/**
	 * 打款中
	 */
	REMITTING("3", "打款中"),

	/**
	 * 打款成功
	 */
	REMIT_SUCCESS("4", "打款成功"),

	/**
	 * 打款失败
	 */
	REMIT_FAIL("5", "打款失败");



	/** 描述 */
	private String code;
	private String desc;

	SettRecordStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}}
