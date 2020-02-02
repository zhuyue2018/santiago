//package com.santiago.core.config;
//
//import com.baidu.fsg.uid.impl.CachedUidGenerator;
//import com.baidu.fsg.uid.impl.DefaultUidGenerator;
//import com.santiago.core.service.impl.DisposableWorkerIdAssigner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//@Component
//public class BaiduUid {
//
//    @Autowired
//    private DisposableWorkerIdAssigner disposableWorkerIdAssigner;
//
//    @Bean(value = "defaultUidGenerator")
//    public DefaultUidGenerator initDefaultUid() {
//        DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
//        defaultUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
//        defaultUidGenerator.setTimeBits(29);
//        defaultUidGenerator.setWorkerBits(21);
//        defaultUidGenerator.setSeqBits(13);
//        defaultUidGenerator.setEpochStr(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        return defaultUidGenerator;￿
//    }
//
//    @Bean(value = "cachedUidGenerator")
//    public CachedUidGenerator initCachedUidGenerator() {
//        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
//        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
//        return cachedUidGenerator;
//    }
//
//}