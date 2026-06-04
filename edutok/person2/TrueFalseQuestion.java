package edutok.person2;

// Creator: Person 2 
// Tester: Person 3

public class TrueFalseQuestion extends AbstractQuestion {

    public TrueFalseQuestion(String prompt, String correctAnswer) {
        super(prompt, correctAnswer);
    }

    @Override
    public boolean checkAnswer(String answer) {
        String a = normalize(answer);
        // accepts true/false or t/f
        if (a.equals("t")) {
            a = "true";
        } else if (a.equals("f")) {
            a = "false";
        }
        return a.equals(normalize(correctAnswer));
    }

    @Override
    public String getType() {
        return "TRUE_FALSE";
    }
}
