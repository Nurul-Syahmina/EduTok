// Creator: Person 4 | Tester: Person 1
// Group: G04/SE-G11 | App: EduTok

package edutok.person4;

import java.io.*;
import java.util.*;

public class UserProgress implements Storable {

    private int latestScore;
    private int points;
    private int stars;

    private ArrayList<Integer> leaderboard;
    private ArrayList<Reward> rewards;

    private static final String FILE_PATH =
            "data/scores.txt";

    public UserProgress() {

        latestScore = 0;
        points = 0;
        stars = 0;

        leaderboard = new ArrayList<>();
        rewards = new ArrayList<>();
    }

    public void recordQuiz(int scorePercent) {

        latestScore = scorePercent;

        points += scorePercent;

        stars = scorePercent / 20;

        rewards.clear();

        rewards.add(new StarPoints(stars));

        if (scorePercent >= 80) {
            rewards.add(
                    new Badge("Education Champion")
            );
        }

        if (scorePercent == 100) {
            rewards.add(
                    new Badge("Perfect Score")
            );
        }

        leaderboard.add(scorePercent);
    }

    public int getLatestScore() {
        return latestScore;
    }

    public int getPoints() {
        return points;
    }

    public int getStars() {
        return stars;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public List<Integer> getLeaderboard() {

        Collections.sort(
                leaderboard,
                Collections.reverseOrder());

        return leaderboard;
    }

    @Override
    public void save() throws Exception {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdir();
            }

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(FILE_PATH)
                    );

            // Save current progress
            writer.println(latestScore);
            writer.println(points);
            writer.println(stars);

            // Save leaderboard scores
            for (Integer score : leaderboard) {
                writer.println("SCORE:" + score);
            }

            writer.close();

        } catch (IOException e) {

            throw new ScoreFileException(
                    "Unable to save score."
            );
        }
    }

    @Override
    public void load() throws Exception {

        leaderboard.clear();
        rewards.clear();

        try {

            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            Scanner scan =
                    new Scanner(file);

            if (scan.hasNextLine()) {
                latestScore =
                        Integer.parseInt(
                                scan.nextLine()
                        );
            }

            if (scan.hasNextLine()) {
                points =
                        Integer.parseInt(
                                scan.nextLine()
                        );
            }

            if (scan.hasNextLine()) {
                stars =
                        Integer.parseInt(
                                scan.nextLine()
                        );
            }

            while (scan.hasNextLine()) {

                String line =
                        scan.nextLine();

                if (line.startsWith("SCORE:")) {

                    leaderboard.add(
                            Integer.parseInt(
                                    line.substring(6)
                            )
                    );
                }
            }

            scan.close();

            // Rebuild rewards after loading
            rewards.add(
                    new StarPoints(stars)
            );

            if (latestScore >= 80) {

                rewards.add(
                        new Badge(
                                "Education Champion"
                        )
                );
            }

            if (latestScore == 100) {

                rewards.add(
                        new Badge(
                                "Perfect Score"
                        )
                );
            }

        } catch (Exception e) {

            throw new ScoreFileException(
                    "Unable to load score file."
            );
        }
    }
}
