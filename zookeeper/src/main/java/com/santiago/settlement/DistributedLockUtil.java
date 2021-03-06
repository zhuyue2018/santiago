package com.santiago.settlement;

import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;


public class DistributedLockUtil implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(DistributedLockUtil.class);
    private String address = "127.0.0.1:2181";
    private int sessionTimeout = 5000;
    private DistributedLockUtil() {
    }

    private static ReentrantZKLockMgr lockmgr;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            DistributedLockUtil.lockmgr = new ReentrantZKLockMgr(this.address, this.sessionTimeout);
        } catch (Exception e) {
            logger.warn("分布式锁初始化异常", e);
        }
    }

    public static synchronized boolean lock(String categoryPath) throws Exception {
        if (null == lockmgr) {
            logger.warn("分布式锁未初始化");
            return false;
        }
        ReentrantZKLock lock = lockmgr.makeLock(categoryPath);
        return lock.lock();
    }

    public static synchronized boolean unLock(String categoryPath) throws KeeperException, InterruptedException {
        ReentrantZKLock lock = lockmgr.getLock(categoryPath);
        if (null == lock) {
            logger.info("锁不存在");
            return true;
        } else {
            lock.unlock();
            return true;
        }
    }


}
