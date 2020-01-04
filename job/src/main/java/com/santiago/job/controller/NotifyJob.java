package com.santiago.job.controller;


import com.santiago.api.NotifyApi;
import com.santiago.job.service.JobRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
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
    JobRecordService jobRecordService;
    @Autowired
    NotifyApi notifyApi;
    /**
     * 每30s执行一次。
     * 查询60分钟内状态为init，通知次数小于等于10的记录，并执行通知。
     */
    @Scheduled(cron = "0/30 * * * * ?")
    private void notifyPayedJob() {
        System.out.println(notifyApi.batchNotify());
//        JobRecord jobRecord = new JobRecord();
//        jobRecord.setJobCode("000001");
//        jobRecord.setJobName("商户通知");
//        jobRecord.setGmtCreate(new Date());
//        jobRecordService.insert(jobRecord);
    }
}
