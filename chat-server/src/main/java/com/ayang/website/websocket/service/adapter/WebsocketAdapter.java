package com.ayang.website.websocket.service.adapter;

import com.ayang.website.websocket.domain.enums.WebsocketRespTypeEnum;
import com.ayang.website.websocket.domain.vo.resp.WebsocketBaseResp;
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

}
