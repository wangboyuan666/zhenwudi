package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.util.UserThreadLocalUtil;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Reference(check = false)
	private DubboCartService cartService;
	@RequestMapping("/show")
	public String show(Model model) {
		long userId = getUserId(); 
		List<Cart> cartList = cartService.findCartListByUsreId(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	private long getUserId() {
		User user = UserThreadLocalUtil.get();
		long userId = user.getId();
		return userId;
	}
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody//标识返回的是json的数据
	public SysResult updateCartNum(Cart cart ) {
		long userId = getUserId();
		cart.setUserId(userId);
		cartService.updateCartNum(cart);
		return SysResult.success();
	}
	/**
	 * 购物车的删除
	 * 当用户点击购物车的删除按钮的时候,从定向到购物车列表页面
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(Cart cart) {
		long userId = getUserId();
		cart.setUserId(userId);
		cartService.deleteCart(cart);
		return "redirect:/cart/show.html";
	}
	/**
	 * http://www.jt.com/cart/add/562379.html
	 */
	@RequestMapping("/add/{itemId}")
	public String saveCart(Cart cart) {
		long userId = getUserId();
		cart.setUserId(userId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";
	}
}
