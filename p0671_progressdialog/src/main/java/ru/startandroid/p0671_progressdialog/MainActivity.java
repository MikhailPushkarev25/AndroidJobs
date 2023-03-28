package ru.startandroid.p0671_progressdialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pgDialog;
    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDefault:
                pgDialog = new ProgressDialog(this);
                pgDialog.setTitle("Title");
                pgDialog.setMessage("Message");
                pgDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                pgDialog.show();
                break;
            case R.id.btnHoriz:
                pgDialog = new ProgressDialog(this);
                pgDialog.setTitle("Title");
                pgDialog.setMessage("Message");
                pgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pgDialog.setMax(2148);
                pgDialog.setIndeterminate(true);
                pgDialog.show();
                h = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        pgDialog.setIndeterminate(false);
                        if (pgDialog.getProgress() < pgDialog.getMax()) {
                            pgDialog.incrementProgressBy(50);
                            pgDialog.incrementSecondaryProgressBy(75);
                            h.sendEmptyMessageDelayed(0, 100);
                        } else {
                            pgDialog.dismiss();
                        }
                    }
                };
                h.sendEmptyMessageDelayed(0, 6000);
                break;
            default:
                break;
        }
    }
}