package com.example.androidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewNotes extends AppCompatActivity {

    private ArrayList<NoteModal> noteModalArrayList;
    private DBHandler dbHandler;
    private NoteAdapter notesRVAdapter;
    private RecyclerView notesRV;
    FloatingActionButton addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        addbtn = findViewById(R.id.floatingActionButton);


        noteModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewNotes.this);

        noteModalArrayList = dbHandler.readNotes();

        notesRVAdapter = new NoteAdapter(noteModalArrayList, ViewNotes.this);
        notesRV = findViewById(R.id.idRVNotes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewNotes.this, RecyclerView.VERTICAL, false);
        notesRV.setLayoutManager(linearLayoutManager);

        notesRV.setAdapter(notesRVAdapter);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewNotes.this, Home.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewNotes.this);
                builder.setMessage("Do you want to exit?");
                builder.setCancelable(true);

                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}