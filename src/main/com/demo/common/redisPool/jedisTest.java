package com.demo.common.redisPool;

import com.demo.common.Util.RedisUtil;
import com.demo.entity.good;
import redis.clients.jedis.ShardedJedis;

/**
 * Created by root on 11/30/16.
 */
public class jedisTest {
    public static void main(String args[]){
        ShardedJedis client = null;
        try {
            client = RedisPool.getShardedPool().getResource();

//            String result = client.set("key1","value1");
//            System.out.println(String.format("set commend result:%s",result));
//            String value = client.get("key1");
//            System.out.println(String.format("get commend result:%s",value));
            good good = new good();
            good.setId("1");
            good.setName("good");
            good.setPrice("100");
//            String result = client.set("entity".getBytes(),SerializeUtil.serialize(good));
//            System.out.println(String.format("set commend result:%s",result));
//            byte[] value = client.get("entity".getBytes());
//            good good1 = (good) SerializeUtil.unserialize(client.get("entity".getBytes()));
//            System.out.println(good1.getId());
            long local = System.currentTimeMillis();
            RedisUtil.setObject(good);
            good value = RedisUtil.getObject("1");
            System.out.println(value.getId());
            System.out.println("1".getBytes());

        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            if (null != client){
                RedisPool.getShardedPool().getResource().close();
            }
        }
    }

}
