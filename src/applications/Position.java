package applications;
import java.util.List;
import java.util.Set;

public class Position {
    private String name;
    private Set<Skill> requiredSkills;

    public Position(String name, Set<Skill> requiredSkills){
        this.name = name;
        this.requiredSkills = requiredSkills;
        for (Skill skill: requiredSkills){
            skill.addPosition(this);
        }
    }
    public String getName() {
        return this.name;
    }

    public List<String> getApplicants() {
        return null;
    }

    public String getWinner() {
        return null;
    }
}
