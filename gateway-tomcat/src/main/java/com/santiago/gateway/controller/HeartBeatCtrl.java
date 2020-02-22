package com.santiago.gateway.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.santiago.commons.util.DateUtil;
import com.santiago.commons.util.NewDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HeartBeatCtrl {
    @NacosValue(value = "${heart.beat.resp:pong}", autoRefreshed = true)
    private String heartBeatResp;
//    @Autowired
//    DefaultUidGenerator defaultUidGenerator;
//    @Autowired
//    NotifyApi notifyApi;
//    @Autowired
//    CoreApi coreApi;
//    @Resource(name="taskExecutor")
//    ThreadPoolTaskExecutor threadPoolTaskExecutor;

//    @RequestMapping(value = "/ping")
//    public String ping() throws ExecutionException, InterruptedException {
////        System.out.println(notifyApi.batchNotify());
////        System.out.println("uid" + coreApi.getUid());
//        ArrayList<Future<String>> futures = new ArrayList<>();
//        Future submit = threadPoolTaskExecutor.submit(new Foo());
//        System.out.println(submit.get());
//        futures.add(submit);
//        return "pong,currentTime:" + submit.get();
////        return heartBeatResp + ":" + defaultUidGenerator.parseUID(defaultUidGenerator.getUID());
//    }

    class Foo implements Callable {

        @Override
        public Object call() {
            return NewDateUtils.getCurrentTimeStr(NewDateUtils.YYYY_MM_DD_HH_MM_SS);
        }
    }
}
