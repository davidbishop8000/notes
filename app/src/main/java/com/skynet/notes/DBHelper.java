package com.skynet.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "notes";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_DATE = "date";
    public static final String NOTE_TEXT = "text";
    public static final String NOTE_TEXT_COLOR = "color";
    public static final String NOTE_TEXT_SIZE = "size";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + NOTE_TITLE + " TEXT, " + NOTE_DATE + " TEXT, " + NOTE_TEXT + " TEXT, "
        + NOTE_TEXT_COLOR + " INTEGER, " + NOTE_TEXT_SIZE + " INTEGER);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + NOTE_TITLE + ", "
        + NOTE_DATE + ", " + NOTE_TEXT + ", " + NOTE_TEXT_COLOR + ", " + NOTE_TEXT_SIZE + ") VALUES ('My first note', '24.04.2022', 'Hello world!', "+ Color.GRAY +", 90);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
