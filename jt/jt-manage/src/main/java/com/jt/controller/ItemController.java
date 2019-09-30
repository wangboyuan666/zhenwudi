package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/item/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("query/item/desc/{id}")
	public SysResult findItemDescById(@PathVariable Long id ) {
		ItemDesc itemDesc = itemService.findItemDescById(id);
		return SysResult.success(itemDesc); //SYsResult.data = ItemDesc
	} 
	
	/**
	 * 商品上架
	 * @param ids
	 * @return
	 * 
	 */
	@RequestMapping("reshelf")
	public SysResult doReshelf(Long ...ids) {
		int status = 1;
		itemService.updateItems(status,ids);
		return SysResult.success();
	}
	/**
	 * 商品下架
	 * @param ids
	 * @return
	 */
	@RequestMapping("instock")
	public SysResult doInstock(Long ...ids) {
		int status = 2;
		itemService.updateItems(status,ids);
		return SysResult.success();
	}
	@RequestMapping("query")
	public EasyUITable doQuery(Integer page,Integer rows){
		return itemService.findItemsByPage(page,rows);
	}
	/**
	 * 实现商品的新增
	 * @return
	 */
	@RequestMapping("save")
	public SysResult doSaveItem(Item item,ItemDesc itemDesc) {
		itemService.saveItem(item,itemDesc);
		return SysResult.success();
	}
	@RequestMapping("update")
	public SysResult doUpdateItem(Item item,ItemDesc itemDesc) {
		itemService.updateItem(item,itemDesc);
		return SysResult.success();
	}
	@RequestMapping("delete")
	public SysResult doDeleteItems(Long ...ids) {
		itemService.deleteItems(ids);
		return SysResult.success();
	}
}
