package org;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Config conf = new Config();

//        onStart
        Controller.startWork(conf.getComputer(), conf.getStartUrl());

//        onStop
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Controller.endWork(conf.getComputer().getComputerId(), conf.getEndUrl());
        }));

        while(true) {
            System.out.println("Type config to change configuration");
            Scanner scanner = new Scanner(System.in);
            if(scanner.nextLine().equals("config")) {
                conf.changeConfig();
            }
        }


    }
}