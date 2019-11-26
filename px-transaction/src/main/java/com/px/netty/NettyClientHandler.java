package com.px.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.px.transaction.Transaction;
import com.px.transaction.TransactionManage;
import com.px.transaction.TransactionType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * netty客户端
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接受数据:" + msg.toString());
        JSONObject jsonObject = JSON.parseObject((String) msg);

        String groupId = jsonObject.getString("groupId");
        String command = jsonObject.getString("command");
        String transactionId = jsonObject.getString("transactionId");

        System.out.println("command: " + command);
        // 对事务进行操作
        // 对指定事务放行，怎么拿到指定事务呢？所以我们需要根据事务id去拿到，那么我们需要维护一个map
        // 在本地事务管理者里面，维护事务组的map
        // 拿到通知的事务对象
        Transaction transaction = TransactionManage.getTransactionById(groupId, transactionId);
        // 有可能订单微服务和库存微服务会在一个事务组里面，肯定会通知两次，
        // 订单本地可能接受库存微服务的事务，所以可能为空
        if (transaction != null) {
            if ("rollback".equals(command)) {
                // 回滚
                transaction.setTransactionType(TransactionType.ROLLBACK);
            } else {
                // 提交
                transaction.setTransactionType(TransactionType.COMMIT);
            }
        }
    }

    public synchronized Object call(JSONObject data) throws Exception {
        context.writeAndFlush(data.toJSONString()).channel().newPromise();
        return null;
    }
}
