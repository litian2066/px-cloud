package com.px.aspect;

import com.px.connection.MyConnection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

// 配置连接切面，截取spring获取的DataSource连接，然后返回我们自定义的连接对象
@Aspect
@Component
public class ConnectionAspect {

    // 代理Datasource的connection
    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            // 拿到spring的connection对象
            Connection connection = (Connection) proceedingJoinPoint.proceed();
            // 最终spring的connection对象是我们自己的connection对象
            return new MyConnection(connection);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
