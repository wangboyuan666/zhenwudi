package com.jt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
/**
 * 将上述操作利用mybatis形式完成
 * @author BoYuan
 *
 */
@Service
public class DubboCartServiceImpl implements DubboCartService{
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<Cart> findCartListByUsreId(Long userId) {
		List<Cart> list = cartMapper.findCartListByUsreId(userId);
		return list;
	}

	@Override
	public void updateCartNum(Cart cart) {
//		Cart cartTemp = new Cart();
//		cartTemp.setNum(cart.getNum()).setUpdated(new Date());
		//UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
		//updateWrapper.eq("item_id", cart.getItemId()).eq("user_id", cart.getUserId());
		cartMapper.updateCartNum(cart);
	}
	/**
	 * 对象中属性不为空的元素当做where条件
	 */
	@Override
	public void deleteCart(Cart cart) {
		if(cart.getUserId() == null &&cart.getItemId() == null ) 
			return ;
		
		cartMapper.delete(cart);
		
	}
	/**
	 * 如果购物车列表中有该数据,则做数量的修改操作
	 * 如果购物车中没有该数据,做数据的新增
	 */
	@Override
	public void saveCart(Cart cart) {
		Cart cartTemp = new Cart();
		cartTemp.setUserId(cart.getUserId()).setItemId(cart.getItemId());
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>(cartTemp);
		Cart cartDB = cartMapper.selectOne(queryWrapper);
		if(cartDB == null) {
			//数据库中没有记录,新增
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			//有数据,则只修改数量
			Integer num = cart.getNum()+cartDB.getNum();
			//Long id = cartDB.getId();
			cartDB.setNum(num).setUpdated(new Date());
			//条件只有一个,就是逐渐充当where条件,其他数据当做要修改的数据
			//cartMapper.updateById(cartDB);
			cartMapper.updateCart(cartDB);
			//上述的操作效率不高
		}
	}
	
}
