package com.skynet.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "notes";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_DATE = "date";
    public static final String NOTE_TEXT = "text";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + NOTE_TITLE + " TEXT, " + NOTE_DATE + " TEXT, " + NOTE_TEXT + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + NOTE_TITLE + ", "
        + NOTE_DATE + ", " + NOTE_TEXT + ") VALUES ('My first note', '24.04.2022', 'Hellow world!');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
