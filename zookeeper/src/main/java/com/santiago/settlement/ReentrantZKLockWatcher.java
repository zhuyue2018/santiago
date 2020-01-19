package com.santiago.settlement;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ReentrantZKLockWatcher implements Watcher {
    private CountDownLatch latch;

    public ReentrantZKLockWatcher(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Watcher.Event.EventType.NodeDeleted == watchedEvent.getType()) {
            latch.countDown();
        }
    }

}