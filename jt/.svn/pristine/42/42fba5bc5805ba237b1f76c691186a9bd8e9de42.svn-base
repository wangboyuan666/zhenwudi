package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Cart;

public interface CartMapper extends BaseMapper<Cart>{

	void delete(Long userId, Long itemId);

	List<Cart> findCartListByUsreId(Long userId);

	void updateCartNum(Cart cartTemp);

	void updateCart(Cart cartDB);

	//void updateCart(@Param("num") Integer num, @Param("id") Long id);

	void delete(Cart cart);
	
}
