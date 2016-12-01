package com.demo.common.redisPool;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/29/16.
 */
public class RedisPool {
//    public static JedisPool pool = null;
//
//    public static JedisPool getPool(){
//        if (pool == null){
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxIdle(5);
//            config.setMaxTotal(500);
//            config.setMaxWaitMillis(1000 * 100);
//            config.setTestOnBorrow(true);
//            pool = new JedisPool(config,"127.0.0.1",6370);
//        }
//        return pool;
//    }

    public static ShardedJedisPool jedisPool;

    private static void createJedisPool(){
        try {

            List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
            String[] address = getRedisAddress(JedisPoolConfig.getRedisAddress());
            for (int i = 0; i < address.length;i++){
                shards.add(new JedisShardInfo(JedisPoolConfig.getScheme()+address[i]+":"+JedisPoolConfig.getRedisPort()+
                "/"+JedisPoolConfig.getDb()));
            }
            redis.clients.jedis.JedisPoolConfig config = new redis.clients.jedis.JedisPoolConfig();
            config.setMaxIdle(JedisPoolConfig.getMaxIdle());
            config.setMaxTotal(JedisPoolConfig.getMaxTotal());
            config.setMaxWaitMillis(JedisPoolConfig.getMaxWaitMillis());

            jedisPool = new ShardedJedisPool(config,shards);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static ShardedJedisPool getShardedPool(){
        if (jedisPool == null){
            createJedisPool();
        }
        return jedisPool;
    }

    public static ShardedJedis getShardedJedis(){
        ShardedJedis jedis = null;
        try {
            jedis = getShardedPool().getResource();
        }catch (Exception e){
            System.out.println(e);
        }
        return jedis;
    }

    private static String[] getRedisAddress(String redisAddress){
        String[] address = null;
        if(StringUtils.isNotBlank(redisAddress) && redisAddress.contains(",")){
            address = redisAddress.split(",");
        }else {
            address = new String[1];
            address[0] = redisAddress;
        }

        return address;
    }

}
