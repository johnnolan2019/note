package com.cit.micro.note.service;

import com.cit.micro.note.Note;
import com.cit.micro.note.dao.INoteDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoteService implements INoteService {
    
    @Autowired
    private INoteDAO noteDAO;

    @Override
    public List<Note> getAllNotes() {
        return noteDAO.getAllNotes();
    }

    @Override
    public Note getNoteById(int noteId) {
        return noteDAO.getNoteById(noteId);
    }

    @Override
    public boolean addNote(Note note) {
        if (noteDAO.noteExists(note.getText(), note.getPointer())) {
            return false;
        } else {
            noteDAO.addNote(note);
            return true;
        }
    }

    @Override
    public void updateNote(Note note) {
        noteDAO.updateNote(note);
    }

    @Override
    public void deleteNote(int noteId) {
        noteDAO.deleteNote(noteId);
    }
}
