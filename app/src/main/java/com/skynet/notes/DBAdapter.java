package com.skynet.notes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBAdapter(Context context) {
        dbHelper = new DBHelper(context.getApplicationContext());
    }

    public DBAdapter open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private Cursor getDBNotes() {
        String[] NotesFromDB = new String[] {DBHelper.NOTE_ID, DBHelper.NOTE_TITLE, DBHelper.NOTE_DATE, DBHelper.NOTE_TEXT};
        return database.query(DBHelper.TABLE_NAME, NotesFromDB, null, null, null, null, null);
    }

    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        Cursor cursor = getDBNotes();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.NOTE_ID));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DBHelper.NOTE_TITLE));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DBHelper.NOTE_DATE));
            notes.add(new Note(id, title, date, null, "", 0, 0));
        }
        cursor.close();
        return notes;
    }
    public Note getNoteById(long id){
        Note note = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DBHelper.TABLE_NAME, DBHelper.NOTE_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DBHelper.NOTE_TITLE));
            @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(DBHelper.NOTE_DATE));
            @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex(DBHelper.NOTE_TEXT));
            @SuppressLint("Range") int textColor = cursor.getInt(cursor.getColumnIndex(DBHelper.NOTE_TEXT_COLOR));
            @SuppressLint("Range") int textSize = cursor.getInt(cursor.getColumnIndex(DBHelper.NOTE_TEXT_SIZE));
            note = new Note(id, title, data, null, text, textColor, textSize);
        }
        cursor.close();
        return note;
    }
    public long insert(Note note) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.NOTE_TITLE, note.getTitle());
        cv.put(DBHelper.NOTE_DATE, note.getDate());
        cv.put(DBHelper.NOTE_TEXT, note.getText());
        cv.put(DBHelper.NOTE_TEXT_COLOR, note.getTextColor());
        cv.put(DBHelper.NOTE_TEXT_SIZE, note.getTextSize());
        return database.insert(DBHelper.TABLE_NAME, null, cv);
    }

    public  long delete(long noteId) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(noteId)};
        return database.delete(DBHelper.TABLE_NAME, whereClause, whereArgs);
    }

    public long update(Note note) {
        String whereClause = DBHelper.NOTE_ID + "=" + note.getId();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.NOTE_TITLE, note.getTitle());
        cv.put(DBHelper.NOTE_DATE, note.getDate());
        cv.put(DBHelper.NOTE_TEXT, note.getText());
        cv.put(DBHelper.NOTE_TEXT_COLOR, note.getTextColor());
        cv.put(DBHelper.NOTE_TEXT_SIZE, note.getTextSize());
        return database.update(DBHelper.TABLE_NAME, cv, whereClause, null);
    }
}
