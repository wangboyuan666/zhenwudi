package com.jt.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements DubboUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisCluster jedisCluster;
	/**
	 * 暂时使用用户的电话代替邮箱
	 * 盐 
	 * md5加密算法:
	 * 
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void savaUser(User user) {
		//.System.out.println("111111111");
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		user.setEmail(user.getPhone()).setCreated(new Date()).setUpdated(user.getCreated());
		userMapper.insert(user);
	}
	@Override 
	public String findUserbyUP(User user) {
		String ticket = null;
		//weile 与数据库数据一致,需要将密码加密处理
		String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Password);
		QueryWrapper< User > queryWrapper = new QueryWrapper<User>(user);
		User userDB = userMapper.selectOne(queryWrapper);
		//将数据库数据转化为json保存到redis中
		if(userDB != null) {
			String uuid = UUID.randomUUID().toString();
			ticket = DigestUtils.md5DigestAsHex(uuid.getBytes());
			//进行脱敏的处理
			userDB.setPassword("***********");
			String userJSON = ObjectMapperUtil.toJSON(userDB);
			jedisCluster.setex(ticket,7*24*3600 , userJSON);
			}
		return ticket;
	}
	
	
	
	
}
