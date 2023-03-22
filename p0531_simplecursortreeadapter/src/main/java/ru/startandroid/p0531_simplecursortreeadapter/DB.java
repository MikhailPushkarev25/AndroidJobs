package ru.startandroid.p0531_simplecursortreeadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DB {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    // имя таблицы компаний, поля и запрос создания
    private static final String COMPANY_TABLE = "company";
    public static final String COMPANY_COLUMN_ID = "_id";
    public static final String COMPANY_COLUMN_NAME = "name";
    private static final String COMPANY_TABLE_CREATE = "create table "
            + COMPANY_TABLE + "(" + COMPANY_COLUMN_ID
            + " integer primary key, " + COMPANY_COLUMN_NAME + " text" + ");";

    // имя таблицы телефонов, поля и запрос создания
    private static final String PHONE_TABLE = "phone";
    public static final String PHONE_COLUMN_ID = "_id";
    public static final String PHONE_COLUMN_NAME = "name";
    public static final String PHONE_COLUMN_COMPANY = "company";
    private static final String PHONE_TABLE_CREATE = "create table "
            + PHONE_TABLE + "(" + PHONE_COLUMN_ID
            + " integer primary key autoincrement, " + PHONE_COLUMN_NAME
            + " text, " + PHONE_COLUMN_COMPANY + " integer" + ");";

    private final Context ctx;
    private SQLiteDatabase db;

    public DB(Context ctx) {
        this.ctx = ctx;
    }

    public void open() {
        DBHelper helper = new DBHelper(ctx, DB_NAME, null, DB_VERSION);
        db = helper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public Cursor getCompanyData() {
        return db.query(COMPANY_TABLE, null, null, null, null, null, null);
    }

    public Cursor getPhoneData(long companyId) {
        return db.query(PHONE_TABLE, null, PHONE_COLUMN_COMPANY + " = " + companyId, null, null, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context,
                        @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            ContentValues cv = new ContentValues();

            String[] companies = new String[] {"HTC", "Sumsung", "LG"};

            db.execSQL(COMPANY_TABLE_CREATE);
            for (int i = 0; i < companies.length; i++) {
                cv.put(COMPANY_COLUMN_ID, i + 1);
                cv.put(COMPANY_COLUMN_NAME, companies[i]);
                db.insert(COMPANY_TABLE, null, cv);
            }

            String[] phonesHTC = new String[] { "Sensation", "Desire",
                    "Wildfire", "Hero" };
            String[] phonesSams = new String[] { "Galaxy S II", "Galaxy Nexus",
                    "Wave" };
            String[] phonesLG = new String[] { "Optimus", "Optimus Link",
                    "Optimus Black", "Optimus One" };

            db.execSQL(PHONE_TABLE_CREATE);
            cv.clear();
            for (String s : phonesHTC) {
                cv.put(PHONE_COLUMN_ID, 1);
                cv.put(PHONE_COLUMN_NAME, s);
                db.insert(PHONE_TABLE, null, cv);
            }

            for (String s : phonesSams) {
                cv.put(PHONE_COLUMN_ID, 2);
                cv.put(PHONE_COLUMN_NAME, s);
                db.insert(PHONE_TABLE, null, cv);
            }

            for (String s : phonesLG) {
                cv.put(PHONE_COLUMN_ID, 3);
                cv.put(PHONE_COLUMN_NAME, s);
                db.insert(PHONE_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
