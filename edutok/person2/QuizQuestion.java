package edutok.person2;

// Creator: Person 2
// Tester: Person 3

public interface QuizQuestion {
    String getPrompt();
    boolean checkAnswer(String answer);
    String getType();
}
