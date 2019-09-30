package com.jt.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
@PropertySource("classpath:properties/jedis.properties")
@Configuration
public class RedisConfig {
	@Value("${redis.nodes}")
	private String nodes;
	@Bean
	public Set<HostAndPort> nodes(){
		Set<HostAndPort> node = new HashSet<>();
		String[] arrayNodes = nodes.split(",");
		for(String  n : arrayNodes) {
			String host = n.split(":")[0];
			int port = Integer.parseInt(n.split(":")[1]);
			node.add(new HostAndPort(host, port));
		}
		return node;
	}
	@Bean
	public JedisCluster jedis(Set<HostAndPort> node) {
		return new JedisCluster(node);
	}
	
	
	
	
	
	
	
	
	
	

	//	/**
	//	 * 方法的返回值交给容器管理
	//	 * @return
	//	 */
	//	@Bean("jedis")
	//	public Jedis newJedis() {
	//		return new Jedis(host,port);	
	//	}

	//@Bean("shards")
//	public List<JedisShardInfo> getList(){
//		List<JedisShardInfo> list  = new ArrayList<JedisShardInfo>();
//		String[] arrayNodes = nodes.split(",");
//		for(String node : arrayNodes) {
//			String host = node.split(":")[0];
//			int port  = Integer.parseInt(node.split(":")[1]);
//			JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port);
//			list.add(jedisShardInfo);
//		}
//		return list;
//	}
//	@Bean("shardedJedis")
//	public ShardedJedis newShardedJedis() {
//		List<JedisShardInfo> shards = getList();
//		System.out.println(shards);
//		return new ShardedJedis(shards);
//	}
//	@Bean("pool")
//	public JedisSentinelPool getPool() {
//		//String[] arrayNodes = nodes.split(",");
//		Set<String> sentinels = new HashSet<>();
//		sentinels.add(nodes);
//		return new JedisSentinelPool("mymaster", sentinels);
//	}
//	@Scope("prototype")
//	@Bean("jedis")
//	public Jedis getJedis(@Autowired JedisSentinelPool pool) {
//		return pool.getResource();
//	}
}
