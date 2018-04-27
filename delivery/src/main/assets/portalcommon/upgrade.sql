-- 更新版本到3, v2.5.0上次收获门店  -2017/3/20
--删除数据库
delete from express_info;

--创建数据库
create table if not exists express_info
(
    --快递id
    expressage_id varchar not null auto_increment primary key,
    --快递代领号
    expressage_lead_num varchar,
    --快递送达时间
    delivery_time varchar,
    --快递送达地点
    delivery_site varchar,
    --快递代领报酬
    expressage_lead_reward varchar,
    --收件人姓名
    delivery_user_name varchar,
    --收件人手机号码
    delivery_phone_number varchar,
    --快递代领备注
    expressage_lead_remark varchar,
    --快递状态
    expressage_status varchar
);






