package org;

import org.model.Computer;

public class Main {
    public static void main(String[] args) {

        Computer computer = new Computer("256a8dd8-7f1a-4dcc-b124-3e7e4d0f8e20", 1L);
        String url = "http://localhost:8080/startWork";

        Controller.startWork(computer, url);

    }
}