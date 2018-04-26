-- 更新版本到3, v2.5.0上次收获门店  -2017/3/20
--删除数据库
delete from express_info;

--创建数据库
create table if not exists express_info
(
     --快递id
     express_id varchar not null auto_increment primary key,
     --快递代领号
     express_lead_num varchar,
     --快递送达时间
     delivery_time varchar,
     --快递送达地点
     delivery_site varchar,
     --快递代领报酬
     express_lead_reward varchar,
     --收件人姓名
     receiver_name varchar,
     --收件人头像
     receiver_head_pic varchar,
     --收件人手机号码
     receiver_phone_number varchar,
     --快递代领备注
     express_lead_remark varchar
);






