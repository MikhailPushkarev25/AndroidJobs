package ru.startandroid.p0631_alertdialogitemssingle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final int DIALOG_ITEMS = 1;
    private static final int DIALOG_ADAPTER = 2;
    private static final int DIALOG_CURSOR = 3;

    private static final String[] data = {"one", "two", "three", "four", "five"};

    private DB db;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.All();
        startManagingCursor(cursor);
    }

    public void onClick(View view) {
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

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_ITEMS:
                builder.setTitle(R.string.items);
                builder.setSingleChoiceItems(data, -1, myClickListener);
                break;
            case DIALOG_ADAPTER:
                builder.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.select_dialog_singlechoice, data);
                builder.setSingleChoiceItems(adapter, -1, myClickListener);
                break;
            case DIALOG_CURSOR:
                builder.setTitle(R.string.cursor);
                builder.setSingleChoiceItems(cursor, -1, DB.COLUMN_TXT, myClickListener);
                break;
        }
        builder.setPositiveButton(R.string.ok, myClickListener);
        return builder.create();
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            ListView lv = ((AlertDialog) dialogInterface).getListView();
            if (i == Dialog.BUTTON_POSITIVE) {
                Log.d("mylog", "pos = " + lv.getCheckedItemPosition());
            } else
                Log.d("mylog", "i = " + i);
        }
    };

    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}