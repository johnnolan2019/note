package com.cit.micro.note.dao;

import com.cit.micro.note.Note;
import com.cit.micro.note.client.GrpcLoggerClient;
import com.cit.micro.note.entity.NoteRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Transactional
@Repository
public class NoteDAO implements INoteDAO {

    private JdbcTemplate jdbcTemplate;
    private GrpcLoggerClient logger = new GrpcLoggerClient();

    @Autowired
    public NoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Note> getAllNotes() {
        String sql = "SELECT id, text, pointer FROM notes";
        RowMapper<Note> rowMapper = new NoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Note> getNoteById(int noteId) {
        String sql = "SELECT id, text, pointer FROM notes WHERE pointer = ?";
        RowMapper<Note> rowMapper = new NoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, noteId);
    }

    @Override
    public int addNote(Note note) {
        String sql = "INSERT INTO notes (text, pointer) values (?, ?)";
        this.jdbcTemplate.update(sql, note.getText(), note.getPointer());

        //Fetch id
        sql = "SELECT id FROM notes WHERE text=? and pointer=?";

        int noteId = 0;
        try{
            noteId = this.jdbcTemplate.queryForObject(sql, Integer.class, note.getText(), note.getPointer());
        }catch (EmptyResultDataAccessException e){
            logger.error("Could not find ID for new entry");
        }catch (IncorrectResultSizeDataAccessException e){
            logger.error("Data added is not unique");
        }
        return noteId;
    }

    @Override
    public void updateNote(Note note) {
        String sql = "UPDATE notes SET text=?, pointer=? WHERE id=?";
        this.jdbcTemplate.update(sql, note.getText(), note.getPointer(), note.getId());
    }

    @Override
    public void deleteNote(int noteId) {
        String sql = "DELETE FROM notes WHERE id=?";
        this.jdbcTemplate.update(sql, noteId);
    }

    @Override
    public boolean noteExists(String text, int pointer) {
        String sql = "SELECT count(*) FROM notes WHERE text = ? and pointer=?";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class, text, pointer);
        logger.info("Count of exists");
        logger.info(String.valueOf(count));
        return count != 0;
    }
}
