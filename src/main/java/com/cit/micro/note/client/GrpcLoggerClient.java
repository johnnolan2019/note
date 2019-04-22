package com.cit.micro.note.client;

import com.cit.micro.logger.LoggerGrpc;
import com.cit.micro.logger.RemoteLog;
import com.cit.micro.logger.ReturnBool;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.time.LocalDate;

public class GrpcLoggerClient {

    public void info(String message) {
        transmit(message);

    }
    public void error(String message) {
        transmit(message);
    }

    public void debug(String message){
        transmit(message);
    }

    private void transmit(String message){
        LocalDate localDate = LocalDate.now();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        LoggerGrpc.LoggerBlockingStub stub = LoggerGrpc.newBlockingStub(channel);

        ReturnBool logResponse = stub.info(RemoteLog.newBuilder()
                .setTimeStamp(localDate.toString())
                .setServiceName("Note_Service")
                .setMessage(message)
                .build());

        channel.shutdown();

    }
}
