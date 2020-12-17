package com.techelevator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogReports {

    public void activityLog(String message) {
        try (FileOutputStream stream = new FileOutputStream("activity_log.txt", true);
             PrintWriter writer = new PrintWriter(stream)) {

            LocalDateTime timeStamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
            writer.println(timeStamp.format(formatter) + " " + message);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void salesReport(String message) {
        try (PrintWriter writer = new PrintWriter("Sales_Report.txt")) {

            writer.println(message);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}




