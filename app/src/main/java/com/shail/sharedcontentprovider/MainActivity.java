package com.shail.sharedcontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        updateUI();
    }

    private void updateUI() {
        Uri uri = Uri.parse("content://com.shail.contentprovider.provider/person");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        Log.i(TAG, "ColumnCount" + cursor.getColumnCount());
        String[] columnNamesArray = new String[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            columnNamesArray[i] = columnName;
        }
        Log.i(TAG, "columnNamesArray" + columnNamesArray);
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.person_view,
                cursor,
                columnNamesArray,
                new int[]{R.id.person_id, R.id.person_ssn,R.id.person_name},
                0
        );
        listView.setAdapter(listAdapter);
    }
}
