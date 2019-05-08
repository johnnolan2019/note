package com.cit.micro.note.service;

import com.cit.micro.note.Note;
import com.cit.micro.note.client.GrpcLoggerClient;
import com.cit.micro.note.dao.INoteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {

    private GrpcLoggerClient logger = new GrpcLoggerClient();
    private INoteDAO noteDAO;

    @Autowired
    NoteService(INoteDAO noteDAO){
        this.noteDAO = noteDAO;
    }

    @Override
    public List<Note> getAllNotes() {
        return noteDAO.getAllNotes();
    }

    @Override
    public List<Note> getNoteById(int noteId) {
        return noteDAO.getNoteById(noteId);
    }

    @Override
    public int addNote(Note note) {
        if (noteDAO.noteExists(note.getText(), note.getPointer())) {
            logger.info("not adding");
            return 0;
        } else {
            return noteDAO.addNote(note);
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
