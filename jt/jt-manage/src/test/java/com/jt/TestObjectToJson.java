package com.jt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

public class TestObjectToJson {
	private ObjectMapper mapper = new ObjectMapper();
	/**
	 * 对象转化为json时依赖对象的那些方法?
	 * 依据:每个对象中必须有set/get方法
	 * @throws IOException 
	 */
	@Test
	public void toJSON() throws IOException {
		ItemDesc desc = new ItemDesc();
		desc.setItemId(100l).setItemDesc("定义详情信息");
		//将对象转化为json ,调用对象的get方法
		String json = mapper.writeValueAsString(desc);
		System.out.println(json);
		//json转对象,调用的是对象的set方法
		ItemDesc itemDesc =mapper.readValue(json, ItemDesc.class);
		System.out.println(itemDesc);
	}
	/**
	 * list集合,转化为json [] 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testToList() throws IOException {
		List<ItemDesc> list = new ArrayList<>();
		ItemDesc desc1 = new ItemDesc();
		desc1.setItemId(100l).setItemDesc("定义详情信息");
		list.add(desc1);
		ItemDesc desc2 = new ItemDesc();
		desc2.setItemId(10010l).setItemDesc("定义详情信sakjfghsjdahfgsdfdsfmjhndasbfcsahnjfgvsxahjfgkhjgsadfhgj息");
		list.add(desc2);
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
		List<ItemDesc> descList = mapper.readValue(json, list.getClass());
		System.out.println(descList);
	}

}
