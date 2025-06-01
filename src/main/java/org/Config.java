package org;

import lombok.Getter;

import java.io.*;
import java.util.Scanner;

@Getter
public class Config {
    private String url = "http://localhost:8080";
    private final String start = "/api/v1/usage/desktop/startWork";
    private String startUrl = url + start;
    private final String end = "/api/v1/usage/desktop/endWork";
    private String endUrl = url + end;
    private Computer computer = new Computer("",1L);

    public Config(){
        loadFromFile("config.txt");
    }


    private void loadFromFile(String filePath) {
        try{
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File does not exist: " + filePath);
        }
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found. Enter new config");
            changeConfig();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            if ((line = reader.readLine()) != null) {
                url = line.trim();
            }
            if ((line = reader.readLine()) != null) {
                computer.setUserId(line.trim());
            }
            if ((line = reader.readLine()) != null) {
                computer.setComputerId(Long.parseLong(line.trim()));
            }

            System.out.println("Config loaded:");
            System.out.println("url = " + url);
            System.out.println("userId = " + computer.getUserId());
            System.out.println("computerId = " + computer.getComputerId());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Błąd parsowania computerId");
            e.printStackTrace();
        }
    }

    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(url);
            writer.newLine();
            writer.write(computer.getUserId());
            writer.newLine();
            writer.write(computer.getComputerId().toString());
            writer.newLine();
            System.out.println("Config saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving configuration to file");
            e.printStackTrace();
        }
    }


    public void changeConfig(){
        Scanner scanner = new Scanner(System.in);

            System.out.println("Set remote path or press enter to continue. current url: " + url);
            String newUrl = scanner.nextLine();
            if(!newUrl.isEmpty()){
                url = newUrl;
                startUrl = url + start;
                endUrl = url + end;
            }

            System.out.println("Set userId or press enter to continue");
            String newUserId = scanner.nextLine();
            if(!newUserId.isEmpty())
                computer.setUserId(newUserId);

            System.out.println("Set computerId or press enter to continue");
            String newComputerId = scanner.nextLine();
            if(!newComputerId.isEmpty())
                computer.setComputerId(Long.parseLong(newComputerId));
            saveToFile("config.txt");
    }
}
