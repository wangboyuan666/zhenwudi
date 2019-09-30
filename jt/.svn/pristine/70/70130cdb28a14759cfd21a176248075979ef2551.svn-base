package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.SysResult;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	/**
	 * param 表示拥护需要校验的数据,type表示校验的类型
	 */
	@Override
	public boolean check(String param, Integer type) {
		String column = type == 1 ? "username": (type == 2? "phone":"email");
		QueryWrapper< User> wrapper = new QueryWrapper<User>();
		wrapper.eq(column, param);
		int count = userMapper.selectCount(wrapper);
		return count == 0 ? false:true;
	}
}
