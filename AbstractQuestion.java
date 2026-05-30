package edutok.person2;

// Creator: Person 2
// Tester: Person 3

public abstract class AbstractQuestion implements QuizQuestion {
    protected String prompt;
    protected String correctAnswer;

    public AbstractQuestion(String prompt, String correctAnswer) {
        this.prompt = prompt;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getPrompt() {
        return prompt;
    }

    // helper for subclasses
    protected String normalize(String text) {
        if (text == null) {
            return "";
        }
        return text.trim().toLowerCase();
    }
}