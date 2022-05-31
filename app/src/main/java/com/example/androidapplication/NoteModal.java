package com.example.androidapplication;

public class NoteModal {

    public String noteTitle;
    public String noteDate;
    public String noteText;
    private int id;

    public String getnoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getnoteDate() { return noteDate; }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getnoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public NoteModal(String noteTitle, String noteDate, String noteText) {
        this.noteTitle = noteTitle;
        this.noteDate = noteDate;
        this.noteText = noteText;
    }




}
