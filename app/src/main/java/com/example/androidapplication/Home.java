package com.example.androidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    public EditText noteTitleEdt, noteDateEdt, noteTextEdt;
    public DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        noteTitleEdt = findViewById(R.id.idEdtNoteTitle);
        noteDateEdt = findViewById(R.id.idEdtNoteDate);
        noteTextEdt = findViewById(R.id.idEdtNoteText);


        dbHandler = new DBHandler(Home.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        noteTitleEdt = findViewById(R.id.idEdtNoteTitle);
        noteDateEdt = findViewById(R.id.idEdtNoteDate);
        noteTextEdt = findViewById(R.id.idEdtNoteText);



        switch (item.getItemId()) {

            case R.id.save:
                String noteTitle = noteTitleEdt.getText().toString();
                String noteDate = noteDateEdt.getText().toString();
                String noteText = noteTextEdt.getText().toString();


                if (noteTitle.isEmpty() && noteDate.isEmpty() && noteText.isEmpty()) {
                    Toast.makeText(Home.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();

                }else {


                    dbHandler.addNewNote(noteTitle, noteDate, noteText);
                    Toast.makeText(Home.this, "New note has been added.", Toast.LENGTH_SHORT).show();
                    noteTitleEdt.setText("");
                    noteDateEdt.setText("");
                    noteTextEdt.setText("");

                    Intent i = new Intent(Home.this, ViewNotes.class);
                    startActivity(i);
                }
            case R.id.list:
                Intent j = new Intent(Home.this, ViewNotes.class);
                startActivity(j);


            default:
                return super.onOptionsItemSelected(item);
        }


    }
}



