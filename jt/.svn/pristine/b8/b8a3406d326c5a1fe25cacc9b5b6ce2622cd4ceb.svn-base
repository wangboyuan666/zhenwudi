package com.jt;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JDesktopPane;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.terracotta.quartz.ClusteredJobStore;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedis;

/**
 * 该测试类,测试redis全部操作
 * @author BoYuan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

//	@Autowired
//	private Jedis jedis;
//	@Test
//	public void testSentinel() {
//		jedis.del("干嘛呀");
//	}
	@Autowired
	private JedisCluster jedis;
	@Test
	public void testCluster() {
		jedis.set("zhenwudi", "大河之水天上来");
		System.out.println(jedis.get("zhenwudi"));
	}
	
	/**
	 * 实现redis分片测试
	 */
//	@Test
//	public void testshards() {
//		System.out.println(shardedJedis);
//		shardedJedis.set("zhenwudi", "nonghslei");
//		System.out.println(shardedJedis.get("zhenwudi"));
//	}

















}
