// Creator: Person 1 | Tester: Person 2
// Group: G04/SE-G11 | App: EduTok

package edutok.person1;

import javax.swing.*;
import java.awt.*;
import edutok.person2.QuizManager;
import edutok.person2.QuizQuestion;
import edutok.person2.MultipleChoiceQuestion;
import edutok.person3.LessonCatalog;
import edutok.person3.Learnable;
import edutok.person4.UserProgress;
import edutok.person4.Reward;

// Main application class
public class MainApplication extends JFrame implements Navigable {

    // Layout manager for switching between screens
    private CardLayout cardLayout;

    // Main container that holds all screens
    private JPanel mainPanel;

    // Module objects from other team members
    private QuizManager quizManager;
    private LessonCatalog lessonCatalog;
    private UserProgress progress;

    // Quiz screen components
    private JTextArea questionArea;
    private JTextField answerField;

    // Learning screen components
    private JLabel lessonTitleLabel;
    private JTextArea lessonBodyArea;
    private JLabel lessonImageLabel;

    // Progress and leaderboard components
    private JTextArea progressArea;
    private JTextArea leaderboardArea;

    // Constructor
    public MainApplication() {

        setTitle("EduTok");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        quizManager = new QuizManager();
        quizManager.loadQuestions();

        lessonCatalog = new LessonCatalog();

        progress = new UserProgress();

        try {
            progress.load();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        createScreens();

        add(mainPanel);

        setVisible(true);
    }


    /*
     * Creates all application screens and
     * registers them with CardLayout.
     */
    private void createScreens() {

        JPanel homePanel = createHomePanel();
        JPanel learnPanel = createLearnPanel();
        JPanel quizPanel = createQuizPanel();
        JPanel progressPanel = createProgressPanel();
        JPanel leaderboardPanel = createLeaderboardPanel();

        mainPanel.add(homePanel, "home");
        mainPanel.add(learnPanel, "learn");
        mainPanel.add(quizPanel, "quiz");
        mainPanel.add(progressPanel, "progress");
        mainPanel.add(leaderboardPanel, "leaderboard");
    }

    /*
     * Creates the home screen containing
     * navigation buttons to all modules.
     */
    private JPanel createHomePanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        JLabel title = new JLabel("EduTok", SwingConstants.CENTER);

        JButton learnBtn = new JButton("Learn");
        JButton quizBtn = new JButton("Quiz");
        JButton progressBtn = new JButton("Progress");
        JButton leaderboardBtn = new JButton("Leaderboard");
        JButton exitBtn = new JButton("Exit");

        // Navigate to Learning Module
        learnBtn.addActionListener(e -> {
            lessonCatalog.resetToFirst();
            updateLessonDisplay();
            showScreen("learn");
        });

        // Navigate to Quiz Module
        quizBtn.addActionListener(e -> showScreen("quiz"));

        // Navigate to Progress Module
        progressBtn.addActionListener(e -> {
            updateProgressDisplay();
            showScreen("progress");
        });

        // Navigate to Leaderboard Module
        leaderboardBtn.addActionListener(e -> {
            updateLeaderboardDisplay();
            showScreen("leaderboard");
        });

        // Exit application
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(title);
        panel.add(learnBtn);
        panel.add(quizBtn);
        panel.add(progressBtn);
        panel.add(leaderboardBtn);
        panel.add(exitBtn);

        return panel;
    }

    /*
     * Creates the quiz screen.
     * Displays questions and allows users
     * to submit answers.
     */
    private JPanel createQuizPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        questionArea = new JTextArea();

        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        updateQuestionDisplay();

        answerField = new JTextField();

        JButton submitButton = new JButton("Submit");

        JButton backBtn = new JButton("Back");

        backBtn.addActionListener(e -> showScreen("home"));

        submitButton.addActionListener(e -> {

            // Get answer entered by user
            String answer = answerField.getText().trim();

            quizManager.submitAnswer(answer);

            if (quizManager.hasNext()) {

                quizManager.nextQuestion();

                updateQuestionDisplay();

                answerField.setText("");

            } else {

                int score = quizManager.getScorePercent();

                progress.recordQuiz(score);

                try {
                    progress.save();
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }

                String message = quizManager.getMotivationalMessage();

                JOptionPane.showMessageDialog(
                        this,
                        "Score: " + score + "%\n" + message
                );

                quizManager.resetQuiz();

                updateQuestionDisplay();

                answerField.setText("");

                showScreen("home");
            }
        });

        JPanel bottomPanel = new JPanel(new GridLayout(4,1));

        JLabel answerLabel = new JLabel("Enter Answer:");

        bottomPanel.add(answerLabel);
        bottomPanel.add(answerField);
        bottomPanel.add(submitButton);
        bottomPanel.add(backBtn);

        panel.add(new JScrollPane(questionArea),BorderLayout.CENTER);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    /*
     * Updates the quiz display with the
     * current question and answer choices.
     */
    private void updateQuestionDisplay() {

        QuizQuestion q = quizManager.getCurrentQuestion();

        String questionText =
        "Question "
        + quizManager.getCurrentQuestionNumber()
        + " / "
        + quizManager.getTotalQuestions()
        + "\n\n"
        + q.getPrompt();

        if (q instanceof MultipleChoiceQuestion) {

            MultipleChoiceQuestion mcq =
                    (MultipleChoiceQuestion) q;

            for (String option : mcq.getOptions()) {

                questionText += "\n" + option;
            }
        }

        questionArea.setText(questionText);
    }

    /*
     * Creates the learning screen with
     * lesson content, image, and navigation buttons.
     */
    private JPanel createLearnPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        lessonTitleLabel = new JLabel("", SwingConstants.CENTER);

        lessonBodyArea = new JTextArea();
        lessonBodyArea.setEditable(false);
        lessonBodyArea.setLineWrap(true);
        lessonBodyArea.setWrapStyleWord(true);

        lessonImageLabel = new JLabel();

        JButton prevBtn = new JButton("Previous");
        JButton nextBtn = new JButton("Next");
        JButton backBtn = new JButton("Back");

        prevBtn.addActionListener(e -> {

            if (lessonCatalog.hasPrevious()) {
                lessonCatalog.previousPage();
                updateLessonDisplay();
            }
        });

        nextBtn.addActionListener(e -> {

            if (lessonCatalog.hasNext()) {
                lessonCatalog.nextPage();
                updateLessonDisplay();
            }
        });

        backBtn.addActionListener(e -> showScreen("home"));

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(prevBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(backBtn);

        panel.add(lessonTitleLabel,BorderLayout.NORTH);

        panel.add(new JScrollPane(lessonBodyArea),BorderLayout.CENTER);

        panel.add(buttonPanel,BorderLayout.SOUTH);

        panel.add(lessonImageLabel, BorderLayout.EAST);

        updateLessonDisplay();

        return panel;
    }

    /*
     * Updates lesson title, content,
     * and image based on the current lesson.
     */
    private void updateLessonDisplay() {

        Learnable lesson = lessonCatalog.getCurrentLesson();

        lessonTitleLabel.setText(
                "Page "
                + lessonCatalog.getCurrentPageNumber()
                + " / "
                + lessonCatalog.getTotalLessons()
                + " : "
                + lesson.getTitle()
        );

        lessonBodyArea.setText(lesson.getBody());

        ImageIcon image = lesson.getImage();

        if (image != null) {
            lessonImageLabel.setIcon(image);
        } else {
            lessonImageLabel.setIcon(null);
        }
    }

    /*
     * Creates the progress screen displaying
     * score, points, stars, and rewards.
     */
    private JPanel createProgressPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        progressArea = new JTextArea();

        progressArea.setEditable(false);

        updateProgressDisplay();

        JButton backBtn =
                new JButton("Back");

        backBtn.addActionListener(
                e -> showScreen("home")
        );

        panel.add(
                new JScrollPane(progressArea),
                BorderLayout.CENTER
        );

        panel.add(
                backBtn,
                BorderLayout.SOUTH
        );

        return panel;
    }

    /*
     * Refreshes progress information
     * from UserProgress.
     */
    private void updateProgressDisplay() {

        String text =
                "Latest Score: "
                + progress.getLatestScore()
                + "\n\nPoints: "
                + progress.getPoints()
                + "\n\nStars: "
                + progress.getStars()
                + "\n\nRewards:\n";

        for (Reward reward :
                progress.getRewards()) {

            text += reward.getRewardInfo()
                    + "\n";
        }

        progressArea.setText(text);
    }

    /*
     * Creates the leaderboard screen showing
     * saved quiz scores.
     */
    private JPanel createLeaderboardPanel() {

        JPanel panel =
                new JPanel(new BorderLayout());

        leaderboardArea = new JTextArea();

        leaderboardArea.setEditable(false);

        updateLeaderboardDisplay();

        JButton backBtn =
                new JButton("Back");

        backBtn.addActionListener(
                e -> showScreen("home")
        );

        panel.add(
                new JScrollPane(leaderboardArea),
                BorderLayout.CENTER
        );

        panel.add(
                backBtn,
                BorderLayout.SOUTH
        );

        return panel;
    }

    /*
     * Updates leaderboard rankings from
     * stored quiz scores.
     */
    private void updateLeaderboardDisplay() {

        String text =
                "Leaderboard\n\n";

        int rank = 1;

        for (Integer score :
                progress.getLeaderboard()) {

            text += rank
                    + ". "
                    + score
                    + "\n";

            rank++;
        }

        leaderboardArea.setText(text);
    }

    // Displays a screen using its screen ID.
    @Override
    public void showScreen(String screenId) {
        cardLayout.show(mainPanel, screenId);
    }

    /*
     * Displays a screen using its numeric index.
     * 0 = Home
     * 1 = Learn
     * 2 = Quiz
     * 3 = Progress
     * 4 = Leaderboard
     */
    @Override
    public void showScreen(int screenIndex) {

        switch(screenIndex) {

            case 0:
                showScreen("home");
                break;

            case 1:
                showScreen("learn");
                break;

            case 2:
                showScreen("quiz");
                break;

            case 3:
                showScreen("progress");
                break;

            case 4:
                showScreen("leaderboard");
                break;

            default:
                showScreen("home");
        }
    }

    // Application entry point
    public static void main(String[] args) {
        new MainApplication();
    }
}
