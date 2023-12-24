package com.ayang.website.common.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author shy
 * @date 2023/12/22
 * @description 分布式锁注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedissonLock {
    /**
     * key的前缀，默认取方法全限定名，也可以自己执行
     */
    String prefixKey() default "";

    /**
     * 支持springEl表达式的key
     */
    String key();

    /**
     * 等待申请的排队时间，默认快速失败
     */
    int waitTime() default -1;

    /**
     * 时间单位，默认毫秒
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;

}
