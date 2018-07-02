package com.example.susan.journalapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {

    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;

    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;

    private Diary_database_adapter mDbHelper;
    private Cursor mDiaryCursor;

    ArrayList<String> buttons;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new Diary_database_adapter(this);
        mDbHelper.open();
        setContentView(R.layout.diary_list);
        renderListView();

//        ArrayList<String> list = new ArrayList<String>();
//        DiaryRow adapter = new DiaryRow(list, this);

//        ListView IView = (ListView)findViewById(R.id.android_list);
//        IView.setAdapter(adapter);

//        ListView listView = (ListView)findViewById(R.id.android_list);
//        DiaryRow adapter = new DiaryRow(this, buttons);
//        listView.setAdapter(adapter);

        Button editButton = (Button) findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActivityDiaryEdit.class);
                startActivity(i);
            }

            ;
        });

    }


    private void renderListView() {
        mDiaryCursor = mDbHelper.getAllNotes();
        startManagingCursor(mDiaryCursor);
        String[] from = new String[] { Diary_database_adapter.KEY_TITLE,
                Diary_database_adapter.KEY_CREATED };
        int[] to = new int[] { R.id.text1, R.id.created };
        SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
                R.layout.diary_row, mDiaryCursor, from, to);
        setListAdapter(notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_insert);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case INSERT_ID:
                createDiary();
                return true;
            case DELETE_ID:
                mDbHelper.deleteDiary(getListView().getSelectedItemId());
                renderListView();
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    private void createDiary() {
        Intent i = new Intent(this, ActivityDiaryEdit.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }

    private void deleteDiary(){

    }

    @Override

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor c = mDiaryCursor;
        c.moveToPosition(position);
        Intent i = new Intent(this, ActivityDiaryEdit.class);
        i.putExtra(Diary_database_adapter.KEY_ROWID, id);
        i.putExtra(Diary_database_adapter.KEY_TITLE, c.getString(c
                .getColumnIndexOrThrow(Diary_database_adapter.KEY_TITLE)));
        i.putExtra(Diary_database_adapter.KEY_BODY, c.getString(c
                .getColumnIndexOrThrow(Diary_database_adapter.KEY_BODY)));
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        renderListView();
    }




}