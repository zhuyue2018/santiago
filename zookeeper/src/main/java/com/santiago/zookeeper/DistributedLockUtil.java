package com.santiago.zookeeper;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Random;

public class DistributedLockUtil {
    public DistributedLockUtil() {
    }

    private static final CuratorFramework client;

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //创建Curator客户端
        client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();
    }

    public static boolean lock(String methodCode) {
        InterProcessMutex lock = new InterProcessMutex(client,"/"+methodCode);
        try {
            lock.acquire();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println(Thread.currentThread().getName() + "抢锁异常");
            return false;
        }
    }

    public static boolean unLock(String methodCode) {
        InterProcessMutex lock = new InterProcessMutex(client,"/"+methodCode);
        try {
            lock.release();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(DistributedLockUtil.lock("methon01"));
        Thread.sleep(1000);
        System.out.println(DistributedLockUtil.lock("methon01"));
        Thread.sleep(1000);
        System.out.println(DistributedLockUtil.unLock("methon01"));
        Thread.sleep(1000);
        System.out.println(DistributedLockUtil.lock("methon01"));

    }
}
