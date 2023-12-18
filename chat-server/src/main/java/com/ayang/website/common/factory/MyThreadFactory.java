package com.ayang.website.common.factory;

import com.ayang.website.common.handler.ThreadUncaughtExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;

/**
 * @author shy
 * @date 2023/12/18
 * @description MyThreadFactory
 */
@Slf4j
@AllArgsConstructor
public class MyThreadFactory implements ThreadFactory {
    private ThreadFactory threadFactory;
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = threadFactory.newThread(r);
        thread.setUncaughtExceptionHandler(ThreadUncaughtExceptionHandler.getInstance());
        return null;
    }
}
