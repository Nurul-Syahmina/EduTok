// Creator: Person 1 | Tester: Person 2
// Group: G04/SE-G11 | App: EduTok

package edutok.person1;

import javax.swing.*;
import java.awt.*;
import edutok.person2.QuizManager;
import edutok.person2.QuizQuestion;
import edutok.person2.MultipleChoiceQuestion;

public class MainApplication extends JFrame implements Navigable {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private QuizManager quizManager;

    private JTextArea questionArea;
    private JTextField answerField;

    public MainApplication() {

        setTitle("EduTok");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        quizManager = new QuizManager();
        quizManager.loadQuestions();

        createScreens();

        add(mainPanel);

        setVisible(true);
    }

    private void createScreens() {

        JPanel homePanel = createHomePanel();
        JPanel learnPanel = createSimplePanel("Learning Module");
        JPanel quizPanel = createQuizPanel();
        JPanel progressPanel = createSimplePanel("Progress Module");
        JPanel leaderboardPanel = createSimplePanel("Leaderboard Module");

        mainPanel.add(homePanel, "home");
        mainPanel.add(learnPanel, "learn");
        mainPanel.add(quizPanel, "quiz");
        mainPanel.add(progressPanel, "progress");
        mainPanel.add(leaderboardPanel, "leaderboard");
    }

    private JPanel createHomePanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        JLabel title = new JLabel("EduTok", SwingConstants.CENTER);

        JButton learnBtn = new JButton("Learn");
        JButton quizBtn = new JButton("Quiz");
        JButton progressBtn = new JButton("Progress");
        JButton leaderboardBtn = new JButton("Leaderboard");
        JButton exitBtn = new JButton("Exit");

        learnBtn.addActionListener(e -> showScreen("learn"));
        quizBtn.addActionListener(e -> showScreen("quiz"));
        progressBtn.addActionListener(e -> showScreen("progress"));
        leaderboardBtn.addActionListener(e -> showScreen("leaderboard"));
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(title);
        panel.add(learnBtn);
        panel.add(quizBtn);
        panel.add(progressBtn);
        panel.add(leaderboardBtn);
        panel.add(exitBtn);

        return panel;
    }

    private JPanel createQuizPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        QuizQuestion q = quizManager.getCurrentQuestion();

        String questionText = q.getPrompt();

        // If it's a Multiple Choice question,
        // display the options too
        if (q instanceof MultipleChoiceQuestion) {

            MultipleChoiceQuestion mcq =
                    (MultipleChoiceQuestion) q;

            String[] options = mcq.getOptions();

            for (String option : options) {
                questionText += "\n" + option;
            }
        }

        questionArea = new JTextArea(questionText);

        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);

        answerField = new JTextField();

        JButton submitButton = new JButton("Submit");

        JButton backBtn = new JButton("Back");

        backBtn.addActionListener(e -> showScreen("home"));

        submitButton.addActionListener(e -> {

            String answer =
                    answerField.getText();

            quizManager.submitAnswer(answer);

            if (quizManager.hasNext()) {

                quizManager.nextQuestion();

                updateQuestionDisplay();

                answerField.setText("");

            } else {

                int score =
                        quizManager.getScorePercent();

                String message =
                        quizManager.getMotivationalMessage();

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

    private JPanel createSimplePanel(String text) {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(text, SwingConstants.CENTER);

        JButton backBtn = new JButton("Back");

        backBtn.addActionListener(e -> showScreen("home"));

        panel.add(label, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public void showScreen(String screenId) {
        cardLayout.show(mainPanel, screenId);
    }

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

    public static void main(String[] args) {
        new MainApplication();
    }
}