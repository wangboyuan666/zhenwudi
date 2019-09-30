package com.jt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * UserController涉及到页面的跳转用普通的controller'
 * @author BoYuan
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;
@Controller
@RequestMapping("/user")
public class UserController {
	//标识当前变量不可变时使用
	private static final String TICKET = "JT_TICKET";
	
	@Reference(check = false)
	private DubboUserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	/**
	 * 通用的页面的跳转
	 * @param moduleName
	 * @return
	 */
	@RequestMapping("/{moduleName}")
	public String module(@PathVariable String moduleName) {
		return moduleName;
	}
	/**
	 * 注意是否为json返回
	 * @param user
	 * @return
	 */
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult doRegister(User user) {
		//System.out.println("111111122222222222222");
		userService.savaUser(user);
		return SysResult.success();
	}
	/**
	 * 登录操作
	 * @param username
	 * @param password
	 * @return
	 * 关于cookie:
	 * cookie.setPath("/") 全部的用户都可见这个cookie
	 * cookie.setPath("/aa") 只有/aa路径下的url才能访问该cookie
	 * cookie的存活时间,cookie.setMaxAge(0); 删除cookie
	 * cookie.setMaxAge(-1) ;关闭会话删除cookie
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user ,HttpServletResponse response) {
		//校验数据是否正确
		String ticket = userService.findUserbyUP(user);
		//秘钥没有值,返回失败
		if(StringUtils.isEmpty(ticket)) 
			return SysResult.fail();
		//入股程序执行到这里标识有值,写入cookie
		Cookie cookie = new Cookie(TICKET,ticket);
		cookie.setMaxAge(7*24*3600);
		cookie.setPath("/");
		//hen关键,设置cookie的共享  
		cookie.setDomain("jt.com");
		//将cookie写入客户端 
		response.addCookie(cookie);
		return SysResult.success();
	}
	/**
	 * 实现用户登出操作
	 * 当用户点击退出按钮的时候,应该删除redis数据和cookie中的数据
	 */
	@RequestMapping("/logout")
	public String doLogout(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String ticket = null;
		//判断cookie是有效的的
		if(cookies.length != 0) {
			for(Cookie cookie : cookies) {
				if(TICKET.equals(cookie.getName())) {
					ticket = cookie.getValue();
					break;
				}
			}
		}
		//如果数据不为空,则删除redis数据,和cookie
		if(!StringUtils.isEmpty(ticket)) {
			jedisCluster.del(ticket);
			Cookie newCookie  =  new Cookie(TICKET, "");
			newCookie.setMaxAge(0);
			newCookie.setDomain("jt.com");
			newCookie.setPath("/");
			response.addCookie(newCookie);
		}
		//从定向到系统首页
		return "redirect:/";
	}

}
