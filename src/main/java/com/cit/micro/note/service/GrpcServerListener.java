package com.cit.micro.note.service;

import com.cit.micro.note.client.GrpcLoggerClient;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GrpcServerListener {
    private static final GrpcLoggerClient logger = new GrpcLoggerClient();
    private static GrpcNote grpcNote;

    @Autowired
    public GrpcServerListener(GrpcNote grpcNoteInjected){
        grpcNote = grpcNoteInjected;
    }

    public static void serverRun(){
        Server server = ServerBuilder
                .forPort(6566)
                .addService(grpcNote).build();
        logger.info("Note service now running ");
        try{
            server.start();
            server.awaitTermination();
        }catch (
                IOException e){
            logger.error("bad");
        }catch (InterruptedException e){
            logger.error("Not as bad, but not good");
        }
    }

}
