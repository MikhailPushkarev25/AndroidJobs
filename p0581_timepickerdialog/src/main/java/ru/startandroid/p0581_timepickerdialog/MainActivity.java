package ru.startandroid.p0581_timepickerdialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private static final int DIALOG_TIME = 1;
    private int MYHOUR = 14;
    private int MYMINUTE = 35;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tvText);
    }

    public void onClick(View view) {
        showDialog(DIALOG_TIME);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tg = new TimePickerDialog(this, myCallBack, MYHOUR, MYMINUTE, true);
            return tg;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            MYHOUR = i;
            MYMINUTE = i1;
            tvText.setText("Time is: " + MYHOUR + " hours " + MYMINUTE + " minutes");
        }
    };

}