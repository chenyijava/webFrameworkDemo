package com.demo.common.Util;

import com.demo.common.redisPool.RedisPool;
import com.demo.common.redisPool.SerializeUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.ShardedJedis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by root on 11/30/16.
 */
public class RedisUtil {

    private static Logger log = Logger.getLogger(RedisUtil.class);
    public static <T> T getObject(String key){
        ShardedJedis client = null;
        T t = null;
        try{
            if(client == null){

                client = RedisPool.getShardedPool().getResource();

            }
            t = (T) SerializeUtil.unserialize(client.get(SerializeUtil.serialize(key)));
        }catch (Exception e){
            log.error("RedisUtil exception:"+e.getMessage());
        }finally {
            if(null != client){
                RedisPool.getShardedPool().getResource().close();
            }
        }

        return t;
    }

    public static <T> void setObject(T t){

        ShardedJedis client = null;
        try {
            if (client == null){
                client = RedisPool.getShardedPool().getResource();
            }
            Method m = t.getClass().getMethod("getId");
            client.set(SerializeUtil.serialize(m.invoke(t))
                    ,SerializeUtil.serialize(t));

        }catch (NoSuchMethodException e1){
            log.error("set exception:" + e1.getMessage());
        }catch (IllegalAccessException e2){
            log.error("set exception:" + e2.getMessage());
        }catch (InvocationTargetException e3){
            log.error("set exception:" + e3.getMessage());
        }

    }

}
