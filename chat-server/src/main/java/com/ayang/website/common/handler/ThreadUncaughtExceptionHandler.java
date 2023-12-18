package com.ayang.website.common.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shy
 * @date 2023/12/18
 * @description ThreadUncaughtExceptionHandler
 */
@Slf4j
public class ThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final ThreadUncaughtExceptionHandler instance= new ThreadUncaughtExceptionHandler();
    @Override
    public void uncaughtException(Thread t, Throwable e) {
      log.error("Exception in thread {} ", t.getName(), e);
    }

    public static ThreadUncaughtExceptionHandler getInstance(){
        return instance;
    }
}
