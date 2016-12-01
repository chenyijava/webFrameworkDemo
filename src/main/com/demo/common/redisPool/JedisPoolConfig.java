package com.demo.common.redisPool;

/**
 * Created by root on 11/30/16.
 */
public class JedisPoolConfig {

    private static final int redisPort = 6379;
    private static final String redisAddress = "127.0.0.1";
    private static final int maxIdle = 10;
    private static final int maxTotal = 30;
    private static final int maxWaitMillis = 3000;
    private static final String scheme = "http://";
    private static final int db = 3;

    public static int getRedisPort() {
        return redisPort;
    }

    public static String getRedisAddress() {
        return redisAddress;
    }

    public static int getMaxIdle() {
        return maxIdle;
    }

    public static int getMaxTotal() {
        return maxTotal;
    }

    public static int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public static String getScheme() {
        return scheme;
    }

    public static int getDb() {
        return db;
    }
}
