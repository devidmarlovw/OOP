package school.lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Faculty implements Serializable {
    private String name;
    private String abbreviation;
    private List<Student> students;
    private StudyField studyField;

    // Constructor
    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.students = new ArrayList<>();
        this.studyField = studyField;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public List<Student> getStudents() {
        return students;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void batchEnrollment(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // Ensure correct number of fields
                    String firstName = data[0];
                    String lastName = data[1];
                    String email = data[2];
                    Student student = new Student(firstName, lastName, email, null, null);
                    students.add(student);
                    OperationLogger.log("Batch Enrollment", "Enrolled student: " + email);
                } else {
                    OperationLogger.log("Batch Enrollment", "Invalid data format in line: " + line);
                }
            }
        } catch (IOException e) {
            OperationLogger.log("Batch Enrollment", "Error reading file: " + filename);
            e.printStackTrace();
        }
    }

    // Method to batch graduate students from a text file
    public void batchGraduation(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean removed = false;
                for (Student student : students) {
                    if (student.getEmail().equals(line)) {
                        students.remove(student);
                        removed = true;
                        OperationLogger.log("Batch Graduation", "Graduated student: " + line);
                        break;
                    }
                }
                if (!removed) {
                    OperationLogger.log("Batch Graduation", "Student not present: " + line);
                }
            }
        } catch (IOException e) {
            OperationLogger.log("Batch Graduation", "Error reading file: " + filename);
            e.printStackTrace();
        }
    }
}
