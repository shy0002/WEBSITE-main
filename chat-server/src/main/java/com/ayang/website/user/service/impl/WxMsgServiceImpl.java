package com.ayang.website.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ayang.website.user.dao.UserDao;
import com.ayang.website.user.domain.entity.User;
import com.ayang.website.user.service.WxMsgService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author shy
 * @date 2023/12/8
 * @description WXMsgServiceImpl
 */
@Service
@Slf4j
public class WxMsgServiceImpl implements WxMsgService {

    @Autowired
    private UserDao userDao;
    @Override
    public WxMpXmlOutMessage scan(WxMpXmlMessage wxMpXmlMessage) {
        String openId = wxMpXmlMessage.getFromUser();
        Integer code = getEventKey(wxMpXmlMessage);
        if (Objects.isNull(code)){
            return null;
        }
        User user = userDao.getByOpenId(openId);
        boolean registered = Objects.nonNull(user);
        // 用户已经注册并授权
        if (registered && StrUtil.isNotBlank(user.getAvatar())){
            // todo 走登录成功逻辑 通过code找到channel，然后给channel推送消息
        }
        // 没有登录成功
//        if ()
        return null;
    }

    private Integer getEventKey(WxMpXmlMessage wxMpXmlMessage) {

        try {
            String eventKey = wxMpXmlMessage.getEventKey();
            String code = eventKey.replace("qrscene_", "");
            return Integer.parseInt(code);
        } catch (Exception e) {
            log.error("getEventKey error eventKey:{}", wxMpXmlMessage.getEventKey());
            return null;
        }

    }
}
