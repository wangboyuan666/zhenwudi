package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	@RequestMapping("/getMsg")
	@ResponseBody
	public String getMsg(){
		return "我是8091,,,";
	}
	/**
	 * 根据用户请求跳转通用的页面跳转
	 * @param moduleName
	 * @return
	 *
	 * 语法规则, Springmvc 动态获取url中的数据
	 * 1 参数必须使用{} 包裹
	 * 2 参数与参数之间用 / 分割 , 参数位置固定
	 * 3 利用@PathVariable 注解接收,并且名称一致!!!
	 *	restFui风格 :
	 *1 动态的获取 用户参数
	 * 2 常用形式:请求路径是不变的,根据请求方式的不同实现crud操作
	 * 需求:实现user的crud操作
	 *	通过请求方式进行增删改查
	 */
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable String moduleName) {
		return moduleName;
	}
	/**
	 * post请求实现用户的新增
	 * @return
	 */
	@PostMapping("/user")
	public String insert() {
		return "";
	}
	/**
	 * delete请求实现用户的删除操作
	 * @return
	 */
	@DeleteMapping("/user")
	public String delete() {
		return "";
	}
	/**
	 * put请求实现用户的更新操作
	 * @return
	 */
	@PutMapping("/user")
	public String update() {
		return "";
	}
	/**
	 * get请求实现用户的查询操作
	 * @return
	 */
	@GetMapping("/user")
	public String select() {
		return "";
	}
	
}
