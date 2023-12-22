package com.ayang.website.common.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
     * @return
     */
    String prefixKey() default "";

    /**
     * 支持springEl表达式的key
     * @return
     */
    String key();

    /**
     * 等待申请的排队时间，默认快速失败
     * @return
     */
    int waitTime() default -1;

}
