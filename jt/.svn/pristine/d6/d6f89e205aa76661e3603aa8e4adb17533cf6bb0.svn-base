package com.jt.util;

import redis.clients.jedis.Jedis;


public class SingletonRedis {
	private SingletonRedis() {
		if(InnerSingleton.INSTANCE != null)
			throw new RuntimeException();
	}
	private static class InnerSingleton{
		private final static String host = "192.168.153.130";
		private final static int port  = 6379;
		private static final  Jedis INSTANCE = new Jedis(host,port);  
	}
	public static Jedis getInstance () {
		return InnerSingleton.INSTANCE;
	}
	
}
