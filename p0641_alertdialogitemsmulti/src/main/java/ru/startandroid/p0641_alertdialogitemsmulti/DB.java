package ru.startandroid.p0641_alertdialogitemsmulti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DB {

    private static final String DB_NAME = "myDb";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "myTable";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHECK = "checked";
    public static final String COLUMN_TEXT = "text";

    public static final String CREATE_TABLE =
            "create table " + TABLE_NAME + "(" +
                    COLUMN_ID + " integer primary key, " +
                    COLUMN_CHECK + " integer, " +
                    COLUMN_TEXT + " text " +
                    ");";

    private Context ctx;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DB(Context ctx) {
        this.ctx = ctx;
    }

    public void open() {
        dbHelper = new DBHelper(ctx, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public Cursor getAll() {
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void update(int pos, boolean check) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHECK, (check) ? 1 : 0);
        db.update(TABLE_NAME, cv, COLUMN_ID + " = " + pos, null);
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
            sqLiteDatabase.execSQL(CREATE_TABLE);

            ContentValues cv = new ContentValues();

            for (int i = 0; i < 5; i++) {
                cv.put(COLUMN_ID, i);
                cv.put(COLUMN_CHECK, 0);
                cv.put(COLUMN_TEXT, "someText " + i);
                sqLiteDatabase.insert(TABLE_NAME, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
