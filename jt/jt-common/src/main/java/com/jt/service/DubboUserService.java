package com.jt.service;

import com.jt.pojo.User;

public interface DubboUserService {

	void savaUser(User user);

	String findUserbyUP(User user);
	
}
