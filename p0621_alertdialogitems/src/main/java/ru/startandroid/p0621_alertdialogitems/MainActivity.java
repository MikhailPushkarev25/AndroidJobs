package ru.startandroid.p0621_alertdialogitems;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity {

    private final String mylog = "myLog";

    private final int DIALOG_ITEMS = 1;
    private final int DIALOG_ADAPTER = 2;
    private final int DIALOG_CURSOR = 3;

    int cnt = 0;

    DB db;
    Cursor cursor;

    private final String[] data = {"one", "two", "three", "four"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onClick(View view) {
        changeCount();
        switch (view.getId()) {
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnAdapter:
                showDialog(DIALOG_ADAPTER);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setItems(data, myClickListener);
                break;
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.select_dialog_item, data);
                adb.setAdapter(adapter, myClickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setCursor(cursor, myClickListener, DB.COLUMN_TXT);
                break;
        }
        return adb.create();
    }


    Dialog.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Log.d(mylog, "with = " + i);
        }
    };

    public void changeCount() {
        cnt++;
        data[3]  = String.valueOf(cnt);
        db.changeRec(4, String.valueOf(cnt));
        cursor.requery();
    }

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}