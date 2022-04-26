package com.skynet.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
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
    private final String ColorsForMenu[] = {"white", "black", "red", "green", "blue"};
    private int colors[] = {R.color.white, R.color.black, R.color.red, R.color.green, R.color.blue};

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
            noteText.setTextColor(note.getTextColor());
            noteText.setTextSize(TypedValue.COMPLEX_UNIT_PX, note.getTextSize());
        }
        final Button backButton = findViewById(R.id.backButton);
        final Button saveButton = findViewById(R.id.saveButton);
        backButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        registerForContextMenu(noteText);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(NoteEditActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        switch (v.getId()) {
            case R.id.backButton:
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
        startActivity(intent);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu");
        SubMenu subMenuColor = menu.addSubMenu(0, 0, menu.NONE, "Color");
        for (int i = 0; i< ColorsForMenu.length; i++) {
            subMenuColor.add(1, i, 0, ColorsForMenu[i]);
        }
        SubMenu subMenuSize = menu.addSubMenu(0, 0, menu.NONE, "Size");
        for (int i=16; i<=28; i+=2) {
            subMenuSize.add(2, i, 0, String.valueOf(i));
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getGroupId() == 1 && item.getItemId() < ColorsForMenu.length) {
            noteText.setTextColor(ContextCompat.getColor(this, colors[item.getItemId()]));
        }
        else if (item.getGroupId() == 2) {
            noteText.setTextSize(Integer.parseInt((String)item.getTitle()));
        }
        return true;
    }
    public void save(View view) {
        String title = noteTitle.getText().toString();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String text = noteText.getText().toString();
        int textColor = noteText.getCurrentTextColor();
        int textSize = (int)noteText.getTextSize();
        Note note = new Note(noteId, title, date, null, text, textColor, textSize);
        dbAdapter.open();
        if (noteId > 0) {
            dbAdapter.update(note);
        } else {
            dbAdapter.insert(note);
        }
        dbAdapter.close();
    }
}