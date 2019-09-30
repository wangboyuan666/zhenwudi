package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.annotation.RequiredCacheSelect;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat/")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 根据商品分类id信息,查询商品分类名称
	 * @param id
	 * @return
	 */
	//@Transactional
	@RequestMapping("queryItemName")
	public String queryItemName(Long itemCatId) {
		ItemCat itemCat = itemCatService.queryNameById(itemCatId);
		return itemCat.getName();
	}
	/**
	 * 查询商品的分类信息,进行树形结构的展现
	 * @return
	 */
	@RequiredCacheSelect
	@RequestMapping("list")
	public List<EasyUITree> selectItemCatList(@RequestParam(value="id",defaultValue = "0",required = true)Long parentId) {
		//System.out.println("查询数据库");
		List<EasyUITree> treeList = itemCatService.selectItemCatList(parentId);
		//List<EasyUITree> treeList = itemCatService.findItemCache(parentId);
		return treeList;
	}
}
