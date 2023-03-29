package ru.startandroid.p0691_parcelable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends Activity {

    private static final String myLogs = "myLogs";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Log.d(myLogs, "getParcenleExtra");
        MyObject myObject = getIntent().getParcelableExtra(
                MyObject.class.getCanonicalName()
        );
        Log.d(myLogs, "myObj " + myObject.s + ", " + myObject.i);
    }
}
