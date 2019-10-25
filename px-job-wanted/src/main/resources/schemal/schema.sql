drop table job_wanted;

create table job_wanted(
    `job_wanted_id` bigint not null primary key comment '主键',
    `company_id` bigint comment '公司id',
    `company_name` varchar(20) comment '公司名称',
    `job_id` bigint comment '工作id',
    `qa_id` bigint comment '问题',
    `summary` longtext not null comment '总结',
    `create_date` timestamp not null default current_timestamp comment '创建时间',
    `create_user_id` bigint not null comment '创建用户',
    `state` int not null comment '状态'
)engine = InnoDB auto_increment=1 charset=utf8 comment = '求职记录表';

create table qa(
    `qa_id` bigint not null primary key comment '主键',
    `qa_question` varchar(50) not null comment '问题',
    `qa_answer` longtext not null comment '答案',
    `qa_state` int  not null comment '状态'
)engine = InnoDB auto_increment=1 charset=utf8 comment = '问题记录表';