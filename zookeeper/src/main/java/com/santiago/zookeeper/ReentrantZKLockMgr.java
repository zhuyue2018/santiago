package com.santiago.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

public class ReentrantZKLockMgr {

    private ZooKeeper zooKeeper;

    public ReentrantZKLockMgr(String connectString, int sessionTimeout) throws Exception {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new SessionWatcher());
    }

    /**
     * 获取锁对象，没有则创建并返回
     * @param categoryPath
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public ReentrantZKLock makeLock(String categoryPath) throws KeeperException, InterruptedException {
        if (null == zooKeeper.exists(categoryPath, false)) {
            zooKeeper.create(categoryPath, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        return new ReentrantZKLock(zooKeeper, categoryPath);
    }


    public ReentrantZKLock getLock(String categoryPath) throws KeeperException, InterruptedException {
        return null == zooKeeper.exists(categoryPath, false) ? null : new ReentrantZKLock(zooKeeper, categoryPath);
    }

    public void close() throws Exception {
        zooKeeper.close();
    }
}

class SessionWatcher implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        System.out.println("State=" + event.getState());
        System.out.println("Type=" + event.getType());
    }

}