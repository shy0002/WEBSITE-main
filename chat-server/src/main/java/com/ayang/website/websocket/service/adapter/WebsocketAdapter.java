package com.ayang.website.websocket.service.adapter;

import com.ayang.website.common.domain.enums.YesOrNoEnum;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.websocket.domain.enums.WebsocketRespTypeEnum;
import com.ayang.website.websocket.domain.vo.resp.WebsocketBaseResp;
import com.ayang.website.websocket.domain.vo.resp.WebsocketBlack;
import com.ayang.website.websocket.domain.vo.resp.WebsocketLoginSuccess;
import com.ayang.website.websocket.domain.vo.resp.WebsocketLoginUrl;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

/**
 * @author shy
 * @date 2023/12/7
 * @description WebsocketAdapter
 */
public class WebsocketAdapter {
    public static WebsocketBaseResp<?> buildResp(WxMpQrCodeTicket wxMpQrCodeTicket) {
        WebsocketBaseResp<WebsocketLoginUrl> resp = new WebsocketBaseResp<>();
        resp.setType(WebsocketRespTypeEnum.LOGIN_URL.getType());
        resp.setDate(new WebsocketLoginUrl(wxMpQrCodeTicket.getUrl()));
        return resp;
    }

    public static WebsocketBaseResp<?> buildResp(User user, String token, boolean hasPower) {
        WebsocketBaseResp<WebsocketLoginSuccess> resp = new WebsocketBaseResp<>();
        resp.setType(WebsocketRespTypeEnum.LOGIN_SUCCESS.getType());
        WebsocketLoginSuccess loginSuccess = WebsocketLoginSuccess.builder()
                .avatar(user.getAvatar())
                .name(user.getName())
                .token(token)
                .uid(user.getId())
                .power(hasPower ? YesOrNoEnum.YES.getStatus() : YesOrNoEnum.NO.getStatus())
                .build();
        resp.setDate(loginSuccess);
        return resp;

    }

    public static WebsocketBaseResp<?> buildWaitAuthorizeResp() {
        WebsocketBaseResp<WebsocketLoginUrl> resp = new WebsocketBaseResp<>();
        resp.setType(WebsocketRespTypeEnum.LOGIN_SCAN_SUCCESS.getType());
        return resp;
    }

    public static WebsocketBaseResp<?> buildInvalidTokenResp() {
        WebsocketBaseResp<WebsocketLoginUrl> resp = new WebsocketBaseResp<>();
        resp.setType(WebsocketRespTypeEnum.INVALIDATE_TOKEN.getType());
        return resp;
    }

    public static WebsocketBaseResp<?> buildBlack(User user) {
        WebsocketBaseResp<WebsocketBlack> resp = new WebsocketBaseResp<>();
        resp.setType(WebsocketRespTypeEnum.BLACK.getType());
        WebsocketBlack build = WebsocketBlack.builder()
                .uid(user.getId())
                .build();
        resp.setDate(build);
        return resp;
    }
}
