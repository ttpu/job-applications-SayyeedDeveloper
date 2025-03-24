package applications;
import java.util.*;

public class HandleApplications {
    final private Map<String, Skill> skills = new HashMap<>();
    final private Map<String, Position> positions = new HashMap<>();
    final private Map<String,Applicant> applicants = new HashMap<>();
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
        if(applicants.containsKey(name)){
            throw new ApplicationException();
        }
        Map<String, Integer> skillLevels = new HashMap<>();
        String[] capabilitiesArray = capabilities.split(",");
        for (String capability: capabilitiesArray){
            String[] parts = capability.split(":");
            if(parts.length != 2){
                throw new ApplicationException();
            }
            String skillName = parts[0].trim();
            int level;
            try {
                level = Integer.parseInt(parts[1].trim());
            }catch (NumberFormatException e){
                throw new ApplicationException();
            }
            if (!skills.containsKey(skillName)){
                throw new ApplicationException();
            }
            if(level < 1 || level > 10){
                throw new ApplicationException();
            }
            skillLevels.put(skillName, level);
        }
        applicants.put(name, new Applicant(name, skillLevels));
    }
    public String getCapabilities(String applicantName) throws ApplicationException {
        Applicant applicant = applicants.get(applicantName);
        if (applicant == null){
            throw new ApplicationException();
        }
        Map <String, Integer> skillLevels = applicant.getSkills();
        if(skillLevels.isEmpty()){
            return "";
        }
        StringBuilder capabilities = new StringBuilder();
        List<String> sortedSkills = new ArrayList<>(skillLevels.keySet());
        Collections.sort(sortedSkills);
        for (String skill: sortedSkills){
            if(capabilities.length() > 0){
                capabilities.append(",");
            }
            capabilities.append(skill).append(":").append(skillLevels.get(skill));
        }
        return capabilities.toString();
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
