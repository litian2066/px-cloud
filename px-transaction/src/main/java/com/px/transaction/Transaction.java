package com.px.transaction;

import com.px.task.Task;

/**
 * 封装事务对象
 */
public class Transaction {
    // 事务id
    private String transactionId;
    // 事务组id
    private String groupId;
    // 枚举事务状态，是Commit还是Rollback
    private TransactionType transactionType;
    // 任务
    private Task task;

    public Transaction(String transactionId, String groupId) {
        this.transactionId = transactionId;
        this.groupId = groupId;
        // 构造的时候附带一个任务
        task = new Task();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
