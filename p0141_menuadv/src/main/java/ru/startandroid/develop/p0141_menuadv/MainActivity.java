package ru.startandroid.develop.p0141_menuadv;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     TextView text;
     CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.tvOut);
        check = (CheckBox) findViewById(R.id.chbExtMenu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        // добавляем пункты меню
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // обновление меню
    public boolean onPrepareOptionsMenu(Menu menus) {
        // TODO Auto-generated method stub
        Log.d("TAG", "Проверка чекбокса");
        menus.setGroupVisible(R.id.group1, check.isChecked());
        return super.onPrepareOptionsMenu(menus);
    }

    // обработка нажатий

    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();

        sb.append("Item Menu");
        sb.append("\r\n groupId: ").append(item.getGroupId());
        sb.append("\r\n itemId: ").append(item.getItemId());
        sb.append("\r\n order: ").append(item.getOrder());
        sb.append("\r\n title: ").append(item.getTitle());
        text.setText(sb.toString());
        return super.onOptionsItemSelected(item);
    }
}