package ru.startandroid.p0691_parcelable;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class MyObject implements Parcelable {

    private static final String LOG_TAG = "myLOG";

    public String s;
    public int i;

    public MyObject(String s, int i) {
        Log.d(LOG_TAG, "MyObject(String s, int i)");
        this.s = s;
        this.i = i;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d(LOG_TAG, "writeToParcel");
        parcel.writeString(s);
        parcel.writeInt(i);
    }

    public static final Parcelable.Creator<MyObject> CREATOR = new Parcelable.Creator<MyObject>() {
        @Override
        public MyObject createFromParcel(Parcel in) {
            Log.d(LOG_TAG, "createFromParcel");
            return new MyObject(in);
        }

        @Override
        public MyObject[] newArray(int size) {
            return new MyObject[size];
        }
    };

    public MyObject(Parcel in) {
        Log.d(LOG_TAG, "MyObject(Parcel in)");
        s = in.readString();
        i = in.readInt();
    }

}
