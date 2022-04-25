package com.skynet.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteEditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText noteTitle;
    private EditText noteText;
    private DBAdapter dbAdapter;
    private long noteId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        Bundle arguments = getIntent().getExtras();
        noteTitle = findViewById(R.id.noteTitle);
        noteText = findViewById(R.id.noteText);
        dbAdapter = new DBAdapter(this);
        if (arguments != null) {
            noteId = arguments.getLong("id");
        }
        if (noteId > 0) {
            dbAdapter.open();
            Note note = (Note) dbAdapter.getNoteById(noteId);
            dbAdapter.close();
            noteTitle.setText(note.getTitle());
            noteText.setText(note.getText());
        }
        final Button backButton = findViewById(R.id.backButton);
        final Button saveButton = findViewById(R.id.saveButton);
        backButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(NoteEditActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        switch (v.getId()) {
            case R.id.backButton:
                startActivity(intent);
                break;
            case R.id.saveButton:
                if (TextUtils.isEmpty(((EditText)findViewById(R.id.noteTitle)).getText().toString())){
                    Toast.makeText(getApplicationContext(),"note title is empty ", Toast.LENGTH_SHORT).show();
                }
                else {
                    save(v);
                    Toast.makeText(getApplicationContext(), "note saved ", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
    public void save(View view) {
        String title = noteTitle.getText().toString();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String text = noteText.getText().toString();
        Note note = new Note(noteId, title, date, null, text);
        dbAdapter.open();
        if (noteId > 0) {
            dbAdapter.update(note);
        } else {
            dbAdapter.insert(note);
        }
        dbAdapter.close();
    }
}