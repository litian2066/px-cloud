package com.px.transaction;

import com.alibaba.fastjson.JSONObject;
import com.px.netty.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地事务管理者
 * 将所有的事务操作都放在这个类里面
 */
@Component
public class TransactionManage {

    // 维护一个ThreadLocal，存储groupID， 主要是标注了@MyTransaction注解的一些个中间服务
    // 方便它们拿到groupId
    private static ThreadLocal<String> CURRENT = new ThreadLocal<>();

    // 维护一个map，存放事务， 方便netty客户端获取
    private static final Map<String, Map<String, Transaction>> GROUP_MAP = new HashMap<>();

    // 维护一个threadLocal，存放本地事务对象，方便切面拿到connection之前传入
    private static ThreadLocal<Transaction> CURREN_TRANSACTION = new ThreadLocal<>();

    // 注入NettyClient，因为这儿的NettyClient是静态不能直接注入，这儿使用set注入
    private static NettyClient nettyClient;

    @Autowired
    public void setNettyClient(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    // 创建事务组
    public static String createGroup() {
        // 使用uuid生成事务组id groupid
        String id = UUID.randomUUID().toString();
        // 要通过netty发送，所以使用JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId", id);
        jsonObject.put("command", "create"); // 创建事务组的命令
        // threadlocal放入事务组id
        CURRENT.set(id);
        // 将事务放进GROUP_Map
        GROUP_MAP.put(id, new HashMap<>());
        // 发送给Netty
        nettyClient.send(jsonObject);
        return id;
    }

    // 创建事务对象
    public static Transaction createTransaction(String groupId) {
        // 事务id, 通过uuid来实现
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, groupId);
        // 将事务放进GROUP_MAP里面
        if (GROUP_MAP.get(groupId) == null) {
            // 会出现空的现象，假设订单服务这儿是start状态，而库存就不是，所以也会创建事务，这儿就会为空
            GROUP_MAP.put(groupId, new HashMap<>());
        }
        GROUP_MAP.get(groupId).put(transactionId, transaction);
        // 在创建本地事务对象之后将事务对象放入本地线程之中
        CURREN_TRANSACTION.set(transaction);
        return transaction;
    }

    // 提交本地事务到事务管理者服务端
    public static void commitTransaction(Transaction transaction, boolean isEnd, TransactionType transactionType) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId", transaction.getGroupId());
        jsonObject.put("transactionId", transaction.getTransactionId());
        jsonObject.put("transactionType", transaction.getTransactionType());
        jsonObject.put("command", "add");
        jsonObject.put("isEnd", isEnd);
        nettyClient.send(jsonObject);
        System.out.println("执行了添加事务");

    }

    /**
     * 通过事务组id和事务id拿到事务对象
     * @param groupId
     * @param transactionId
     * @return
     */
    public static Transaction getTransactionById(String groupId, String transactionId) {
        return GROUP_MAP.get(groupId).get(transactionId);
    }

    // 拿到事务组的id
    public static String getCurrent() {
        return CURRENT.get();
    }

    // set事务组id到本地线程中
    public static void setCurrent(String groupId) {
        CURRENT.set(groupId);
    }

    /**
     * 拿到当前线程中的事务对象，即本地事务
     * @return
     */
    public static Transaction getCurrentTransaction() {
        return CURREN_TRANSACTION.get();
    }


}
