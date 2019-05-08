package com.cit.micro.note.service;

import com.cit.micro.note.Id;
import com.cit.micro.note.NotationGrpc;
import com.cit.micro.note.Note;
import com.cit.micro.note.Result;
import com.cit.micro.note.client.GrpcLoggerClient;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrpcNote extends NotationGrpc.NotationImplBase {
    private final GrpcLoggerClient logger = new GrpcLoggerClient();
    private INoteService noteService;

    @Autowired
    public GrpcNote(INoteService noteService){
        this.noteService = noteService;
    }

    @Override
    public void add(Note note, StreamObserver<Id> responseObserver){
        logger.info("Adding ");
        int tableId = noteService.addNote(note);
        Id id = Id.newBuilder().setId(String.valueOf(tableId)).build();
        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Id id, StreamObserver<Result> responseObserver){
        logger.info("Deleting ");
    }

    @Override
    public void get(Id id, StreamObserver<Note> responseObserver){
        logger.info("Finding ");
        Note note =  noteService.getNoteById(Integer.valueOf(id.getId()));
        responseObserver.onNext(note);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(Id id, StreamObserver<Note> responseObserver){
        logger.info("getting everything ");
    }

    @Override
    public void update(Note note, StreamObserver<Id> responseObserver){
        logger.info("updating ");
    }
}
