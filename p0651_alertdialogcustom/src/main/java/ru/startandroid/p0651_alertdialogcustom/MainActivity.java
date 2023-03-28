package ru.startandroid.p0651_alertdialogcustom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int DIALOG = 1;
    private int btn;
    LinearLayout layout;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private TextView tvView;
    private List<TextView> textViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewList = new ArrayList<>(10);
    }

    public void onClick(View v) {
        btn = v.getId();
        showDialog(DIALOG);
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Custom dialog");
        layout = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dialog, null);
        builder.setView(layout);
        tvView = (TextView) layout.findViewById(R.id.tvCount);
        return builder.create();
    }

    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if (id == DIALOG) {

            TextView tvTime = (TextView) dialog.getWindow().findViewById(R.id.tvTime);
            tvTime.setText(dateFormat.format(new Date(System.currentTimeMillis())));
            if (btn == R.id.btnAdd) {
                TextView tv = new TextView(this);
                layout.addView(tv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tv.setText("TextView: " + (textViewList.size() + 1));
                textViewList.add(tv);
            } else {
                if (textViewList.size() > 0) {
                    TextView tv = textViewList.get(textViewList.size() - 1);
                    layout.removeView(tv);
                    textViewList.remove(tv);
                }
            }
            tvView.setText("Кол-во TextView " + textViewList.size());
        }
    }
}