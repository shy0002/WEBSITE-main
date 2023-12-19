package com.ayang.website.common.interceptor;

import com.ayang.website.common.exception.HttpErrorEnum;
import com.ayang.website.user.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

/**
 * @author shy
 * @date 2023/12/19
 * @description Token拦截器
 */
@Component
@AllArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_SCHEMA = "Bearer ";
    public static final String UID = "uid";

    private final LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = getToken(request);
        Long uid = loginService.getValidUid(token);
        if (Objects.nonNull(uid)) {
            // 用户有登录态
            request.setAttribute(UID, uid);
        } else {
            // 用户未登录
            boolean isPublicUri = isPublicUri(request);
            if (!isPublicUri) {
                // 401
                HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
                return false;
            }
        }
        return true;
    }

    private static boolean isPublicUri(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String[] split = requestUri.split("/");
        return split.length > 3 && Objects.equals("public", split[3]);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);
        return Optional.ofNullable(token)
                .filter(h -> h.startsWith(AUTHORIZATION_SCHEMA))
                .map(h -> h.replaceFirst(AUTHORIZATION_SCHEMA, ""))
                .orElse(null);
    }
}
