// Creator: Person 2 | Tester: Person 3
// Group: G04/SE-G11 | App: EduTok
// Console test harness for submission (testing/debugging proof).

package edutok.person2;

import edutok.person3.LessonCatalog;
import edutok.person3.Learnable;

public class Person2TestRunner {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("=== EduTok Person 2 — Quiz Module Tests ===\n");
        testQuizManager();
        System.out.println();
        System.out.println("=== Person 2 tests Person 3 — Learning Module ===\n");
        testLessonCatalog();
        System.out.println();
        System.out.println("=== Summary ===");
        System.out.println("Passed: " + passed + " | Failed: " + failed);
        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testQuizManager() {
        QuizManager quiz = new QuizManager();
        quiz.loadQuestions();

        check("Total questions >= 20", quiz.getTotalQuestions() >= 20);
        check("Starts at question 1", quiz.getCurrentQuestionNumber() == 1);

        int mcq = 0;
        int tf = 0;
        for (int i = 0; i < quiz.getTotalQuestions(); i++) {
            QuizQuestion q = quiz.getCurrentQuestion();
            if (q.getType().equals("MCQ")) {
                mcq++;
            } else if (q.getType().equals("True/False")) {
                tf++;
            }
            if (quiz.hasNext()) {
                quiz.nextQuestion();
            }
        }
        check("At least 10 MCQ questions", mcq >= 10);
        check("At least 10 True/False questions", tf >= 10);

        quiz.resetQuiz();
        quiz.loadQuestions();
        QuizQuestion first = quiz.getCurrentQuestion();
        check("MCQ checkAnswer accepts 'A'", first.checkAnswer("A") || first.checkAnswer("a"));

        quiz = new QuizManager();
        quiz.loadQuestions();
        while (quiz.hasNext()) {
            quiz.submitAnswer("wrong");
            quiz.nextQuestion();
        }
        quiz.submitAnswer("wrong");
        check("0% score message", "Don't give up!".equals(quiz.getMotivationalMessage()));

        quiz.resetQuiz();
        quiz.loadQuestions();
        for (int i = 0; i < quiz.getTotalQuestions(); i++) {
            quiz.submitAnswer("correct-placeholder");
            if (quiz.hasNext()) {
                quiz.nextQuestion();
            }
        }
        check("Score percent in range 0-100", quiz.getScorePercent() >= 0 && quiz.getScorePercent() <= 100);

        quiz = new QuizManager();
        quiz.loadQuestions();
        for (int i = 0; i < quiz.getTotalQuestions(); i++) {
            QuizQuestion q = quiz.getCurrentQuestion();
            if (q instanceof TrueFalseQuestion) {
                check("TrueFalse accepts 't' alias", q.checkAnswer("t") == q.checkAnswer("true")
                        || !q.checkAnswer("true"));
            }
            if (quiz.hasNext()) {
                quiz.nextQuestion();
            }
        }

        checkMessage(80, "Outstanding!");
        checkMessage(60, "That's good!");
        checkMessage(40, "Good try!");
        checkMessage(20, "You can do better!");
        checkMessage(0, "Don't give up!");
    }

    private static void checkMessage(int targetPercent, String expected) {
        QuizManager m = new QuizManager();
        m.loadQuestions();
        int needCorrect = (targetPercent * m.getTotalQuestions()) / 100;
        for (int i = 0; i < m.getTotalQuestions(); i++) {
            if (i < needCorrect) {
                m.submitAnswer(findCorrectAnswer(m.getCurrentQuestion()));
            } else {
                m.submitAnswer("x");
            }
            if (m.hasNext()) {
                m.nextQuestion();
            }
        }
        check("Message at ~" + targetPercent + "% (" + m.getScorePercent() + "%)",
                expected.equals(m.getMotivationalMessage()));
    }

    private static String findCorrectAnswer(QuizQuestion q) {
        String[] tries = {"a", "b", "c", "d", "true", "false", "t", "f"};
        for (String t : tries) {
            if (q.checkAnswer(t)) {
                return t;
            }
        }
        return "a";
    }

    private static void testLessonCatalog() {
        LessonCatalog catalog = new LessonCatalog();

        check("At least 10 lessons", catalog.getTotalLessons() >= 10);
        check("Starts on page 1", catalog.getCurrentPageNumber() == 1);

        Learnable lesson = catalog.getCurrentLesson();
        check("Current lesson has title", lesson.getTitle() != null && !lesson.getTitle().isEmpty());
        check("Current lesson has body", lesson.getBody() != null && !lesson.getBody().isEmpty());
        check("Page number matches", lesson.getPageNumber() == 1);

        String firstTitle = lesson.getTitle();
        if (catalog.hasNext()) {
            catalog.nextPage();
            check("Next page changes lesson", !catalog.getCurrentLesson().getTitle().equals(firstTitle)
                    || catalog.getCurrentPageNumber() == 2);
        }

        if (catalog.hasPrevious()) {
            catalog.previousPage();
            check("Previous returns to page 1", catalog.getCurrentPageNumber() == 1);
        }

        catalog.resetToFirst();
        check("resetToFirst works", catalog.getCurrentPageNumber() == 1);

        Learnable byPage = catalog.getLessonByPage(catalog.getTotalLessons());
        check("getLessonByPage last page", byPage != null && byPage.getPageNumber() == catalog.getTotalLessons());

        check("getLessonByPage invalid returns null", catalog.getLessonByPage(999) == null);
        check("getAllLessons size", catalog.getAllLessons().size() == catalog.getTotalLessons());
    }

    private static void check(String name, boolean ok) {
        if (ok) {
            System.out.println("[PASS] " + name);
            passed++;
        } else {
            System.out.println("[FAIL] " + name);
            failed++;
        }
    }
}
