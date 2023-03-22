package ru.startandroid.p0531_simplecursortreeadapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorTreeAdapter;

public class MainActivity extends AppCompatActivity {

     ExpandableListView exView;
     DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();

        Cursor cursor = db.getCompanyData();
        startManagingCursor(cursor);
        String[] groupForm = {DB.COMPANY_COLUMN_NAME};
        int[] groupTo = {android.R.id.text1};

        String[] childForm = {DB.PHONE_COLUMN_NAME};
        int[] childTo = {android.R.id.text1};

        SimpleCursorTreeAdapter adapter = new MyAdapter(
                this,
                cursor,
                android.R.layout.simple_expandable_list_item_1,
                groupForm,
                groupTo,
                android.R.layout.simple_expandable_list_item_1,
                childForm,
                childTo
        );

        exView = findViewById(R.id.lvsimple);
        exView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    class MyAdapter extends SimpleCursorTreeAdapter {

        public MyAdapter(Context context, Cursor cursor,
                         int expandedGroupLayout,
                         String[] groupFrom,
                         int[] groupTo,
                         int childLayout,
                         String[] childFrom,
                         int[] childTo) {
            super(context, cursor,
                    expandedGroupLayout,
                    groupFrom, groupTo, childLayout,
                     childFrom, childTo);
        }

        @Override
        protected Cursor getChildrenCursor(Cursor cursor) {
            int idCursor = cursor.getColumnIndex(DB.COMPANY_COLUMN_ID);
            return db.getPhoneData(cursor.getInt(idCursor));
        }
    }
}