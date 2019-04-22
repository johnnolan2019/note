package com.cit.micro.note.entity;

import com.cit.micro.note.Note;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteRowMapper implements RowMapper<Note> {
    @Override
    //todo fix this...
    public Note mapRow(ResultSet row, int rowNum) throws SQLException {
        return Note.newBuilder().setPointer(row.getInt("pointer")).setText(row.getString("text")).build();
    }
}
