package com.ayang.website.common.constant;

/**
 * @description: redis的key生成类
 * @author: <a href="https://github.com/shy0002“>ayang</a>
 * @date: 2023/12/16
 **/

public class RedisKey {

    private static final String BASE_KEY = "website:chat";

    /**
     * 用户的key
     */
    public static final String USER_TOKEN = "userToken:uid_%d";

    public static String getKey(String key, Object... o) {
        return BASE_KEY + String.format(key, o);
    }
}
