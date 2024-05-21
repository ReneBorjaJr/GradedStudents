package gradedstudentslab;

import java.util.ArrayList;

public class Student {
    private String firstName;
    private String lastName;
    private ArrayList<Double> examScores;

    public Student(String firstName, String lastName, ArrayList<Double> examScores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = examScores;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setExamScores(ArrayList<Double> examScores) {
        if (examScores == null) {
            this.examScores = null;
            return;
        }
        for (Double score : examScores) {
            if (score < 0 || score > 100) {
                return;
            } else {
                this.examScores = examScores;
            }
        }
    }

    public String getExamScores() {
        if (examScores == null) {
            return "null";
        }
        String result = "> Exam Scores:\n";
        for (int i = 0; i < examScores.size(); i++) {
            result += "\tExam " + (i + 1) + " -> " + examScores.get(i) + "\n";
        }
        return result;
    }

    public int getNumberOfExamsTaken() {
        if (examScores == null) {
            return 0;
        }
        return examScores.size();
    }

    public void addExamScore(double examScore) {
        if (examScore < 0 || examScore > 100) {
            return;
        }
        examScores.add(examScore);
    }

    public void setExamScoreByExamNumber(int examNumber, double newScore) {
        if (newScore < 0 || newScore > 100) {
            return;
        }
        if (examNumber > examScores.size() || examNumber < 1) {
            return;
        }
        int number = examNumber - 1;
        examScores.set(number, newScore);
    }

    public double getAverageExamScore() {
        double sum = 0.0;
        int count = 0;
        for (Double score : examScores) {
            if (score != null) {
                sum += score;
                count++;
            }
        }
        if (count == 0) {
            return 0.0;
        }
        double average = sum / count;
        return Math.round(average * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Student Name: " + firstName + " " + lastName + "\n" +
              "> Average Score: " + getAverageExamScore() + "\n" + getExamScores();

    }
}
