package ru.startandroid.p0751_files;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String MY_LOG = "myLog";

    private static final String FILENAME = "file";

    String DIR_SD = "MyFiles";

    String FILENAME_SD = "fileSD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnWrite:
                write();
                break;
            case R.id.btnRead:
                read();
                break;
            case R.id.btnWriteSD:
                writeSD();
                break;
            case R.id.btnReadSD:
                readSD();
                break;
            default:
                break;
        }
    }

    public void write() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));

            bw.write("Содержимое файла");
            bw.close();
            Log.d(MY_LOG, "Файл записан");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String line = "";
            while ((line = br.readLine()) != null) {
                Log.d(MY_LOG, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSD() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(MY_LOG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }

                File sdFile = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS + "/" + DIR_SD), FILENAME_SD);

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
                    bw.write("Содержимое файла на sd");
                    bw.close();
                    Log.d(MY_LOG, "Файл записан на sd " + sdFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

    }


    public void readSD() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(MY_LOG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }

        File sdFile = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS + "/" + DIR_SD), FILENAME_SD);
        try {
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
           String line = "";
           while ((line = br.readLine()) != null) {
               Log.d(MY_LOG, line);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}