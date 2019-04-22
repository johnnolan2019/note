package com.cit.micro.note.service;

import com.cit.micro.note.Note;

import java.util.List;

public interface INoteService {
    List<Note> getAllNotes();
    Note getNoteById(int noteId);
    boolean addNote(Note note);
    void updateNote(Note note);
    void deleteNote(int noteId);
}
