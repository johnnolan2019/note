package com.cit.micro.note;

import com.cit.micro.note.client.GrpcLoggerClient;
import com.cit.micro.note.service.GrpcNote;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class NoteApplication {

    public static void main(String[] args) {
        final GrpcLoggerClient logger = new GrpcLoggerClient();
        SpringApplication.run(NoteApplication.class, args);
        Server server = ServerBuilder
                .forPort(6566)
                .addService(new GrpcNote()).build();
        try{
            server.start();
            server.awaitTermination();
        }catch (IOException e){
            logger.info("bad");
        }catch (InterruptedException e){
            logger.error("Not as bad, but not good");
        }
    }

}
