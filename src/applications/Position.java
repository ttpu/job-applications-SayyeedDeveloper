package applications;
import java.util.*;

public class Position {
    final private String name;
    final private Set<Skill> requiredSkills;
    final private Set<String> applicants = new HashSet<>();

    public Position(String name, Set<Skill> requiredSkills){
        this.name = name;
        this.requiredSkills = requiredSkills;
        for (Skill skill: requiredSkills){
            skill.addPosition(this);
        }
    }
    public Set<Skill> getRequiredSkills() {
        return this.requiredSkills;
    }
    public void  addApplicant( String applicantName){
        applicants.add(applicantName);
    }
    public List<String> getApplicants(){
        List<String> sortedApplicants = new ArrayList<>(applicants);
        Collections.sort(sortedApplicants);
        return sortedApplicants;
    }
    public String getName() {
        return this.name;
    }

    public String getWinner() {
        return null;
    }
}
