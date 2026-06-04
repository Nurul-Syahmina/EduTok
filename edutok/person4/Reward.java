// Creator: Person 4 | Tester: Person 1
// Group: G04/SE-G11 | App: EduTok

package edutok.person4;

public abstract class Reward {

    protected String name;

    public Reward(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getRewardInfo();
}
