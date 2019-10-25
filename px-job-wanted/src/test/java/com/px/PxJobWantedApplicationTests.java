package com.px;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.px.entity.User;
import com.px.mapper.UserMapper;
import com.px.service.JobWantedService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PxJobWantedApplicationTests {

    @Autowired
    private JobWantedService jobWantedService;


    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<User> users = userMapper.selectList(null);
        // 使用断言判断个数是否正确
        Assert.assertEquals(5, users.size());
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("向前");
        user.setAge(44);
        user.setManagerId(111l);
        user.setCreateTime(new Date());
        user.setEmail("xq@baomidou.com");
        user.setRemark("我是一条备注信息");
        // 返回影响记录数
        int row = userMapper.insert(user);

        System.out.println("影响记录数：" + row);
    }

    @Test
    public void selectById() {
        User user = this.userMapper.selectById(111L);
        System.out.println(user);
    }

    @Test
    public void selectBatchIds() {
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(111L, 222L));
        users.forEach(System.out::println);
    }

    /**
     * 字段参数查询
     */
    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("manager_id", 111l);
        List<User> users = this.userMapper.selectByMap(columnMap);
        users.forEach(System.out::println);
    }

    /**
     * name like '%雨%' and age < 40
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 或者使用另一种方式
        // QueryWrapper<User> query = Wrappers.query();
        // key是字段名
        queryWrapper.like("name", "雨").le("age", 40);
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void selectByWrapper1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or()
                .ge("age", 25).orderByDesc("age")
                .orderByAsc("user_id");
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time, '%Y-%m-%d') = {0}", "2019-03-11")
                .inSql("manager_id", "select user_id from user where name like '王%'");
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // and里面是函数式接口
        queryWrapper.likeRight("name", "王")
                .and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // and里面是函数式接口
        queryWrapper.likeRight("name", "王")
                .or(mq -> mq.lt("age", 40)
                        .gt("age", 20)
                        .isNotNull("email"));
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(mq -> mq.lt("age", 40).isNotNull("email"))
                .likeRight("name", "王");
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(31, 32, 34, 35));
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(31, 32, 34, 35)).last("limit 1");
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperSuper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id", "name", "email").in("age", Arrays.asList(31, 32, 34, 35));
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperSuper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class,
                c -> !c.getColumn().equals("create_time") && !c.getColumn().equals("manager_id"))
                .in("age", Arrays.asList(31, 32, 34, 35));
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperCondition() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String name = "王天风";
        String email = "";
        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);
        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectEntity() {
        User whereUser = new User();
        whereUser.setName("王天风");
        whereUser.setAge(25);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(whereUser);

        List<User> users = this.userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

}
