package com.example.androidapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<NoteModal> noteModalArrayList;
    private Context context;

    public NoteAdapter(ArrayList<NoteModal> noteModalArrayList, Context context) {
        this.noteModalArrayList = noteModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NoteModal modal = noteModalArrayList.get(position);
        holder.noteTitleTV.setText(modal.getnoteTitle());
        holder.noteDateTV.setText(modal.getnoteDate());
        holder.noteTextTV.setText(modal.getnoteText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,  NoteUpdate.class);
                i.putExtra("title", modal.getnoteTitle());
                i.putExtra("date", modal.getnoteDate());
                i.putExtra("note", modal.getnoteText());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView noteTitleTV, noteDateTV, noteTextTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitleTV = itemView.findViewById(R.id.idTVNoteTitle);
            noteDateTV = itemView.findViewById(R.id.idTVNoteDate);
            noteTextTV = itemView.findViewById(R.id.idTVNoteText);
        }
    }



}
