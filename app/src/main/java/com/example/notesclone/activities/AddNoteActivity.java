package com.example.notesclone.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.notesclone.BaseActivity;
import com.example.notesclone.R;
import com.example.notesclone.presenter.ApiPresenter;

public class AddNoteActivity extends BaseActivity implements ApiPresenter.OnSavedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        setToolbar();
        setStatusBar();
    }

    public void saveNote(View view) {
        String title = ((EditText) findViewById(R.id.et_title)).getText().toString();
        String note = ((EditText) findViewById(R.id.et_note)).getText().toString();
        apiPresenter.setOnSaveNoteListener(this);
        apiPresenter.saveNote(title, note);
    }

    @Override
    public void onSave() {
        finish();
    }
}
