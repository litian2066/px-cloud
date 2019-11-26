package com.px.aspect;

import com.px.annotation.MyTransaction;
import com.px.transaction.Transaction;
import com.px.transaction.TransactionManage;
import com.px.transaction.TransactionType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 分布式事务切面
 */
@Order(value = 10000) // 数字越大优先级越低，让spring的注解先执行。
@Aspect
@Component
public class TransactionAspect {

    @Around("@annotation(com.px.annotation.MyTransaction)")
    public void invoke(ProceedingJoinPoint proceedingJoinPoint) {
        // 首先拿到方法对象
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 拿到我们的注解对象
        MyTransaction annotation = signature.getMethod().getAnnotation(MyTransaction.class);
        // 事务组id
        String group = "";
        if (annotation.isStart()) {
            // 创建事务组
            group = TransactionManage.createGroup();
        } else {
            // 拿到当前事务组的id, 通过ThreadLocal拿到
            // 这样写可能会拿到null
            group = TransactionManage.getCurrent();
        }
        // 创建事务对象 这儿创建的是本地事务对象
        Transaction transaction = TransactionManage.createTransaction(group);
        // 执行本地逻辑
        try {
            // Spring会帮我们执行mysql的逻辑，让spring告诉我们失败或者成功
            // 但是这儿会有一个优先级的问题，因为我们要让spring的@Transactional先执行
            // 然后再调用到我们的Connection在commit前等待，所以我们的注解需要后执行，所以添加一个Order注解
            Object proceed = proceedingJoinPoint.proceed();
            // 提交本地事务状态 COMMIT操作
            TransactionManage.commitTransaction(transaction, annotation.isEnd(), TransactionType.COMMIT);
        } catch (Throwable throwable) {
            // ROLLBACK操作
            TransactionManage.commitTransaction(transaction, annotation.isEnd(), TransactionType.ROLLBACK);
            throwable.printStackTrace();
        }

        // 如果有@MyTransaction标注，但是没有start也没有end，
        // 比如在服务中间的一个服务，如什么积分服务之类的，说明
        // 她肯定存在分布式事务里面，同时存在这个group里面，那么我们这儿
        // 怎么拿到group的id？所以在生成group那边的类里面维护一个保存groupId的ThredLocal

    }
}
