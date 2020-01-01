package com.santiago.zookeeper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReentrantZKLockTester {
    private static final Logger logger = LoggerFactory.getLogger(ReentrantZKLockTester.class);

    public static void main(String[] args) {
        try {
            logger.info("thread:{},try lock", Thread.currentThread().getId());
            DistributedLockUtil.lock("test");
            logger.info("thread:{},locked", Thread.currentThread().getId());
            try {
                logger.info("thread:{},exec", Thread.currentThread().getId());
                Thread.sleep(5 * 1000);
                logger.info("thread:{},exec finish", Thread.currentThread().getId());
            } finally {
                DistributedLockUtil.unLock("test");
                logger.info("thread:{},unlock", Thread.currentThread().getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}