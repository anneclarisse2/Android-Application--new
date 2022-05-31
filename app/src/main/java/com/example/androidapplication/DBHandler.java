package com.example.androidapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "notedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mynotedb";
    private static final String ID_COL = "id";
    private static final String TITLE_COL = "title";
    private static final String DATE_COL = "date";
    private static final String NOTES_COL = "notes";
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + NOTES_COL + " TEXT)";

        db.execSQL(query);

    }

    public void addNewNote(String noteTitle, String noteDate, String noteText) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE_COL, noteTitle);
        values.put(DATE_COL, noteDate);
        values.put(NOTES_COL, noteText);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<NoteModal> readNotes() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorNotes = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<NoteModal> noteModalArrayList = new ArrayList<>();

        if (cursorNotes.moveToFirst()) {
            do {
                noteModalArrayList.add(new NoteModal(cursorNotes.getString(1),
                        cursorNotes.getString(2),
                        cursorNotes.getString(3)));
            } while (cursorNotes.moveToNext());
        }
        cursorNotes.close();
        return noteModalArrayList;
    }

    public void updateNote(String originalNoteTitle, String noteTitle, String noteDate,
                           String noteText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TITLE_COL, noteTitle);
        values.put(DATE_COL, noteDate);
        values.put(NOTES_COL, noteText);

        db.update(TABLE_NAME, values, "title=?", new String[]{originalNoteTitle});
        db.close();
    }


    public void deleteNote(String noteTitle) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "title=?", new String[]{noteTitle});
        db.close();
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    }

