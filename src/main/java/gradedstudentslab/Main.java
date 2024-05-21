package gradedstudentslab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create an instance of Classroom
        Classroom classroom = new Classroom(3);
        classroom.addStudent(new Student("Leon", "Hunter", new ArrayList<>()));
        classroom.addStudent(new Student("John", "Day", new ArrayList<>()));
        classroom.addStudent(new Student("Jane", "Daa", new ArrayList<>()));
        // add test scores to Leon
        classroom.getStudents()[0].addExamScore(80.0);
        classroom.getStudents()[0].addExamScore(80.0);
        classroom.getStudents()[0].addExamScore(100.0);
        // add test scores to John
        classroom.getStudents()[1].addExamScore(100.0);
        classroom.getStudents()[1].addExamScore(80.0);
        classroom.getStudents()[1].addExamScore(95.0);
        // add test scores to Jane
        classroom.getStudents()[2].addExamScore(100.0);
        classroom.getStudents()[2].addExamScore(80.0);
        classroom.getStudents()[2].addExamScore(95.0);


        System.out.println("==============Get Students==============");

        // print the result of classroom.getStudents
        for (Student student : classroom.getStudents()) {
            System.out.println(student);
        }
        System.out.println("==============Get Average Score==============");
        // print the result of classroom.getAverageExamScore
        System.out.println(classroom.getAverageExamScore());

        System.out.println("==============Get Students By Score==============");
        // print the result of classroom.getStudentsByScore
        for (Student student : classroom.getStudentsByScore()) {
            System.out.println(student);
        }
        System.out.println("==============Get Grade Book==============");
        // print the result of classroom.getGradeBook not printGradeBook method
        System.out.println(classroom.getGradeBook());




        System.out.println("==============Get exam scores==============");
        // print the result of student.getExamScores
        System.out.println(classroom.getStudents()[0].getExamScores());

        System.out.println("==============Get number of exams taken==============");
        // print the result of student.getNumberOfExamsTaken
        System.out.println(classroom.getStudents()[0].getNumberOfExamsTaken());

        System.out.println("==============get average exam score==============");
        // print the result of student.getAverageExamScore
        System.out.println(classroom.getStudents()[0].getAverageExamScore());

        System.out.println("==============set exam score==============");
        // print the result of student.setExamScore
        classroom.getStudents()[0].setExamScoreByExamNumber(1, 100.0);
        System.out.println(classroom.getStudents()[0].getExamScores());
         //call the toString method
        System.out.println("==============toString==============");
        System.out.println(classroom.getStudents()[0].toString());

        System.out.println("==============Remove Student John Day==============");
        // print the result of classroom.removeStudent
        classroom.removeStudent("John", "Day");
        for (Student student : classroom.getStudents()) {
            System.out.println(student);
        }

    }
}
