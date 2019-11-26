package com.px.task;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 任务类
 * 在提交本地事务之前进行等待
 */
public class Task {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    // 等待
    public void waitTask() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 唤醒
    public void singalTask() {
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
