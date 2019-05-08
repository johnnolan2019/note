package com.cit.micro.note.dao;

import com.cit.micro.note.Note;
import com.cit.micro.note.client.GrpcLoggerClient;
import com.cit.micro.note.entity.NoteRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        String sql = "SELECT id, text, pointer FROM note";
        RowMapper<Note> rowMapper = new NoteRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Note getNoteById(int noteId) {
        String sql = "SELECT id, text, pointer FROM note WHERE noteId = ?";
        RowMapper<Note> rowMapper = new BeanPropertyRowMapper<Note>(Note.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, noteId);
    }

    @Override
    public int addNote(Note note) {
        String sql = "INSERT INTO note (text, pointer) values (?, ?)";
        jdbcTemplate.update(sql, note.getText(), note.getPointer());

        //Fetch note id
        sql = "SELECT id FROM note WHERE text=? and pointer=?";
        int noteId = jdbcTemplate.queryForObject(sql, Integer.class, note.getText(), note.getPointer());

        //Set article id
       return noteId;
    }

    @Override
    public void updateNote(Note note) {
        String sql = "UPDATE note SET text=?, pointer=? WHERE id=?";
        jdbcTemplate.update(sql, note.getText(), note.getPointer(), note.getId());
    }

    @Override
    public void deleteNote(int noteId) {
        String sql = "DELETE FROM note WHERE id=?";
        jdbcTemplate.update(sql, noteId);
    }

    @Override
    public boolean noteExists(String text, int pointer) {
        String sql = "SELECT count(*) FROM note WHERE text = ? and pointer=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, text, pointer);
        return count == 0;
    }

}
