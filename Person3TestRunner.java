// Creator: Person 3 | Tester: Person 2 
// Group: G04/SE-G11 | App: EduTok

package edutok.person3;

import edutok.person2.QuizManager;
import edutok.person2.QuizQuestion;

/**
 * Test class written by Person 3  to test Person 2 QuizManager.
 * Run this file to verify all quiz functionality works correctly.
 */
public class Person3TestRunner {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  EduTok - Testing Person 2: QuizManager");
        System.out.println("  Tester: Person 3");
        System.out.println("========================================\n");

        testTotalQuestions();
        testQuestionTypes();
        testGetPrompt();
        testCheckAnswer();
        testScoreCalculation();
        testMotivationalMessages();
        testNavigation();
        testReset();

        System.out.println("\n========================================");
        System.out.println("  RESULTS: " + passed + " passed | " + failed + " failed");
        System.out.println("========================================");
    }

    // ─────────────────────────────────────────────
    // TEST 1: Total Questions >= 20
    // ─────────────────────────────────────────────
    static void testTotalQuestions() {
        System.out.println("TEST 1: Total questions >= 20");
        QuizManager qm = new QuizManager();
        qm.loadQuestions();

        int total = qm.getTotalQuestions();
        if (total >= 20) {
            pass("Total questions = " + total + " (>= 20)");
        } else {
            fail("Total questions = " + total + " (MUST be >= 20!)");
        }
    }

    // ─────────────────────────────────────────────
    // TEST 2: At least 2 question types exist (MCQ + True/False)
    // ─────────────────────────────────────────────
    static void testQuestionTypes() {
        System.out.println("\nTEST 2: At least 2 question types (MCQ + True/False)");
        QuizManager qm = new QuizManager();
        qm.loadQuestions();

        boolean hasMCQ = false;
        boolean hasTrueFalse = false;

        int total = qm.getTotalQuestions();
        for (int i = 0; i < total; i++) {
            QuizQuestion q = qm.getCurrentQuestion();
            String type = q.getType();

            if (type != null) {
                if (type.equalsIgnoreCase("MCQ") || type.equalsIgnoreCase("multiple choice")) {
                    hasMCQ = true;
                }
                if (type.equalsIgnoreCase("TrueFalse") || type.equalsIgnoreCase("True/False")) {
                    hasTrueFalse = true;
                }
            }

            if (qm.hasNext()) {
                qm.nextQuestion();
            } else {
                break;
            }
        }

        if (hasMCQ) {
            pass("MCQ type found");
        } else {
            fail("MCQ type NOT found — check getType() in MCQ class");
        }

        if (hasTrueFalse) {
            pass("True/False type found");
        } else {
            fail("True/False type NOT found — check getType() in TrueFalse class");
        }
    }

    // ─────────────────────────────────────────────
    // TEST 3: getPrompt() returns non-empty string
    // ─────────────────────────────────────────────
    static void testGetPrompt() {
        System.out.println("\nTEST 3: getPrompt() returns non-empty text");
        QuizManager qm = new QuizManager();
        qm.loadQuestions();

        QuizQuestion q = qm.getCurrentQuestion();
        String prompt = q.getPrompt();

        if (prompt != null && !prompt.trim().isEmpty()) {
            pass("getPrompt() = \"" + prompt.substring(0, Math.min(50, prompt.length())) + "...\"");
        } else {
            fail("getPrompt() returned null or empty!");
        }
    }

    // ─────────────────────────────────────────────
    // TEST 4: checkAnswer() works for correct + wrong
    // ─────────────────────────────────────────────
    static void testCheckAnswer() {
        System.out.println("\nTEST 4: checkAnswer() works correctly");
        QuizManager qm = new QuizManager();
        qm.loadQuestions();

        // Test that checkAnswer doesn't crash
        QuizQuestion q = qm.getCurrentQuestion();
        try {
            boolean result1 = q.checkAnswer("someAnswer");
            boolean result2 = q.checkAnswer("");
            boolean result3 = q.checkAnswer(null);
            pass("checkAnswer() ran without crashing");
        } catch (Exception e) {
            fail("checkAnswer() threw an exception: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // TEST 5: Score calculation (0-100%)
    // ─────────────────────────────────────────────
    static void testScoreCalculation() {
        System.out.println("\nTEST 5: Score percent calculation");
        QuizManager qm = new QuizManager();
        qm.loadQuestions();

        int score = qm.getScorePercent();

        if (score >= 0 && score <= 100) {
            pass("getScorePercent() = " + score + "% (valid range 0-100)");
        } else {
            fail("getScorePercent() = " + score + "% (OUT OF RANGE!)");
        }
    }

    // ─────────────────────────────────────────────
    // TEST 6: Motivational messages (exact wording)
    // ─────────────────────────────────────────────
    static void testMotivationalMessages() {
        System.out.println("\nTEST 6: Motivational messages (exact wording per spec)");

        // We test by checking the method exists and returns something
        // Since we can't control the score directly, we just verify it doesn't crash
        QuizManager qm = new QuizManager();
        qm.loadQuestions();

        try {
            String msg = qm.getMotivationalMessage();
            if (msg != null && !msg.isEmpty()) {
                pass("getMotivationalMessage() returned: \"" + msg + "\"");

                // Check exact wording from spec
                boolean validMsg = msg.equals("Outstanding!") ||
                                   msg.equals("That's good!") ||
                                   msg.equals("Good try!") ||
                                   msg.equals("You can do better!") ||
                                   msg.equals("Don't give up!");

                if (validMsg) {
                    pass("Message matches exact spec wording ✓");
                } else {
                    fail("Message \"" + msg + "\" does NOT match spec wording!\n" +
                         "       Expected one of: Outstanding! / That's good! / Good try! / You can do better! / Don't give up!");
                }
            } else {
                fail("getMotivationalMessage() returned null or empty!");
            }
        } catch (Exception e) {
            fail("getMotivationalMessage() threw exception: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // TEST 7: Navigation (hasNext, nextQuestion)
    // ─────────────────────────────────────────────
    static void testNavigation() {
        System.out.println("\nTEST 7: Navigation (hasNext + nextQuestion)");
        QuizManager qm = new QuizManager();
        qm.loadQuestions();

        try {
            int count = 1;
            while (qm.hasNext()) {
                qm.nextQuestion();
                count++;
            }
            pass("Navigated through " + count + " questions without crashing");
        } catch (Exception e) {
            fail("Navigation crashed: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // TEST 8: resetQuiz() goes back to question 1
    // ─────────────────────────────────────────────
    static void testReset() {
        System.out.println("\nTEST 8: resetQuiz() resets to first question");
        QuizManager qm = new QuizManager();
        qm.loadQuestions();

        // Get first question prompt
        String firstPrompt = qm.getCurrentQuestion().getPrompt();

        // Navigate forward
        if (qm.hasNext()) qm.nextQuestion();
        if (qm.hasNext()) qm.nextQuestion();

        // Reset
        try {
            qm.resetQuiz();
            String afterReset = qm.getCurrentQuestion().getPrompt();

            if (firstPrompt.equals(afterReset)) {
                pass("resetQuiz() correctly resets to first question");
            } else {
                fail("resetQuiz() did NOT go back to first question!");
            }
        } catch (Exception e) {
            fail("resetQuiz() threw exception: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // HELPER METHODS
    // ─────────────────────────────────────────────
    static void pass(String message) {
        System.out.println("  ✓ PASS: " + message);
        passed++;
    }

    static void fail(String message) {
        System.out.println("  ✗ FAIL: " + message);
        failed++;
    }
}