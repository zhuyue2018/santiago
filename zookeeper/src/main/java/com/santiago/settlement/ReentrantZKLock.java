package com.santiago.settlement;

import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReentrantZKLock {

    private static final Logger LOG = LoggerFactory.getLogger(ReentrantZKLock.class);

    private static byte[] data = new byte[0];
    private static String lockNode = "lock_";


    private ZooKeeper zooKeeper;
    private String categoryPath;
    private String nodePath;
    private Long watchTimeout = 5000L;

    private CountDownLatch latch = new CountDownLatch(1);

    public ReentrantZKLock(ZooKeeper zooKeeper, String categoryPath) {
        this.zooKeeper = zooKeeper;
        this.categoryPath = categoryPath;
    }

    public boolean lock() {
        try {
            String rawNodePath = categoryPath + "/" + lockNode;
            // 创建临时顺序节点
            nodePath = zooKeeper.create(rawNodePath, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("nodePath=" + nodePath);
            // 获取所有节点
            List<String> nodeIdList = zooKeeper.getChildren(categoryPath, true);
            System.out.println("nodeIdList=" + nodeIdList);
            // 获取最前的节点
            TreeSet<String> nodeIdSet = new TreeSet<String>(nodeIdList);
            String firstNodeId = nodeIdSet.first();
            System.out.println("firstNodeId=" + firstNodeId);

            String nodeId = nodePath.substring((categoryPath + "/").length());
            System.out.println("nodeId=" + nodeId);

            // 如果最前的节点为当前阶段，认为获取锁成功
            if (firstNodeId.equals(nodeId)) {
                return true;
            }

            String preNodeId = nodeIdSet.lower(nodeId);
            if (preNodeId != null) {
                System.out.println("nodeId=" + nodeId + ", preNodeId=" + preNodeId);
                String preNodePath = categoryPath + "/" + preNodeId;
                // 监听preNode的delete事件，删除或超时才会往后执行
                if (null != zooKeeper.exists(preNodePath, new ReentrantZKLockWatcher(latch))) {
                    if (latch.await(watchTimeout, TimeUnit.MILLISECONDS)) {
                        return true;
                    } else { // 获取锁超时
                        zooKeeper.delete(rawNodePath, -1);
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOG.error("lock " + categoryPath, e);
            return false;
        }
    }

    public void unlock() {
        try {
            zooKeeper.delete(nodePath, -1);
        } catch (Exception e) {
            LOG.error("unlock " + categoryPath, e);
        }
    }
}