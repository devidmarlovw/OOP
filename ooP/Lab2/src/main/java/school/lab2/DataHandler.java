package school.lab2;

import school.lab2.Faculty;

import java.io.*;
import java.util.List;

public class DataHandler {

    // Method to save data to a file
    public static void saveData(List<Faculty> faculties, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(faculties);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving data.");
        }
    }

    // Method to load data from a file
    public static List<Faculty> loadData(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            List<Faculty> faculties = (List<Faculty>) inputStream.readObject();
            System.out.println("Data loaded successfully.");
            return faculties;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading data.");
            return null;
        }
    }
}
