package gradedstudentslab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ClassroomTest {

    private Classroom classroom;

    @BeforeEach
    void setUp() {
        classroom = new Classroom(4);
        classroom.addStudent(new Student("John", "Borja", new ArrayList<>()));
        classroom.addStudent(new Student("Ryan", "Yerger", new ArrayList<>()));
        classroom.addStudent(new Student("Devin", "Fields", new ArrayList<>()));
    }

    @Test
    void testGetStudents() {
        assertEquals(3, classroom.getStudents().length);
    }

    @Test
    void testGetAverageExamScore() {
        //given
        classroom.getStudents()[0].addExamScore(100.0);
        classroom.getStudents()[0].addExamScore(80.0);
        classroom.getStudents()[1].addExamScore(97.0);
        classroom.getStudents()[1].addExamScore(65.0);
        classroom.getStudents()[2].addExamScore(45.0);
        classroom.getStudents()[2].addExamScore(75.0);

        //when
        double average = classroom.getAverageExamScore();

        //then
        assertEquals(77, average);

    }

    @Test
    void testAddStudent() {
        //when
        classroom.addStudent(new Student("Giselle", "Gonzalez", new ArrayList<>()));

        //then
        assertEquals(4, classroom.getStudents().length);
    }

    @Test
    void testRemoveStudent() {
        //when
        classroom.removeStudent("Ryan", "Yerger");

        //then
        assertEquals(2, classroom.getStudents().length);
    }

    @Test
    void testGetStudentsByScoreDifferentScores() {
        //given
        Student student1 = new Student("Alice", "Smith", new ArrayList<>(Arrays.asList(80.0, 75.0))); //average is 77.5
        Student student2 = new Student("Bob", "Johnson", new ArrayList<>(Arrays.asList(85.0, 90.0))); // average is 87.5
        Student student3 = new Student("Charlie", "Brown", new ArrayList<>(Arrays.asList(90.0, 100.0))); //average is 95.0
        Classroom classroom1 = new Classroom(new Student[]{student1, student2, student3});

        //when
        Student[] students = classroom1.getStudentsByScore();

        //then
        assertEquals("Charlie", students[0].getFirstName());
        assertEquals("Bob", students[1].getFirstName());
        assertEquals("Alice", students[2].getFirstName());

    }

    @Test
    void testGetStudentsByScoreSameScores() {
        //given
        Student student1 = new Student("Alice", "Smith", new ArrayList<>(Arrays.asList(80.0, 100.0))); //average is 90.0
        Student student2 = new Student("Bob", "Johnson", new ArrayList<>(Arrays.asList(80.0, 90.0))); // average is 85.0
        Student student3 = new Student("Charlie", "Brown", new ArrayList<>(Arrays.asList(80.0, 100.0))); //average is 90.0
        Classroom classroom1 = new Classroom(new Student[]{student1, student2, student3});

        //when
        Student[] students = classroom1.getStudentsByScore();

        //then
        assertEquals("Charlie", students[0].getFirstName());
        assertEquals("Alice", students[1].getFirstName());
        assertEquals("Bob", students[2].getFirstName());
    }

    @Test
    void testGetStudentsByScoreNoStudents() {
        //given
        Classroom classroom1 = new Classroom();

        //when
        Student[] students = classroom1.getStudentsByScore();

        //then
        assertEquals(0, students.length);
    }

    @Test
    public void testGetGradeBook() {
        // Test with students having different average exam scores
        Classroom classroom = new Classroom();
        Student student1 = new Student("John", "Doe", new ArrayList<>());
        student1.addExamScore(80.0);
        student1.addExamScore(90.0);
        Student student2 = new Student("Jane", "Smith", new ArrayList<>());
        student2.addExamScore(70.0);
        student2.addExamScore(80.0);
        Student student3 = new Student("Bob", "Johnson", new ArrayList<>());
        student3.addExamScore(90.0);
        student3.addExamScore(100.0);
        classroom.addStudent(student1);
        classroom.addStudent(student2);
        classroom.addStudent(student3);
        Map<Student, String> expectedGradeBook = new HashMap<>();
        expectedGradeBook.put(student3, "> Student grade: A\n\n");
        expectedGradeBook.put(student1, "> Student grade: C\n\n");
        expectedGradeBook.put(student2, "> Student grade: D\n\n");
        assertEquals(expectedGradeBook, classroom.getGradeBook());
    }

    @Test
    public void testGetGradeBookWithEmptyClassroom() {
        // Test with an empty classroom
        Classroom classroom = new Classroom();
        assertEquals(new HashMap<>(), classroom.getGradeBook());
    }

    @Test
    public void testGetGradeBookWithNullStudents() {
        // Test with null students in the classroom
        Classroom classroom = new Classroom();
        classroom.addStudent(null);
        classroom.addStudent(null);
        classroom.addStudent(null);
        assertEquals(new HashMap<>(), classroom.getGradeBook());
    }

}