package school.lab2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class University {
    private List<Faculty> faculties;

    public University() {
        this.faculties = new ArrayList<>();
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    // Faculty operations

    public void createAndAssignStudent(String firstName, String lastName, String email,
                                       Date enrollmentDate, Date dateOfBirth, String facultyAbbreviation) {
        Faculty faculty = findFacultyByAbbreviation(facultyAbbreviation);
        if (faculty != null) {
            Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
            faculty.getStudents().add(student);
        } else {
            System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
        }
    }

    public void graduateStudent(String studentName) {
        for (Faculty faculty : faculties) {
            List<Student> students = faculty.getStudents();
            students.removeIf(student -> student.getName().equals(studentName));
        }
    }

    public void displayCurrentEnrolledStudents() {
        for (Faculty faculty : faculties) {
            System.out.println("Faculty: " + faculty.getName());
            for (Student student : faculty.getStudents()) {
                System.out.println("  Student: " + student.getFirstName() + " " + student.getLastName());
            }
        }
    }

    public void displayGraduates() {
        for (Faculty faculty : faculties) {
            System.out.println("Faculty: " + faculty.getName());
            // Assuming all students in the list are graduates
            for (Student student : faculty.getStudents()) {
                System.out.println("  Graduate: " + student.getFirstName() + " " + student.getLastName());
            }
        }
    }

    public boolean isStudentInFaculty(String studentName, String facultyAbbreviation) {
        Faculty faculty = findFacultyByAbbreviation(facultyAbbreviation);
        if (faculty != null) {
            for (Student student : faculty.getStudents()) {
                if (student.getName().equals(studentName)) {
                    return true;
                }
            }
        }
        return false;
    }

    // General operations

    public void createNewFaculty(String name, String abbreviation, StudyField studyField) {
        faculties.add(new Faculty(name, abbreviation, studyField));
    }

    public String findFacultyByStudentName(String studentName) {
        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (student.getName().equals(studentName)) {
                    return faculty.getName();
                }
            }
        }
        return "Student not found in any faculty.";
    }

    public void displayUniversityFaculties() {
        System.out.println("University Faculties:");
        for (Faculty faculty : faculties) {
            System.out.println("Name: " + faculty.getName() + ", Abbreviation: " + faculty.getAbbreviation());
        }
    }

    public void displayFacultiesByField(StudyField field) {
        System.out.println("Faculties in " + field.name() + " field:");
        for (Faculty faculty : faculties) {
            if (faculty.getStudyField() == field) {
                System.out.println("Name: " + faculty.getName() + ", Abbreviation: " + faculty.getAbbreviation());
            }
        }
    }

    private Faculty findFacultyByAbbreviation(String abbreviation) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equals(abbreviation)) {
                return faculty;
            }
        }
        return null;
    }

    // Method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
