package ru.startandroid.p0611_alertdialogprepare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String myLog = "myLog";

    private int DIALOG = 1;

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        showDialog(DIALOG);
    }
    protected Dialog onCreateDialog(int d) {
        Log.d(myLog, "onCreateDialog");
        if (d == DIALOG) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Текущее время");
            adb.setMessage(dateFormat.format(new Date(System.currentTimeMillis())));
            return adb.create();
        }
        return super.onCreateDialog(d);
    }

    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        Log.d(myLog, "onPrepareDialog");
        if (id == DIALOG) {
            ((AlertDialog) dialog).setMessage(dateFormat.format(new Date(System.currentTimeMillis())));
        }
    }
}