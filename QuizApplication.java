import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApplication {

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                new String[]{"London", "Paris", "Rome"}, 1));
        questions.add(new Question("Who wrote 'Hamlet'?",
                new String[]{"Shakespeare", "Poe", "Dostoevsky"}, 0));
        questions.add(new Question("What is the powerhouse of the cell?",
                new String[]{"Mitochondria", "Nucleus", "Ribosome"}, 0));

        Quiz quiz = new Quiz(questions);
        quiz.conductQuiz();
        quiz.displayScore();
    }
}

class Question {
    private String text;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String text, String[] options, int correctAnswerIndex) {
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctAnswerIndex;
    }

    public void displayQuestion() {
        System.out.println(text);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public String[] getOptions() {
        return options;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void conductQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();
            int userAnswer = getUserAnswer(scanner, question.getOptions().length);
            if (question.checkAnswer(userAnswer - 1)) {
                score++;
            }
            System.out.println(); // Blank line for clarity
        }
        scanner.close();
    }

    private int getUserAnswer(Scanner scanner, int numOptions) {
        int userAnswer;
        while (true) {
            try {
                System.out.print("Enter the number of your answer: ");
                userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= numOptions) {
                    break;
                } else {
                    System.out.println("Please enter a valid option number.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid option number.");
                scanner.nextLine(); // Clear the invalid input from scanner
            }
        }
        return userAnswer;
    }

    public void displayScore() {
        System.out.println("You scored " + score + " out of " + questions.size() + ".");
    }
}