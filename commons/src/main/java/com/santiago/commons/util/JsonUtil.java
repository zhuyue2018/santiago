package com.santiago.commons.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
	private JsonUtil() {
	}
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private final static ObjectMapper propertiesNullableMapper;
	private final static ObjectMapper propertiesNotNullMapper;

	static {
		propertiesNullableMapper = new ObjectMapper();
		propertiesNotNullMapper = new ObjectMapper();
		propertiesNotNullMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	}

	public static <T> T parseJson(String jsonStr, Class<T> prototype) {
		try {
			return (T)JsonUtil.propertiesNotNullMapper.readValue(jsonStr, prototype);
		} catch (IOException e) {
			String msg = String.format("解析jsonStr异常，未能正确解析的jsonStr为：%s", jsonStr);
			throw new JsonHandleException(msg);
		}
	}


	/**
	 * json转列表。
	 * demo：JsonUtil.parseJson2List(listStr,  new TypeReference<List<TestSerialize>>() {});
	 * @param jsonStr
	 * @param jsonTypeReference
	 * @param <T>
	 * @return
	 */
	public static <T> List parseJson2List(String jsonStr, TypeReference<T> jsonTypeReference) {
		try {
			return propertiesNotNullMapper.readValue(jsonStr, jsonTypeReference);
		} catch (IOException e) {
			String msg = String.format("解析jsonStr异常，未能正确解析的jsonStr为：%s", jsonStr);
			logger.warn(msg, e);
			throw new JsonHandleException(msg);
		}
	}


	/**
	 * 对象转字符串，属性值为null的参与序列化
	 * @param obj
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String obj2JsonStrWithNull(Object obj) {
		try {
			return JsonUtil.propertiesNullableMapper.writeValueAsString(obj);
		} catch (IOException e) {
			String msg = "json序列化异常";
			logger.warn(msg, e);
			throw new JsonHandleException(msg);
		}
	}

	/**
	 * 对象转字符串，属性值为null的不参与序列化
	 * @param obj
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String obj2JsonStrExcludeNull(Object obj) {
		String json = null;
		try {
			json = JsonUtil.propertiesNotNullMapper.writeValueAsString(obj);
		} catch (IOException e) {
			String msg = "json序列化异常";
			logger.warn(msg, e);
			throw new JsonHandleException(msg);
		}
		return json;
	}

	public static void main(String[] args) throws IOException {
		TestSerialize object = new TestSerialize();
		object.setId("123456");
		object.setName("name");
		TestSerialize object2 = new TestSerialize();
		object2.setId("1234567");
		object2.setName("name2");
		ArrayList<TestSerialize> list = new ArrayList<TestSerialize>();
		list.add(object);
		list.add(object2);
		String listStr = JsonUtil.obj2JsonStrExcludeNull(list);
		List json2List = JsonUtil.parseJson2List(listStr,  new TypeReference<List<TestSerialize>>() {});
		String strWithNull = JsonUtil.obj2JsonStrWithNull(object);
		String strExcludeNull = JsonUtil.obj2JsonStrExcludeNull(object);
		TestSerialize testSerialize1 = JsonUtil.parseJson(strWithNull, TestSerialize.class);
		TestSerialize testSerialize2 = JsonUtil.parseJson(strExcludeNull, TestSerialize.class);
		String strExcludeNullWithErr = strExcludeNull.substring(0,5);
		TestSerialize testSerialize = JsonUtil.parseJson(strExcludeNullWithErr, TestSerialize.class);
		System.out.println("over");
	}

	static class TestSerialize {
		String id;
		String name;
		String value;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

}