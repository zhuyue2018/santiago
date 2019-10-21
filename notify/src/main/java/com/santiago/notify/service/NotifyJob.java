package com.santiago.notify.service;


import com.santiago.commons.enums.StatusEnum;
import com.santiago.notify.entity.domain.NotifyRecord;
import com.santiago.notify.mapper.NotifyRecordMapper;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class NotifyJob {
    private static final Logger logger = LoggerFactory.getLogger(NotifyJob.class);
//    @Scheduled(cron = "0/5 * * * * ?")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }
    @Autowired
    NotifyRecordMapper notifyRecordMapper;
    @Autowired
    NotifyService notifyService;

    /**
     * 每30s执行一次。
     * 查询60分钟内状态为init，通知次数小于等于10的记录，并执行通知。
     */
    @Scheduled(cron = "0/30 * * * * ?")
    private void notifyPayedJob() {
        LocalDateTime now = LocalDateTime.now();
        Date time = now.minusHours(9).toDate();
        Example example = new Example(NotifyRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan("gmtCreate", time)
                .andEqualTo("status", StatusEnum.INIT.getCode())
                .andLessThanOrEqualTo("notifyTimes", 10);
        List<NotifyRecord> recordList = notifyRecordMapper.selectByExample(example);
        if (recordList.size() > 0) {
            recordList.forEach(record -> notifyService.doNotify(record));
            logger.info("time:{},通知完成", now.toString());
        } else {
            logger.info("time:{},没有须通知的记录", now.toString());
        }
    }
}
