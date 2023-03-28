package ru.startandroid.p0661_alertdialogoperations;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final int DIALOG = 1;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.d("myLog", "Create");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Title");
            builder.setMessage("Message");
            builder.setPositiveButton("OK", null);
            dialog = builder.create();

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Log.d("myLog", "Show");
                }
            });

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Log.d("myLog", "Cancel");
                }
            });

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    Log.d("myLog", "Dismiss");
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);
    }

    public void method1() {
        removeDialog(DIALOG);
    }

    public void method2() {
        showDialog(DIALOG);
    }

    public void onClick(View v) {
        showDialog(DIALOG);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        }, 2000);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                method2();
            }
        }, 4000);
    }
}