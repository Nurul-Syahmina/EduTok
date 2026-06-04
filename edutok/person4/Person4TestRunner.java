// Creator: Person 1 | Tester: Person 4
// Group: G04/SE-G11 | App: EduTok

package edutok.person4;

import edutok.person1.MainApplication;
import edutok.person1.Navigable;

public class Person4TestRunner {

    private static int passed = 0;
    private static int failed = 0;

    private static void pass(String testName) {
        System.out.println("[PASS] " + testName);
        passed++;
    }

    private static void fail(String testName) {
        System.out.println("[FAIL] " + testName);
        failed++;
    }

    public static void main(String[] args) {

        System.out.println("=== Person 4 tests Person 1 ===");
        System.out.println();

        try {

            MainApplication app = new MainApplication();

            pass("MainApplication launches");

            if (app instanceof Navigable) {
                pass("Implements Navigable");
            } else {
                fail("Implements Navigable");
            }

            // Test String navigation
            app.showScreen("home");
            pass("showScreen(\"home\") works");

            app.showScreen("learn");
            pass("showScreen(\"learn\") works");

            app.showScreen("quiz");
            pass("showScreen(\"quiz\") works");

            app.showScreen("progress");
            pass("showScreen(\"progress\") works");

            app.showScreen("leaderboard");
            pass("showScreen(\"leaderboard\") works");

            // Test overloaded int navigation
            app.showScreen(0);
            pass("showScreen(0) works");

            app.showScreen(1);
            pass("showScreen(1) works");

            app.showScreen(2);
            pass("showScreen(2) works");

            app.showScreen(3);
            pass("showScreen(3) works");

            app.showScreen(4);
            pass("showScreen(4) works");

        } catch (Exception e) {

            fail("Application launch");

            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
}