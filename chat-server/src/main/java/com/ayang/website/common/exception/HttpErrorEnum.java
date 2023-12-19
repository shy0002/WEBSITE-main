package com.ayang.website.common.exception;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import com.ayang.website.common.domain.vo.resp.ApiResult;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author shy
 * @date 2023/12/19
 * @description HTTP错误返回的枚举
 */
@AllArgsConstructor
public enum HttpErrorEnum {
    ACCESS_DENIED(401, "登录失效，请重新登陆"),
    ;
    private Integer httpCode;
    private String desc;

    public void sendHttpError(HttpServletResponse response) throws IOException {
        response.setStatus(httpCode);
        response.setContentType(ContentType.JSON.toString(StandardCharsets.UTF_8));
        response.getWriter().write(JSONUtil.toJsonStr(ApiResult.fail(httpCode, desc)));
    }
}
