package ru.startandroid.p0681_parcel;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String myLog = "myLog";

    private Parcel parcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeParcel();
        readParcel();
    }

    public void writeParcel() {
        parcel = Parcel.obtain();

        byte b = 1;
        int i = 2;
        long l = 3;
        float f = 4;
        double d = 5;
        String s = "abcdefgh";

        logWriteInfo("Before writing");
        parcel.writeByte(b);
        logWriteInfo("Byte");
        parcel.writeInt(i);
        logWriteInfo("Int");
        parcel.writeLong(l);
        logWriteInfo("Long");
        parcel.writeFloat(f);
        logWriteInfo("Float");
        parcel.writeDouble(d);
        logWriteInfo("Double");
        parcel.writeString(s);
        logWriteInfo("String");
    }

    public void logWriteInfo(String text) {
        Log.d(myLog, text + ": " + "dataSize " + parcel.dataSize());
    }

    public void readParcel() {

        logReadInfo("Before reading");
        parcel.setDataPosition(0);
        logReadInfo("Byte = " + parcel.readByte());
        logReadInfo("Int = " + parcel.readInt());
        logReadInfo("Long = " + parcel.readLong());
        logReadInfo("Float = " + parcel.readFloat());
        logReadInfo("Double = " + parcel.readDouble());
        logReadInfo("String = " + parcel.readString());
    }

    public void logReadInfo(String text) {
        Log.d(myLog, text + ": " + "dataPosition " + parcel.dataPosition());
    }
}