package com.example.androidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteUpdate extends AppCompatActivity {

    public EditText noteTitleEdt, noteDateEdt, noteTextEdt;
    public DBHandler dbHandler;
    String noteTitle, noteDate, noteText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_update);

        noteTitleEdt = findViewById(R.id.idEdtNoteTitle);
        noteDateEdt = findViewById(R.id.idEdtNoteDate);
        noteTextEdt = findViewById(R.id.idEdtNoteText);


        dbHandler = new DBHandler(NoteUpdate.this);

        noteTitle = getIntent().getStringExtra("title");
        noteDate = getIntent().getStringExtra("date");
        noteText = getIntent().getStringExtra("note");

        noteTitleEdt.setText(noteTitle);
        noteDateEdt.setText(noteDate);
        noteTextEdt.setText(noteText);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.update_and_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        switch (item.getItemId()) {

            case R.id.update:

                dbHandler.updateNote(noteTitle, noteTitleEdt.getText().toString(),
                        noteDateEdt.getText().toString(), noteTextEdt.getText().toString());
                Toast.makeText(NoteUpdate.this, "Note Updated.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(NoteUpdate.this, ViewNotes.class);
                startActivity(i);
                break;

            case R.id.delete:
                dbHandler.deleteNote(noteTitle);
                Toast.makeText(NoteUpdate.this, "Note Successfully Deleted.", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(NoteUpdate.this, ViewNotes.class);
                startActivity(j);
                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return false;

    }


}