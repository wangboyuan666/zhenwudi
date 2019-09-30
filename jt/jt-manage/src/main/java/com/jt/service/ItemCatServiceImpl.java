package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.annotation.RequiredCacheSelect;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private ItemCatMapper itemCatMapper;
	//@Autowired
	//private Jedis jedis;

	@Override
	public ItemCat queryNameById(Long itemCatId) {
		if(itemCatId == null || itemCatId <0)
			throw new IllegalArgumentException();
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		return itemCat;
	}
	/**
	 * 返回值的结果:List<EasyUITree> 
	 * 数据的雷院:tb_item_cat 表
	 * 转化的过程 :List<ItemCat> 到List<EasyUITree>
	 */
	@Override
	public List<EasyUITree> selectItemCatList(Long parentId) {
		List<EasyUITree> treeList = new ArrayList<>();
		//获取数据库的数据
		List<ItemCat> catList = findItemCatList(parentId);
		//实现数据的转化 一级 和二级closed 三级open
		for(ItemCat itemCat : catList ) {
			EasyUITree tree = new EasyUITree();
			String state= itemCat.getIsParent()?"closed":"open";
			tree.setId(itemCat.getId());
			tree.setText(itemCat.getName());
			tree.setState(state);
			treeList.add(tree);
		}
		return treeList;
	}
	private List<ItemCat> findItemCatList(Long parentId) {
		QueryWrapper< ItemCat> queryWrapper = new  QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		List<ItemCat> catList = itemCatMapper.selectList(queryWrapper);
		return catList;
	}
	/**
	 * 从缓存中拿非常的快啊
	 */
	//@RequiredCacheSelect(key = "parentId"  , target = List.class)
	@SuppressWarnings("unchecked")
	@Override
	public List<EasyUITree> findItemCache(@PathVariable Long parentId) {
		List<EasyUITree> treeList = new ArrayList<>();
		
			treeList = selectItemCatList(parentId);
			//将数据转化为json的串 存储到数据库
			String dataJSON = ObjectMapperUtil.toJSON(treeList);
		
		return treeList;
	}
	

}
