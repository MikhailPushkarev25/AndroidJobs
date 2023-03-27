package ru.startandroid.p0641_alertdialogitemsmulti;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int DIALOG_ITEMS = 1;
    private static final int DIALOG_CURSOR = 3;

    private DB db;
    private Cursor cursor;

    private String[] data = {"one", "two", "three", "four", "five"};
    private boolean[] check = {false, true, true, false, false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();
        cursor = db.getAll();
        startManagingCursor(cursor);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
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
                builder.setMultiChoiceItems(data, check, multiChoiceClickListenerItems);
                break;
            case DIALOG_CURSOR:
                builder.setTitle(R.string.cursor);
                builder.setMultiChoiceItems(cursor, DB.COLUMN_CHECK, DB.COLUMN_TEXT, multiChoiceClickListenerCursor);
                break;
        }
        builder.setPositiveButton(R.string.ok, clickListener);
        return builder.create();
    }

    DialogInterface.OnMultiChoiceClickListener multiChoiceClickListenerItems = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
            Log.d("myLog", i + " i " + b + " b ");
        }
    };

    DialogInterface.OnMultiChoiceClickListener multiChoiceClickListenerCursor = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
            Log.d("myLog", i + " i " + b + " b ");
            db.update(i, b);
            cursor.requery();
        }
    };

    DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            SparseBooleanArray array = ((AlertDialog) dialogInterface).getListView().getCheckedItemPositions();
            for (int j = 0; j < array.size(); j++) {
                int key = array.keyAt(j);
                if (array.get(key)) {
                    Log.d("qwe", "checked: " + key);
                }
            }
        }
    };

    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}