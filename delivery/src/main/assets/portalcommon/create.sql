-- 用命令行创建数据库和表并初始化数据
-- sqlite3 portalcommon.db < portalcommon.sql
-- 用户表
create table express_info
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

