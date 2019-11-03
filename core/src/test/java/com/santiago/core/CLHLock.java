package com.santiago.core;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock {

    private static class CLHNode {
        String name;
        public CLHNode(String name) {
            this.name = name;
        }
        volatile boolean active = true;
    }

    private volatile CLHNode tail = null;
    private ThreadLocal<CLHNode> currentThreadNode = new ThreadLocal<>();
    private static final AtomicReferenceFieldUpdater UPDATER = AtomicReferenceFieldUpdater
            .newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock(String name) {
        CLHNode cNode = currentThreadNode.get();
        if (cNode == null) {
            cNode = new CLHNode(name);
            currentThreadNode.set(cNode);
        }
        CLHNode predecessor = (CLHNode) UPDATER.getAndSet(this, cNode);
        if (predecessor != null) {
            System.out.println("当前节点："+cNode.name+",设置前驱节点:" + predecessor.name);
            while (predecessor.active) {
            }
        }
    }

    public void unlock() {
        CLHNode cNode = currentThreadNode.get();
        if (cNode == null || !cNode.active) {
            return;
        }
        currentThreadNode.remove();
        if (!UPDATER.compareAndSet(this, cNode, null)) {
            cNode.active = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CLHLock lock = new CLHLock();
        for (int i = 1; i <= 10; i++) {
            new Thread(generateTask(lock, "node:" + i)).start();
        }

    }

    private static Runnable generateTask(final CLHLock lock, final String name) {
        return () -> {
            lock.lock(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            System.out.println(String.format("Thread %s Completed", name));
            lock.unlock();
        };
    }
}
