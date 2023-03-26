package ru.startandroid.p0601_alertdialogsimple;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int DIALOG_EXIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        showDialog(DIALOG_EXIT);
    }

    protected Dialog onCreateDialog(int d) {
        if (d == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.exit);
            adb.setMessage(R.string.save_data);
            adb.setIcon(android.R.drawable.ic_dialog_info);
            adb.setPositiveButton(R.string.yes, myClickListener);
            adb.setNegativeButton(R.string.no, myClickListener);
            adb.setNeutralButton(R.string.cancle, myClickListener);
            return adb.create();
        }
        return super.onCreateDialog(d);
    }

    public void onBackPressed() {
            showDialog(DIALOG_EXIT);
    }

    Dialog.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                    case Dialog.BUTTON_POSITIVE:
                        saveData();
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        break;

        }
    }
    };

    public void saveData() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
    }
}