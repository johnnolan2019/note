package com.cit.micro.note.service;

import com.cit.micro.note.Id;
import com.cit.micro.note.NotationGrpc;
import com.cit.micro.note.Note;
import com.cit.micro.note.Result;
import com.cit.micro.note.client.GrpcLoggerClient;
import io.grpc.stub.StreamObserver;

public class GrpcNote extends NotationGrpc.NotationImplBase {
    private final GrpcLoggerClient logger = new GrpcLoggerClient();

    @Override
    public StreamObserver<Note> add(StreamObserver<Id> responseObserver){
        logger.info("Adding ");
        return new StreamObserver<Note>() {
            @Override
            public void onNext(Note note) {
                //todo handle receiving pointer and text from remote caller

            }

            @Override
            public void onError(Throwable throwable) {
                //todo handle failure
            }

            @Override
            public void onCompleted() {
                //todo handle the end of the send
            }
        };
    }

    @Override
    public void delete(Id id, StreamObserver<Result> responseObserver){

        logger.info("Deleting ");
    }

    @Override
    public void get(Id id, StreamObserver<Note> responseObserver){
        logger.info("Finding ");
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
