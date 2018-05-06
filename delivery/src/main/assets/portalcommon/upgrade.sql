-- 更新版本到3, v2.5.0上次收获门店  -2017/3/20
--删除数据库
delete from express_info;

--创建数据库
create table if not exists express_info
(
    expressage_id int auto_increment primary key,
    expressage_lead_num varchar,
    delivery_time varchar,
    delivery_site varchar,
    expressage_desc varchar,
    expressage_lead_reward varchar,
    expressage_lead_type varchar,
    delivery_user_name varchar,
    delivery_phone_number varchar,
    expressage_lead_remark varchar,
    expressage_status varchar,
    expressage_type varchar
);






