package com.jt.util;
/**
 * 实现对象与json串之间的转化
 * @author BoYuan
 *
 */

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * 将对象转化为json串
	 * @param data
	 * @return
	 */
	public static String toJSON(Object data) {
		String json = null;
		try {
			json = MAPPER.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return json;
	}
	/**
	 * 将json串转化为对象
	 * @param <T>
	 * @param json
	 * @param t
	 * @return
	 */
	public static <T>T toObject(String json , Class<T>  t) {
		T result = null ;
		try {
			result = MAPPER.readValue(json,t);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	
}
