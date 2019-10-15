package com.santiago.notify.service;


import com.santiago.notify.entity.domain.RpNotifyRecord;
import com.santiago.notify.mapper.RpNotifyRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class NotifyJob {
//    @Scheduled(cron = "0/5 * * * * ?")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }
    @Autowired
    RpNotifyRecordMapper notifyRecordMapper;
    @Autowired
    NotifyService notifyService;

    @Scheduled(cron = "0/30 * * * * ?")
    private void notifyPayedTasks() {
        LocalDateTime now = LocalDateTime.now();
        Date time = now.minusMillis(30).toDate();
        Example example = new Example(RpNotifyRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan("gmtCreate", time)
                .andEqualTo("status", "0")
                .andLessThanOrEqualTo("notifyTimes", 10);
        List<RpNotifyRecord> recordList = notifyRecordMapper.selectByExample(example);
        recordList.forEach(record -> {
            notifyService.doNotify(record);
        });
    }
}
