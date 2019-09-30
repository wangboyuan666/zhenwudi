package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	/**
	 * 由于jsonp跨域的请求,使用特殊的对象(JSONPObject)封装
	 * 真正的返回值类型,SYSResult
	 * 1. param用户校验的参数
	 * 2.type 校验的字段:1. username ,2.phone 3.email
	 * 	@RequestMapping("zhenwudi")
	 * @return
	 * private Integer status;
	private String msg;
	private Object data;
	 */
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject check(@PathVariable String param, @PathVariable Integer type,String callback) {
		boolean data = userService.check(param,type);
		return new JSONPObject(callback,SysResult.success(data)) ;
	}
	/**
	 * 实现用户信息的回显
	 * 通过用户的ticket信息检索redis中是否存在该数据
	 */
	@RequestMapping("/query/{ticket}")
	public JSONPObject findUserByTicket(@PathVariable String ticket ,String callback) {
		String userJSON = jedisCluster.get(ticket);
		return new JSONPObject(callback,SysResult.success(userJSON));
	}
	
}
