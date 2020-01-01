package com.santiago.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ReentrantZKLockMgr {

    private ZooKeeper zooKeeper = null;
    private static byte[] data = new byte[0];

    public ReentrantZKLockMgr(String connectString, int sessionTimeout) throws Exception {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new SessionWatcher());
    }

    public ReentrantZKLock makeLock(String categoryPath) throws Exception {
        Stat stat = zooKeeper.exists(categoryPath, false);
        if (stat == null) {
            zooKeeper.create(categoryPath, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        return new ReentrantZKLock(zooKeeper, categoryPath);
    }

    public void close() throws Exception {
        zooKeeper.close();
    }
}

class SessionWatcher implements Watcher {

    public void process(WatchedEvent event) {

        System.out.println("State=" + event.getState());
        System.out.println("Type=" + event.getType());
    }

}