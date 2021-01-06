package com.keroro.redistest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class demo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("8.135.59.138",6379);
        System.out.println(jedis.ping());
        jedis.set("k1","v1");
        jedis.set("k2","v2");

        Transaction transaction =jedis.multi();
        transaction.set("k4","v4");
        transaction.set("k5","v5");
        transaction.exec();
    }
}
