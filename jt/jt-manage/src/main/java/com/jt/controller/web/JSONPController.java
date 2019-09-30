package com.jt.controller.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;

@RestController
@RequestMapping("/web/")
public class JSONPController {
	
	@Autowired
	private ItemService itemService;
	/**
	 * 利用api简化调用
	 * @param callback
	 * @return
	 */
	@RequestMapping("testJSONP")
	public JSONPObject testJson(String callback) {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(10010l);
		itemDesc.setItemDesc("jsonp跨域访问");
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		JSONPObject object = new JSONPObject(callback, itemDesc);
		return object;
	}
	/**
	 * url:www.jt.com/jsonp_item.html
	 * 利用jsonp技术实现后台数据的访问
	 * url:http://manage.jt.com/web/jsonpitem
	 * 将item数据表格展现
	 */
	@RequestMapping("jsonpitem")
	public JSONPObject homework(String callback) {
		System.out.println("真无敌");
		List<Item> itemList = itemService.findItemAll();
		return new JSONPObject(callback, itemList);
	}
	
	
	
	
	
	
	
	
//	@RequestMapping("testJSONP")
//	public String jsonp(String callback ) {
//		ItemDesc itemDesc = new ItemDesc();
//		itemDesc.setItemId(10010l);
//		itemDesc.setItemDesc("jsonp跨域访问");
//		itemDesc.setCreated(new Date());
//		itemDesc.setUpdated(new Date());
//		String json = ObjectMapperUtil.toJSON(itemDesc);
//		return callback+"("+json+")";
//		
//	}
}
