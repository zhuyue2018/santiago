package com.santiago.zookeeper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReentrantZKLockTester implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ReentrantZKLockTester.class);

    private static String path = "/test2";
    private static ReentrantZKLockMgr lockmgr;
    static {
      String connectString = "127.0.0.1:2181";
      int sessionTimeout = 5000;
      try {
        lockmgr = new ReentrantZKLockMgr(connectString, sessionTimeout);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public void run() {
        try {
            ReentrantZKLock lock = lockmgr.makeLock(path);
            logger.info("thread:{},try lock", Thread.currentThread().getId());
            lock.lock();
            logger.info("thread:{},locked", Thread.currentThread().getId());
            try {
                logger.info("thread:{},exec", Thread.currentThread().getId());
                Thread.sleep(5 * 1000);
                logger.info("thread:{},exec finish", Thread.currentThread().getId());
            } finally {
                lock.unlock();
                logger.info("thread:{},unlock", Thread.currentThread().getId());
            }
            lockmgr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 2; i++) {
//            new Thread(new ReentrantZKLockTester()).start();
//        }
        try {
            ReentrantZKLock lock = lockmgr.makeLock(path);
            lock.lock();
            try {
                Thread.sleep(5 * 1000);
                System.out.println(Thread.currentThread().getId());
            } finally {
                lock.unlock();
            }
            lockmgr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}