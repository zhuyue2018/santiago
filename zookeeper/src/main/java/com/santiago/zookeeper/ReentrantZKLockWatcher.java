package com.santiago.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ReentrantZKLockWatcher implements Watcher {
    private CountDownLatch latch;

    public ReentrantZKLockWatcher(CountDownLatch latch) {
        this.latch = latch;
    }

    public void process(WatchedEvent event) {
        if (Watcher.Event.EventType.NodeDeleted == event.getType()) {
            latch.countDown();
        }
    }
}