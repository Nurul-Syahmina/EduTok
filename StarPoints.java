// Creator: Person 4 | Tester: Person 1
// Group: G04/SE-G11 | App: EduTok

package edutok.person4;

public class StarPoints extends Reward {

    private int stars;

    public StarPoints(int stars) {
        super("Star Reward");
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    @Override
    public String getRewardInfo() {
        return "Stars Earned: " + stars;
    }
}