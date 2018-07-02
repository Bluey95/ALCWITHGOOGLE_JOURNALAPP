package com.example.susan.journalapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


    public class ActivityMAin extends Activity {
        private TextView mTitleText;
        private TextView mBodyText;
        private Long mRowId;
        private Diary_database_adapter mDbHelper;
        private Cursor mDiaryCursor;
        private static final int ACTIVITY_CREATE = 0;
        private static final int ACTIVITY_EDIT = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mDbHelper = new Diary_database_adapter(this);
            mDbHelper.open();
            setContentView(R.layout.activity_main);

            mTitleText = (TextView) findViewById(R.id.textview_title);
            mBodyText = (TextView) findViewById(R.id.textview_body);

            Button backButton = (Button) findViewById(R.id.back);

            mRowId = null;
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String title = extras.getString(Diary_database_adapter.KEY_TITLE);
                String body = extras.getString(Diary_database_adapter.KEY_BODY);
                mRowId = extras.getLong(Diary_database_adapter.KEY_ROWID);

                if (title != null) {
                    mTitleText.setText(title);
                }
                if (body != null) {
                    mBodyText.setText(body);
                }
            }

            backButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent i = new Intent(ActivityMAin.this, MainActivity.class);
                    startActivity(i);
            };
        });


    }

}
