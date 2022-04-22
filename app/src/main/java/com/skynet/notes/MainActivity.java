package com.skynet.notes;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ArrayList<Note> notes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button addButton = findViewById(R.id.addButton);
        final RecyclerView recyclerView = findViewById(R.id.NotesRecycleView);
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        notes.add(new Note ("My first note", date, null));
        notes.add(new Note ("My second note", date, null));
        notes.add(new Note ("My third note", date, null));

        NoteAdapter.NoteClickListener noteClickListener = (note, position) -> startEditActivity(note.getTitle());

        NoteAdapter adapter = new NoteAdapter(this, notes, noteClickListener);
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEditActivity("");
            }
        });
    }
    private void startEditActivity(String titleVal) {
        Intent intent = new Intent(MainActivity.this, NoteEditActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("title", titleVal);
        startActivity(intent);
    }
}