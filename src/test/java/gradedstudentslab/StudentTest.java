package gradedstudentslab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class StudentTest {
    
    private Student student;
    
    @BeforeEach
    void setUp() {
        student = new Student("John", "Doe", new ArrayList<>());
    }
    
    @Test
    void testGetExamScores_NoScores() {
        String expected = "> Exam Scores:\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testGetExamScores_OneScore() {
        student.addExamScore(100.0);
        String expected = "> Exam Scores:\n\tExam 1 -> 100.0\n";
        assertEquals(expected, student.getExamScores());

    }

    @Test
    void testGetExamScores_MultipleScores() {
        student.addExamScore(100.0);
        student.addExamScore(80.0);
        student.addExamScore(60.0);
        String expected = "> Exam Scores:\n\tExam 1 -> 100.0\n\tExam 2 -> 80.0\n\tExam 3 -> 60.0\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testSetExamScores_NonNullList() {
        ArrayList<Double> examScores = new ArrayList<>();
        examScores.add(100.0);
        examScores.add(200.0);
        examScores.add(300.0);
        student.setExamScores(examScores);
        String expected = "> Exam Scores:\n\tExam 1 -> 100.0\n\tExam 2 -> 200.0\n\tExam 3 -> 300.0\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testSetExamScores_EmptyList() {
        ArrayList<Double> examScores = new ArrayList<>();
        student.setExamScores(examScores);
        String expected = "> Exam Scores:\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testSetExamScores_NullList() {
        student.setExamScores(null);
        String expected = "null";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testGetNumberOfExamsTaken() {
        student.addExamScore(100.0);
        student.addExamScore(80.0);
        student.addExamScore(60.0);
        assertEquals(3, student.getNumberOfExamsTaken());
    }

    @Test
    void testGetNumberOfExamsTaken_EmptyList() {
        assertEquals(0, student.getNumberOfExamsTaken());
    }

    @Test
    void testNumberOfExamsTaken_nullList() {
        student.setExamScores(null);
        assertEquals(0, student.getNumberOfExamsTaken());
    }

    @Test
    void testAddExamScore() {
        student.addExamScore(100.0);
        String expected = "> Exam Scores:\n\tExam 1 -> 100.0\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testAddExamScore_Negative() {
        student.addExamScore(-100.0);
        String expected = "> Exam Scores:\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testGetAverageExamScore() {
        student.addExamScore(97.0);
        student.addExamScore(56.0);
        student.addExamScore(83.0);
        assertEquals(78.67, student.getAverageExamScore());
    }

    @Test
    void testGetAverageExamScore_EmptyList() {
        assertEquals(0, student.getAverageExamScore());
    }

    @Test
    void testGetAverageExamScore_Negative() {
        student.addExamScore(-100.0);
        assertEquals(0, student.getAverageExamScore());
    }

    @Test
    void testSetExamScoreByExamNumber() {
        student.addExamScore(100.0);
        student.addExamScore(80.0);
        student.addExamScore(70.0);
        student.setExamScoreByExamNumber(2, 50.0);
        String expected = "> Exam Scores:\n\tExam 1 -> 100.0\n\tExam 2 -> 50.0\n\tExam 3 -> 70.0\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testSetExamScoreByExamNumber_Invalid() {
        student.addExamScore(100.0);
        student.addExamScore(60.0);
        student.addExamScore(87.0);
        student.setExamScoreByExamNumber(1, 250.0);
        String expected = "> Exam Scores:\n\tExam 1 -> 100.0\n\tExam 2 -> 60.0\n\tExam 3 -> 87.0\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testSetExamScoreByExamNumber_Negative() {
        student.addExamScore(100.0);
        student.addExamScore(87.0);
        student.addExamScore(60.0);
        student.setExamScoreByExamNumber(2, -250.0);
        String expected = "> Exam Scores:\n\tExam 1 -> 100.0\n\tExam 2 -> 87.0\n\tExam 3 -> 60.0\n";
        assertEquals(expected, student.getExamScores());
    }

    @Test
    void testToString() {
        //Given
        student.addExamScore(100.0);
        student.addExamScore(59.0);
        student.addExamScore(86.0);

        //When
        String result = student.toString();

        //Then
        assertTrue(result.contains("Student Name: John Doe"));
        assertTrue(result.contains("> Average Score: 81.67"));
        assertTrue(result.contains("> Exam Scores:\n\tExam 1 -> 100.0\n\tExam 2 -> 59.0\n\tExam 3 -> 86.0\n"));
        assertTrue(result.matches("Student Name: John Doe\n> Average Score: 81.67\n> Exam Scores:\n\tExam 1 -> 100.0\n\tExam 2 -> 59.0\n\tExam 3 -> 86.0\n"));
    }
}