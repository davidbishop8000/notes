package com.skynet.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteEditActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        Bundle arguments = getIntent().getExtras();
        String titleVal = arguments.get("title").toString();
        final EditText editTitle = findViewById(R.id.editTitle);
        if (!TextUtils.isEmpty(titleVal)){
            editTitle.setText(titleVal);
        }
        final Button backButton = findViewById(R.id.backButton);
        final Button saveButton = findViewById(R.id.saveButton);
        backButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        Intent intent = new Intent(NoteEditActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        switch (v.getId()) {
            case R.id.backButton:
                startActivity(intent);
                break;
            case R.id.saveButton:
                if (TextUtils.isEmpty(((EditText)findViewById(R.id.editTitle)).getText().toString())){
                    Toast.makeText(getApplicationContext(),"note title is empty ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "note saved ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}