package com.wuli.delivery.portal.bean.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.db.DBHelper;
import com.wuli.delivery.AppConstants;
import com.wuli.delivery.portal.bean.Expressage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ziv
 * @date 2018/5/6
 */

public class ExpressageDao {


    private ExpressageDao() {

    }

    public static void save(Expressage expressage) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("expressage_lead_num", expressage.getExpressageLeadNum());
        contentValues.put("delivery_time", expressage.getDeliveryTime());
        contentValues.put("delivery_site", expressage.getDeliverySite());
        contentValues.put("expressage_desc", expressage.getExpressageDesc());
        contentValues.put("expressage_lead_reward", expressage.getExpressageLeadReward());
        contentValues.put("expressage_lead_type", expressage.getExpressageLeadType());
        contentValues.put("delivery_user_name", expressage.getDeliveryUserName());
        contentValues.put("delivery_phone_number", expressage.getDeliveryPhoneNumber());
        contentValues.put("expressage_lead_remark", expressage.getExpressageLeadRemark());
        contentValues.put("expressage_status", expressage.getExpressageStatus());
        contentValues.put("expressage_type", expressage.getExpressageType());

        SQLiteDatabase db = DBHelper.getInstance().getDB(AppConstants.DBNAME_COMMON);

        db.insert("express_info", null, contentValues);
    }

    public static List<Expressage> getAllExpressageList() {
        SQLiteDatabase db = DBHelper.getInstance().getDB(AppConstants.DBNAME_COMMON);
        String sql = "select * from express_info";
        List<Expressage> expressageList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, new String[]{});
            while (cursor.moveToNext()) {
                String expressageLeadNum = cursor.getString(cursor.getColumnIndex("expressage_lead_num"));
                String deliveryTime = cursor.getString(cursor.getColumnIndex("delivery_time"));
                String delivertSite = cursor.getString(cursor.getColumnIndex("delivery_site"));
                String expressageDesc = cursor.getString(cursor.getColumnIndex("expressage_desc"));
                String expressageLeadReward = cursor.getString(cursor.getColumnIndex("expressage_lead_reward"));
                String expressageLeadType = cursor.getString(cursor.getColumnIndex("expressage_lead_type"));
                String deliveryUserName = cursor.getString(cursor.getColumnIndex("delivery_user_name"));
                String deliveryPhoneNumber = cursor.getString(cursor.getColumnIndex("delivery_phone_number"));
                String expressageLeadRemark = cursor.getString(cursor.getColumnIndex("expressage_lead_remark"));
                String expressageLeadStatus = cursor.getString(cursor.getColumnIndex("expressage_status"));
                String expressageType = cursor.getString(cursor.getColumnIndex("expressage_type"));

                expressageList.add(new Expressage.Builder()
                        .expressageLeadNum(expressageLeadNum)
                        .deliveryTime(deliveryTime)
                        .deliverySite(delivertSite)
                        .expressageDesc(expressageDesc)
                        .expressageLeadReward(expressageLeadReward)
                        .expressageLeadType(expressageLeadType)
                        .deliveryUserName(deliveryUserName)
                        .deliveryPhoneNumber(deliveryPhoneNumber)
                        .expressageLeadRemark(expressageLeadRemark)
                        .expressageStatus(expressageLeadStatus)
                        .expressageType(expressageType)
                        .build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return expressageList;
    }

    /**
     * 更具leadType查找包裹列表
     *
     * @param leadType
     * @return
     */
    public static List<Expressage> getExpressageListByLeadType(String leadType) {

        SQLiteDatabase db = DBHelper.getInstance().getDB(AppConstants.DBNAME_COMMON);
        String sql = "select * from express_info where expressage_lead_type = ?";
        List<Expressage> expressageList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, new String[]{leadType});
            while (cursor.moveToNext()) {
                String expressageLeadNum = cursor.getString(cursor.getColumnIndex("expressage_lead_num"));
                String deliveryTime = cursor.getString(cursor.getColumnIndex("delivery_time"));
                String delivertSite = cursor.getString(cursor.getColumnIndex("delivery_site"));
                String expressageDesc = cursor.getString(cursor.getColumnIndex("expressage_desc"));
                String expressageLeadReward = cursor.getString(cursor.getColumnIndex("expressage_lead_reward"));
                String expressageLeadType = cursor.getString(cursor.getColumnIndex("expressage_lead_type"));
                String deliveryUserName = cursor.getString(cursor.getColumnIndex("delivery_user_name"));
                String deliveryPhoneNumber = cursor.getString(cursor.getColumnIndex("delivery_phone_number"));
                String expressageLeadRemark = cursor.getString(cursor.getColumnIndex("expressage_lead_remark"));
                String expressageLeadStatus = cursor.getString(cursor.getColumnIndex("expressage_status"));
                String expressageType = cursor.getString(cursor.getColumnIndex("expressage_type"));

                expressageList.add(new Expressage.Builder()
                        .expressageLeadNum(expressageLeadNum)
                        .deliveryTime(deliveryTime)
                        .deliverySite(delivertSite)
                        .expressageDesc(expressageDesc)
                        .expressageLeadReward(expressageLeadReward)
                        .expressageLeadType(expressageLeadType)
                        .deliveryUserName(deliveryUserName)
                        .deliveryPhoneNumber(deliveryPhoneNumber)
                        .expressageLeadRemark(expressageLeadRemark)
                        .expressageStatus(expressageLeadStatus)
                        .expressageType(expressageType)
                        .build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return expressageList;

    }

}
