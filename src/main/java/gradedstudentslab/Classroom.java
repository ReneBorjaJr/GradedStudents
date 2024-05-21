package gradedstudentslab;

import java.util.*;
import java.util.stream.Collectors;

public class Classroom {
    private Student[] students;


    public Classroom(int maxNumberOfStudents) {
        students = new Student[maxNumberOfStudents];
    }


    public Classroom(Student[] students) {
        this.students = students;
    }

    public Classroom() {
        students = new Student[30];
    }


    public Student[] getStudents() {
        long nonNullCount = Arrays.stream(students)
                .filter(Objects::nonNull)
                .count();
        Student[] nonNullStudents = new Student[(int) nonNullCount];
        int index = 0;
        for (Student student : students) {
            if (student != null) {
                nonNullStudents[index++] = student;
            }
        }
        return nonNullStudents;
    }


    public double getAverageExamScore() {
        long nonNullCount = Arrays.stream(students)
                .filter(Objects::nonNull)
                .count();
        double total = Arrays.stream(students)
                .filter(Objects::nonNull)
                .mapToDouble(Student::getAverageExamScore)
                .sum();
        return (double) (int) total / nonNullCount;
    }


    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }


    public void removeStudent(String firstName, String lastName) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getFirstName().equals(firstName) && students[i].getLastName().equals(lastName)) {
                // Remove the student by setting the element to null
                students[i] = null;
                break;
            }
        }

        // Reorder the array
        int nonNullIndex = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                students[nonNullIndex++] = students[i];
            }
        }

        // Set remaining elements to null
        for (int i = nonNullIndex; i < students.length; i++) {
            students[i] = null;
        }
    }


    public Student[] getStudentsByScore() {
        List<Student> nonNullStudents = new ArrayList<>();
        for (Student student : students) {
            if (student != null) {
                nonNullStudents.add(student);
            }
        }
        // Sort students
        nonNullStudents.sort((s1, s2) -> {
            // Compare by average exam score in descending order
            double scoreDiff = s2.getAverageExamScore() - s1.getAverageExamScore();
            if (scoreDiff != 0) {
                return (int) Math.signum(scoreDiff);
            } else {
                // If scores are the same, compare lexicographically by name
                int lastNameComparison = s1.getLastName().compareTo(s2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                } else {
                    return s1.getFirstName().compareTo(s2.getFirstName());
                }
            }
        });

        //Return the sorted array
        return nonNullStudents.toArray(new Student[0]);
    }


    public Map<Student, String> getGradeBook() {
        Student[] nonNullStudents = getStudents();
        Arrays.sort(nonNullStudents, Comparator.comparingDouble(Student::getAverageExamScore).reversed());

        int totalStudents = nonNullStudents.length;
        Map<Student, String> gradeBook = new HashMap<>();

        int upper10thIndex = (int) Math.ceil(0.10 * totalStudents);
        int upper29thIndex = (int) Math.ceil(0.29 * totalStudents);
        int upper50thIndex = (int) Math.ceil(0.50 * totalStudents);
        int lower89thIndex = (int) Math.ceil(0.89 * totalStudents);

        for (int i = 0; i < totalStudents; i++) {
            if (i < upper10thIndex) {
                gradeBook.put(nonNullStudents[i], "> Student grade: A\n\n");
            } else if (i < upper29thIndex) {
                gradeBook.put(nonNullStudents[i], "> Student grade: B\n\n");
            } else if (i < upper50thIndex) {
                gradeBook.put(nonNullStudents[i], "> Student grade: C\n\n");
            } else if (i < lower89thIndex) {
                gradeBook.put(nonNullStudents[i], "> Student grade: D\n\n");
            } else {
                gradeBook.put(nonNullStudents[i], "> Student grade: F\n\n");
            }
        }
        return gradeBook;
    }


    public void printGradeBook() {
        Map<Student, String> gradeBook = getGradeBook();
        System.out.println("=========================================");
        for (Map.Entry<Student, String> entry : gradeBook.entrySet()) {
            Student student = entry.getKey();
            if (student != null){
                String grade = entry.getValue();
                System.out.println("Student Name: " + student.getFirstName() + " " + student.getLastName());
                System.out.println("> Average Score: " + student.getAverageExamScore());
                System.out.print(student.getExamScores());
                System.out.println(grade);
            }
        }
    }

}
