package com.example.susan.journalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityDiaryEdit extends Activity{
    private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    private Diary_database_adapter mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new Diary_database_adapter(this);
        mDbHelper.open();
        setContentView(R.layout.diary_edit);

        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);

        Button confirmButton = (Button) findViewById(R.id.confirm);

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

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = mTitleText.getText().toString();
                String body = mBodyText.getText().toString();
                if (mRowId != null) {
                    mDbHelper.updateDiary(mRowId, title, body);
                } else
                    mDbHelper.createDiary(title, body);
                Intent mIntent = new Intent();
                setResult(RESULT_OK, mIntent);
                finish();
            }

        });
    }
}
