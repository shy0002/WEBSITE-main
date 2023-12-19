package com.ayang.website.common.utils;

import com.ayang.website.common.domain.dto.RequestInfo;

/**
 * @author shy
 * @date 2023/12/19
 * @description 请求上下文
 */
public class RequestHolder {
    private static final ThreadLocal<RequestInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        THREAD_LOCAL.set(requestInfo);
    }

    public static RequestInfo get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
