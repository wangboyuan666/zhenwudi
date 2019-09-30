package com.jt.service;

import java.util.List;

import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {
	EasyUITable findItemsByPage(Integer page,Integer rows);

	void saveItem(Item item, ItemDesc itemDesc);

	void updateItem(Item item, ItemDesc itemDesc);

	void deleteItems(Long[] ids);

	void updateItems(int status, Long[] ids);

	ItemDesc findItemDescById(Long id);

	Item findItemById(Long id);

	List<Item> findItemAll();

}
