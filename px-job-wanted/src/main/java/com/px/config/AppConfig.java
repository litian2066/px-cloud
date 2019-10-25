package com.px.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.px.mapper")
public class AppConfig {

//    @Primary
//    @Bean // (name = "dddddDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource(){
//        log.info("开始配置数据源信息--------");
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean// (name = "dddddSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try{
//            // Resource resource = resolver.getResource("classpath:mapper/*.xml");
//            // bean.setMapperLocations();
//            bean.setMapperLocations(new Resource[]{resolver.getResource("classpath:mapper/*.xml")});
//            return bean.getObject();
//        }catch(Exception e){
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//
//    }
//
//    @Bean// (name = "dddddSqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(/*@Qualifier("sqlSessionFactory")*/SqlSessionFactory sqlSessionFactory){
//        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
//        return template;
//    }
}
