package school.lab2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static University university = new University(); // Instantiate University
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        TUMBoardProgramMain();
    }

    public static void TUMBoardProgramMain() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("|--------------------------------------------------|");
            System.out.println("|   Welcome to TUM`s students management system    |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| What do you want to do?                          |");
            System.out.println("| g - General operations                           |");
            System.out.println("| f - Faculty operations                           |");
            System.out.println("| s - Students operations                          |");
            System.out.println("|--------------------------------------------------|");
            System.out.println("| q - Quit Program                                 |");
            System.out.println("|--------------------------------------------------|");
            String choice = scanner.next();

            switch (choice) {
                case "g":
                    generalOperations();
                    break;
                case "f":
                    facultyOperations();
                    break;
                case "s":
                    dataOperations();
                    break;
                case "q":
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void generalOperations() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("|--------------------------------------------------|");
        System.out.println("|                 General Operations               |");
        System.out.println("|--------------------------------------------------|");
        System.out.println("| 1 - Create a new faculty                         |");
        System.out.println("| 2 - Search what faculty a student belongs to     |");
        System.out.println("| 3 - Display University faculties                 |");
        System.out.println("| 4 - Display all faculties belonging to a field   |");
        System.out.println("|--------------------------------------------------|");
        System.out.println("| b - Back                                         |");
        System.out.println("| q - Quit Program                                 |");
        System.out.println("|--------------------------------------------------|");
        String choice = scanner.next();

        switch (choice) {
            case "1":
                // Create a new faculty
                System.out.println("Enter faculty name:");
                String name = scanner.next();
                System.out.println("Enter faculty abbreviation:");
                String abbreviation = scanner.next();
                System.out.println("Enter study field:");
                StudyField studyField = StudyField.valueOf(scanner.next().toUpperCase());
                university.createNewFaculty(name, abbreviation, studyField);
                break;
            case "2":
                // Search what faculty a student belongs to
                System.out.println("Enter student name:");
                String studentName = scanner.next();
                String faculty = university.findFacultyByStudentName(studentName);
                System.out.println("Student belongs to faculty: " + faculty);
                break;
            case "3":
                // Display University faculties
                university.displayUniversityFaculties();
                break;
            case "4":
                // Display all faculties belonging to a field
                System.out.println("Enter study field:");
                StudyField field = StudyField.valueOf(scanner.next().toUpperCase());
                university.displayFacultiesByField(field);
                break;
            case "b":
                TUMBoardProgramMain();
                break;
            case "q":
                System.out.println("Exiting program. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void facultyOperations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                   Faculty Operations                 |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| 1 - Create and assign a student to a faculty.        |");
        System.out.println("| 2 - Graduate a student from a faculty.               |");
        System.out.println("| 3 - Display current enrolled students.               |");
        System.out.println("| 4 - Display graduates.                               |");
        System.out.println("| 5 - Tell or not if a student belongs to this faculty.|");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| b - Back                                             |");
        System.out.println("| q - Quit Program                                     |");
        System.out.println("|------------------------------------------------------|");
        String choice = scanner.next();

        switch (choice) {
            case "1":
                System.out.println("Enter student details:");
                System.out.println("First Name:");
                String firstName = scanner.next();
                System.out.println("Last Name:");
                String lastName = scanner.next();
                System.out.println("Email:");
                String email = scanner.next();
                System.out.println("Enrollment Date (YYYY-MM-DD):");
                Date enrollmentDate = getDate(scanner.next());
                System.out.println("Date of Birth (YYYY-MM-DD):");
                Date dateOfBirth = getDate(scanner.next());
                System.out.println("Faculty Abbreviation:");
                String facultyAbbreviation = scanner.next();
                university.createAndAssignStudent(firstName, lastName, email, enrollmentDate, dateOfBirth, facultyAbbreviation);
                break;
            case "2":
                // Graduate a student from a faculty
                System.out.println("Enter student Name to graduate:");
                String studentName = scanner.next();
                university.graduateStudent(studentName);
                break;
            case "3":
                // Display current enrolled students
                university.displayCurrentEnrolledStudents();
                break;
            case "4":
                // Display graduates
                university.displayGraduates();
                break;
            case "5":
                System.out.println("Enter student name:");
                String nameToCheck = scanner.next();
                System.out.println("Enter faculty abbreviation:");
                String abbreviationToCheck = scanner.next();
                boolean belongs = university.isStudentInFaculty(nameToCheck, abbreviationToCheck);
                if (belongs) {
                    System.out.println("Student belongs to this faculty.");
                } else {
                    System.out.println("Student does not belong to this faculty.");
                }
                break;
            case "b":
                TUMBoardProgramMain();
                break;
            case "q":
                System.out.println("Exiting program. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void dataOperations() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("|---------------------------------------------|");
        System.out.println("|          Data Handling Operations          |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Save data to a file                    |");
        System.out.println("| 2 - Load data from a file                  |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| b - Back                                   |");
        System.out.println("| q - Quit Program                           |");
        System.out.println("|---------------------------------------------|");
        String choice = scanner.next();

        switch (choice) {
            case "1":
                // Save data to a file
                System.out.println("Enter filename to save data:");
                String saveFilename = scanner.next();
                DataHandler.saveData(university.getFaculties(), saveFilename);
                break;
            case "2":
                // Load data from a file
                System.out.println("Enter filename to load data:");
                String loadFilename = scanner.next();
                university.setFaculties(DataHandler.loadData(loadFilename));
                break;
            case "b":
                // Go back
                TUMBoardProgramMain();
                break;
            case "q":
                // Quit program
                System.out.println("Exiting program. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static Date getDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
            return null;
        }
    }
}
