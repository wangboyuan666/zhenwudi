package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Override
	public EasyUITable findItemsByPage(Integer page, Integer rows) {
		if(page == null || page < 0)
			throw new IllegalArgumentException("参数类型不合法");
		if(rows == null || rows <=0)
			throw new IllegalArgumentException("参数类型不合法");
		//获取记录的总数
		int total = itemMapper.selectCount(null);
		if(total == 0)
			throw new IllegalArgumentException("记录已经不存在");
		int start = (page-1)*rows;
		List<Item> itemList = itemMapper.selectItemdByPage(start,rows);
		return new EasyUITable(total,itemList);
	}

	@Override
	public void saveItem(Item item,ItemDesc itemDesc) {
		if(item == null)
			throw new IllegalArgumentException();
		if(itemDesc == null)
			throw new IllegalArgumentException();
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		//入库商品描述信息
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updateItem(Item item,ItemDesc itemDesc) {
		if(item == null)
			throw new IllegalArgumentException();
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		//商品描述表的更新
		itemDesc.setUpdated(item.getUpdated());
		itemDesc.setItemId(item.getId());
		itemDescMapper.updateById(itemDesc);
	}

	@Override
	public void deleteItems(Long[] ids) {
		if(ids == null || ids.length <= 0) 
			throw new IllegalArgumentException();
		List<Long> idList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
		//商品描述表的删除
		itemDescMapper.deleteBatchIds(idList);
	}

	/**
	 * 商品上架下架
	 */
	@Override
	public void updateItems(int status, Long[] ids) {
		if(ids == null || ids.length <=0)
			throw new IllegalArgumentException();
		itemMapper.updateItems(status,ids);
	}

	@Override
	public ItemDesc findItemDescById(Long id) {
		ItemDesc itemDesc = itemDescMapper.selectById(id);
		return itemDesc;
	}

	@Override
	public Item findItemById(Long id) {
		return itemMapper.selectById(id);
	}

	@Override
	public List<Item> findItemAll() {
		return itemMapper.selectList(null);
	}









}
