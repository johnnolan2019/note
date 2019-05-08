package com.cit.micro.note.dao;

import com.cit.micro.note.Note;

import java.util.List;

public interface INoteDAO {
    List<Note> getAllNotes();
    Note getNoteById(int articleId);
    int addNote(Note note);
    void updateNote(Note note);
    void deleteNote(int noteId);
    boolean noteExists(String text, int pointer);
}
