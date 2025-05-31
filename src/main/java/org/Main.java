package org;

import org.model.Computer;

public class Main {
    public static void main(String[] args) {

//        OnStart
        Computer computer = new Computer("256a8dd8-7f1a-4dcc-b124-3e7e4d0f8e20", 1L);
        String startUrl = "http://localhost:8080/startWork";
        String endUrl = "http://localhost:8080/endWork";
        Controller.startWork(computer, startUrl);


//        onStop
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Controller.endWork(computer.getComputerId(), endUrl);
        }));


        while (true) ;
    }
}