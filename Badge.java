// Creator: Person 4 | Tester: Person 1
// Group: G04/SE-G11 | App: EduTok

package edutok.person4;

public class Badge extends Reward {

    public Badge(String name) {
        super(name);
    }

    @Override
    public String getRewardInfo() {
        return "Badge: " + name;
    }
}