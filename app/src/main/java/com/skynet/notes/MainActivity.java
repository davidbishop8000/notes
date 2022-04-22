package com.skynet.notes;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        notes.add(new Note ("My first note", date, null));
        notes.add(new Note ("My second note", date, null));
        notes.add(new Note ("My third note", date, null));
        RecyclerView recyclerView = findViewById(R.id.NotesRecycleView);
        NoteAdapter adapter = new NoteAdapter(this, notes);
        recyclerView.setAdapter(adapter);
    }

}