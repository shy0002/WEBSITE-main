package com.ayang.website.websocket.domain.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * @author shy
 * @date 2023/12/5
 * @description 用户上下线变动的推送类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketOnlineOfflineNotify {
    private List<ChatMemberResp> changeList = new ArrayList<>();//新的上下线用户
    private Long onlineNum;//在线人数
}
