package com.jt.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;

/**
 * 要求从后台服务器返回json数据
 * @author BoYuan
 *
 */
@RestController
@RequestMapping("/web/item/")
public class WebItemController {
	@Autowired
	private ItemService itemService;
	/**
	 * 根据itmeid查询商品信息
	 * 1.查询item的信息
	 * 2.查询itemdesc的信息
	 */
	@RequestMapping("findItemById/{id}")
	public Item findItemById(@PathVariable Long id) {
		return itemService.findItemById(id);
	}
	@RequestMapping("findItemDescById/{id}")
	public ItemDesc findItemDescById(@PathVariable Long id) {
		return itemService.findItemDescById(id);
	}
	
	
	
}
