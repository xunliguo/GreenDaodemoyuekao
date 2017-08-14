package coda.expamle.com.greendaodemoyuekao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import coda.expamle.com.greendaodemoyuekao.dao.DaoMaster;

/**
 * Created by ASUS-PC on 2017/7/4.
 */


    public class GreenDaoHelper {

        private static GreenDaoHelper greenDaoHelper;
//我修改代码了恩
        private DaoMaster.DevOpenHelper devOpenHelper;

        public GreenDaoHelper(Context context) {

            devOpenHelper = new DaoMaster.DevOpenHelper(context, "books.db", null);

        }

        public static synchronized GreenDaoHelper openHelper(Context context) {

            if (greenDaoHelper == null) {

                synchronized (GreenDaoHelper.class) {

                    if (greenDaoHelper == null) {

                        greenDaoHelper = new GreenDaoHelper(context);

                    }

                }

            }

            return greenDaoHelper;

        }

        public SQLiteDatabase getRead() {

            return devOpenHelper.getReadableDatabase();

        }

        public SQLiteDatabase gerWriter() {

            return devOpenHelper.getWritableDatabase();

        }

    }

