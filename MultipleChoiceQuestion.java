package edutok.person2;

// Creator: Person 2 
// Tester: Person 3

public class MultipleChoiceQuestion extends AbstractQuestion {
    private String[] options;

    public MultipleChoiceQuestion(String prompt, String[] options, String correctAnswer) {
        super(prompt, correctAnswer);
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return normalize(answer).equals(normalize(correctAnswer));
    }

    @Override
    public String getType() {
        return "MULTIPLE_CHOICE";
    }
}