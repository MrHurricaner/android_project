package com.wuli.delivery;

import android.app.Application;
import android.content.Context;

import com.app.db.AssetDBVersionManager;
import com.app.db.DBHelper;
import com.app.db.DBPathBuilder;
import com.app.util.FileUtil;

import java.io.File;


public class App extends Application {


    private final static String TAG = App.class.getSimpleName();

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mContext = getApplicationContext();

        AppConstants.init(mContext);
        // 初始化数据库
        DBHelper.getInstance().init(mContext, new AssetDBVersionManager(mContext));

        DBHelper.getInstance().initDB(AppConstants.DBNAME_COMMON, 1, new MyDBPathBuilder(null));
    }

    class MyDBPathBuilder implements DBPathBuilder {

        private String userID;

        public MyDBPathBuilder(String userID) {
            this.userID = userID;
        }

        @Override
        public String getDBPath(String name) {

            String dbPath = AppConstants.DB_DIR;

            FileUtil.checkAndCreateDirs(dbPath);

            if (AppConstants.DBNAME_COMMON.equals(name)) {
                File dbFile = new File(dbPath, AppConstants.DBNAME_COMMON_FILE);
                return dbFile.getPath();
            } else {
                dbPath = dbPath + "/" + userID + "/";
                FileUtil.checkAndCreateDirs(dbPath);
                return new File(dbPath, "portal_user.db").getPath();
            }
        }
    }

}
