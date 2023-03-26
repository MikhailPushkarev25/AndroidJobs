package ru.startandroid.p0621_alertdialogitems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DB {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TXT = "text";

    private static final String DB_CREATE =
            "create table " + DB_TABLE + "(" +
            COLUMN_ID + " integer primary key," +
            COLUMN_TXT + " text" +
                    ");";

    private Context mctx;

    private DBHelper dbHelper;
    private SQLiteDatabase mdb;

    public DB(Context mctx) {
        this.mctx = mctx;
    }

    public void open() {
        dbHelper = new DBHelper(mctx, DB_NAME, null, DB_VERSION);
        mdb = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    public Cursor getAllData() {
        return mdb.query(DB_TABLE, null, null, null, null, null, null);
    }

    public void changeRec(int id, String text) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TXT, text);
        mdb.update(DB_TABLE, cv, COLUMN_ID + " = " + id, null);
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(@Nullable Context context,
                        @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE);

            ContentValues cv = new ContentValues();
            for (int i = 1; i < 5; i++) {
                cv.put(COLUMN_ID, i);
                cv.put(COLUMN_TXT, "some text " + i);
                sqLiteDatabase.insert(DB_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
