package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;

public interface ItemMapper extends BaseMapper<Item>{

	List<Item> selectItemdByPage(@Param("start") Integer start, @Param("rows") Integer rows);

	void updateItems(@Param("status")int status, @Param("ids")Long[] ids);
	
	
}
