package com.skynet.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;
    private RecyclerView recyclerView;
    private Button addButton;
    private DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.NotesRecycleView);
        dbAdapter = new DBAdapter(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.v("LOG: ", "Resumed");
        dbAdapter.open();
        notes = (ArrayList<Note>) dbAdapter.getNotes();
        Collections.reverse(notes);
        dbAdapter.close();
        NoteAdapter.NoteClickListener noteClickListener = new NoteAdapter.NoteClickListener() {
            @Override
            public void NoteClick(Note note, int position) {
                startEditActivity(note.getId());
                Log.v("LOG: ", "short");
            }
            @Override
            public boolean NoteLongClick(Note note, int position) {
                Log.v("LOG: ", "long");
                removeNote(position);
                return true;
            }
        };

        noteAdapter = new NoteAdapter(this, notes, noteClickListener);
        recyclerView.setAdapter(noteAdapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEditActivity(0L);
            }
        });
    }
    private void startEditActivity(Long noteId) {
        Intent intent = new Intent(MainActivity.this, NoteEditActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("id", noteId);
        startActivity(intent);
    }
    private void removeNote(int pos) {
        final CharSequence[] items = {"Remove", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item==0) {
                    dbAdapter.open();
                    dbAdapter.delete((notes.get(pos)).getId());
                    dbAdapter.close();
                    notes.remove(pos);
                    recyclerView.removeViewAt(pos);
                    noteAdapter.notifyItemRemoved(pos);
                    noteAdapter.notifyItemRangeChanged(pos, notes.size());
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}