package com.santiago.gateway.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.santiago.api.CoreApi;
import com.santiago.api.NotifyApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatCtrl {
    @NacosValue(value = "${heart.beat.resp:pong}", autoRefreshed = true)
    private String heartBeatResp;
    @Autowired
    DefaultUidGenerator defaultUidGenerator;
    @Autowired
    NotifyApi notifyApi;
    @Autowired
    CoreApi coreApi;

    @RequestMapping(value = "/ping")
    public String ping() {
        System.out.println(notifyApi.batchNotify());
        System.out.println("uid" + coreApi.getUid());
        return heartBeatResp + ":" + defaultUidGenerator.parseUID(defaultUidGenerator.getUID());
    }
}
