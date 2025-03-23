package applications;
import java.util.*;

public class HandleApplications {
    final private Map<String, Skill> skills = new HashMap<>();
    final private Map<String, Position> positions = new HashMap<>();
    public void addSkills(String... names) throws ApplicationException {
        for (String name: names){
            if(skills.containsKey(name)){
                throw new ApplicationException();
            }
            skills.put(name, new Skill(name));
        }
    }
    public void addPosition(String name, String... skillNames) throws ApplicationException {
        if (positions.containsKey(name)) {
            throw new ApplicationException();
        }
        Set<Skill> requiredSkills = new HashSet<>();
        for (String skillName: skillNames){
            Skill skill = skills.get(skillName);
            if (skill == null){
                throw new ApplicationException();
            }
            requiredSkills.add(skill);
        }
        positions.put(name, new Position(name, requiredSkills));

    }
    public Skill getSkill(String name) {
        return skills.get(name);
    }
    public Position getPosition(String name) {
        return positions.get(name);
    }

    public void addApplicant(String name, String capabilities) throws ApplicationException {

    }
    public String getCapabilities(String applicantName) throws ApplicationException {
        return null;
    }

    public void enterApplication(String applicantName, String positionName) throws ApplicationException {

    }

    public int setWinner(String applicantName, String positionName) throws ApplicationException {
        return 0;
    }

    public SortedMap<String, Long> skill_nApplicants() {
        return null;
    }
    public String maxPosition() {
        return null;
    }
}
