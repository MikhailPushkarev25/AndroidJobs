package ru.startandroid.p0701_saveinstancestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MY_LOG = "myLog";
    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(MY_LOG, "onCreate");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(MY_LOG, "onDestroy");
    }

    protected void onPause() {
        super.onPause();
        Log.d(MY_LOG, "onPause");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(MY_LOG, "onRestart");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cnt = savedInstanceState.getInt("Count");
        Log.d(MY_LOG, "onRestoreInstanceState");
    }
    protected void onResume() {
        super.onResume();
        Log.d(MY_LOG, "onResume");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Count", cnt);
        Log.d(MY_LOG, "onSaveInstanceState");
    }

    protected void onStart() {
        super.onStart();
        Log.d(MY_LOG, "onStart");
    }

    protected void onStop() {
        super.onStop();
        Log.d(MY_LOG, "onStop");
    }

    public void onClick(View view) {
        Toast.makeText(this, "Count = " + ++cnt, Toast.LENGTH_SHORT).show();
    }
}