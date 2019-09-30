package com.jt.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocalUtil;

import redis.clients.jedis.JedisCluster;
/**
 * 自定义拦截器
 * @author BoYuan
 *实现用户的权限认证,用户不登录不允许访问涉密操作,重定向到用户登录页面,如果用户登录,则请求放行
 */
@Component
public class UserInterceptor implements HandlerInterceptor{
	private static final String TICKET = "JT_TICKET";
	@Autowired
	private JedisCluster jedisCluster;
	//前置处理器
	/**
	 * 方法的说明:
	 * 1.boolean 类型 , 返回true,表示拦截放行,返回false表示拦截.
	 * 一般配合重定向使用
	 * 实现步骤
	 * 1.获取用户的cookie信息
	 * 2.去redis中进行校验
	 * 有则放行,无责拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
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
		//到redis中进行校验
		if(!StringUtils.isEmpty(ticket)) {
			String result = jedisCluster.get(ticket);
			if(!StringUtils.isEmpty(result)) {
				//shixian用户信息的动态获取
				User user = ObjectMapperUtil.toObject(result, User.class);
				//liyong request域待到controller
				//request.setAttribute("JT_USER", user);
				UserThreadLocalUtil.set(user);
				return true;
			}
		}

		//如果用户没有登录,拦截 并重定向
		response.sendRedirect("/user/login.html");
		return false;
	}
	//后置处理器
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	//视图渲染完成之后拦截
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//防止内存溢出
		UserThreadLocalUtil.remove();
	}
}
