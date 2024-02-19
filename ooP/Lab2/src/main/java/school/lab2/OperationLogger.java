package school.lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class OperationLogger {

    private static final String LOG_FILE = "operation_log.txt";

    // Method to log an operation with a message
    public static void log(String operation, String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(LocalDateTime.now() + " - " + operation + ": " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}