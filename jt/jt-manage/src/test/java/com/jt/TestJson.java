package com.jt;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.service.ItemCatService;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJson {
	@Autowired
	private ItemCatService service;
	@Autowired
	private Jedis jedis;
	@Test
	public void getJSON() {
		String json = jedis.get("json");
		List<EasyUITree> list = ObjectMapperUtil.toObject(json, java.util.List.class);
		System.out.println(list);
	}

	@Test
	public void testJson() {

		List<EasyUITree> list = service.selectItemCatList(1l);
		String json = ObjectMapperUtil.toJSON(list);
		System.out.println(json);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testToObject() {
		List<EasyUITree> list = service.selectItemCatList(1l);
		String json = ObjectMapperUtil.toJSON(list);
		System.out.println(json);
		System.out.println("-----------------------------------");
		List<EasyUITree> treeList = ObjectMapperUtil.toObject(json,list.getClass());
		System.out.println(treeList);

	}

	@Test
	public void getJedis () {
		System.out.println(jedis.get("bb"));


	}
}
