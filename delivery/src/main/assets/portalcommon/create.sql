-- 用命令行创建数据库和表并初始化数据
-- sqlite3 portalcommon.db < portalcommon.sql
-- 用户表
create table express_info
(
    --快递id
    express_id varchar not null primary key,
    --快递代领号
    express_lead_num varchar,
    --快递送达时间
    delivery_time varchar,
    --快递送达地点
    delivery_site varchar,
    --快递代领报酬
    express_lead_reward varchar,
    --收件人姓名
    delivery_user_name varchar,
    --收件人手机号码
    delivery_phone_number varchar,
    --快递代领备注
    express_lead_remark varchar,
);

