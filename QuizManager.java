package edutok.person2;

import java.util.ArrayList;
import java.util.List;

// Creator: Person 2 
// Tester: Person 3

public class QuizManager {
    private List<QuizQuestion> questions;
    private int currentIndex;
    private int correctCount;

    public QuizManager() {
        questions = new ArrayList<QuizQuestion>();
        currentIndex = 0;
        correctCount = 0;
    }

    public void loadQuestions() {
        questions.clear();
        currentIndex = 0;
        correctCount = 0;

        // 10 Multiple Choice
        questions.add(new MultipleChoiceQuestion(
            "SDG 4 focuses on what?",
            new String[]{"A) Quality Education", "B) Clean Water", "C) No Poverty", "D) Climate Action"},
            "a"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Which is a good study habit?",
            new String[]{"A) Procrastinate", "B) Review notes regularly", "C) Skip classes", "D) Sleep 2 hours"},
            "b"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Which tool is used to compile Java files?",
            new String[]{"A) java", "B) javac", "C) javadoc", "D) jar"},
            "b"
        ));
        questions.add(new MultipleChoiceQuestion(
            "OOP stands for?",
            new String[]{"A) Object-Oriented Programming", "B) Open Office Protocol", "C) Online Operating Program", "D) Object Output Process"},
            "a"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Which keyword creates inheritance in Java?",
            new String[]{"A) implements", "B) import", "C) extends", "D) static"},
            "c"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Which is an interface keyword in Java?",
            new String[]{"A) interface", "B) struct", "C) protocol", "D) module"},
            "a"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Which collection is dynamic in size?",
            new String[]{"A) Array", "B) ArrayList", "C) int[]", "D) String[]"},
            "b"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Which is used for GUI in your project?",
            new String[]{"A) Swing", "B) TensorFlow", "C) React", "D) Laravel"},
            "a"
        ));
        questions.add(new MultipleChoiceQuestion(
            "Which statement is for decision-making?",
            new String[]{"A) for", "B) if", "C) import", "D) class"},
            "b"
        ));
        questions.add(new MultipleChoiceQuestion(
            "What should be avoided for score storage?",
            new String[]{"A) Text file", "B) Array", "C) Database", "D) Hardcoding"},
            "d"
        ));

        // 10 True/False
        questions.add(new TrueFalseQuestion("Java is a case-sensitive language. (true/false)", "true"));
        questions.add(new TrueFalseQuestion("An interface can contain method declarations. (true/false)", "true"));
        questions.add(new TrueFalseQuestion("SDG 4 is about life below water. (true/false)", "false"));
        questions.add(new TrueFalseQuestion("A class can implement multiple interfaces. (true/false)", "true"));
        questions.add(new TrueFalseQuestion("The keyword 'extends' is used for inheritance. (true/false)", "true"));
        questions.add(new TrueFalseQuestion("Hardcoding user scores is allowed in this project. (true/false)", "false"));
        questions.add(new TrueFalseQuestion("Polymorphism allows one interface with many implementations. (true/false)", "true"));
        questions.add(new TrueFalseQuestion("A quiz with 5 questions meets project requirement. (true/false)", "false"));
        questions.add(new TrueFalseQuestion("Command line execution is required for this project. (true/false)", "true"));
        questions.add(new TrueFalseQuestion("Testing and debugging proof must be submitted. (true/false)", "true"));
    }

    public QuizQuestion getCurrentQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        if (currentIndex < 0 || currentIndex >= questions.size()) {
            return null;
        }
        return questions.get(currentIndex);
    }

    public boolean hasNext() {
        return currentIndex < questions.size() - 1;
    }

    public void nextQuestion() {
        if (hasNext()) {
            currentIndex++;
        }
    }

    public void submitAnswer(String answer) {
        QuizQuestion q = getCurrentQuestion();
        if (q != null && q.checkAnswer(answer)) {
            correctCount++;
        }
    }

    public int getScorePercent() {
        if (questions.isEmpty()) {
            return 0;
        }
        return (correctCount * 100) / questions.size();
    }

    public String getMotivationalMessage() {
        int score = getScorePercent();
        if (score >= 80) {
            return "Outstanding!";
        } else if (score >= 60) {
            return "That's good!";
        } else if (score >= 40) {
            return "Good try!";
        } else if (score >= 20) {
            return "You can do better!";
        } else {
            return "Don't give up!";
        }
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getCurrentQuestionNumber() {
        return currentIndex + 1;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void resetQuiz() {
        currentIndex = 0;
        correctCount = 0;
    }
}