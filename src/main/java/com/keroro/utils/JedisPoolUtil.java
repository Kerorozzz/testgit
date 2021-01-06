package com.keroro.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;
    private JedisPoolUtil(){};
    public  static  JedisPool getJedisPoolInstance(){
        if (null == jedisPool){
            synchronized (JedisPoolUtil.class){
                if (jedisPool==null){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);   //最大连接数
                    poolConfig.setMaxIdle(32);      //最大空闲连接
                    poolConfig.setMaxWaitMillis(100*1000);  //最大阻塞时间
                    poolConfig.setTestOnBorrow(true);       //检测连通性，就是ping
                    jedisPool = new JedisPool(poolConfig,"8.135.59.138",6379);
                }
            }
        }
        return jedisPool;
    }
    public  static void release(JedisPool jedisPool, Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }
}
