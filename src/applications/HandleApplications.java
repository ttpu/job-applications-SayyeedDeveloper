package applications;
import java.util.*;

public class HandleApplications {
    final private Map<String, Skill> skills = new HashMap<>();
    final private Map<String, Position> positions = new HashMap<>();
    final private Map<String,Applicant> applicants = new HashMap<>();
    final private Set<String>  appliedApplicants = new HashSet<>();
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
            if(!capabilities.isEmpty()){
                capabilities.append(",");
            }
            capabilities.append(skill).append(":").append(skillLevels.get(skill));
        }
        return capabilities.toString();
    }

    public void enterApplication(String applicantName, String positionName) throws ApplicationException {
        Applicant applicant = applicants.get(applicantName);
        Position position = positions.get(positionName);

        if(applicant == null || position == null){
            throw new ApplicationException();
        }
        if(appliedApplicants.contains(applicantName)){
            throw new ApplicationException();
        }
        Map<String, Integer> applicantSkills = applicant.getSkills();
        for (Skill requiredSkill : position.getRequiredSkills()){
            if (!applicantSkills.containsKey(requiredSkill.getName())){
                throw new ApplicationException();
            }
        }
        position.addApplicant(applicantName);
        appliedApplicants.add(applicantName);

    }

    public int setWinner(String applicantName, String positionName) throws ApplicationException {
        Applicant applicant = applicants.get(applicantName);
        Position position = positions.get(positionName);

        if(applicant == null || position == null){
            throw new ApplicationException();
        }

        if (!position.getApplicants().contains(applicantName)){
            throw new ApplicationException();
        }
        if(position.getWinner() != null){
            throw new ApplicationException();
        }
        int sumOfLevels = 0;
        for (Skill requiredSkill : position.getRequiredSkills()) {
            sumOfLevels += applicant.getSkills().getOrDefault(requiredSkill.getName(), 0);
        }

        if (sumOfLevels <= 6 * position.getRequiredSkills().size()) {
            throw new ApplicationException();
        }
        position.setWinner(applicantName);
        return sumOfLevels;
    }

    public SortedMap<String, Long> skill_nApplicants() {
        SortedMap<String, Long> skillCounts = new TreeMap<>();
        for (Applicant applicant : applicants.values()) {
            for (String skill : applicant.getSkills().keySet()) {
                skillCounts.put(skill, skillCounts.getOrDefault(skill, 0L) + 1);
            }
        }
        return skillCounts;
    }
    public String maxPosition() {
        String maxPosition = null;
        int maxApplicants = 0;
        for (Position position : positions.values()) {
            int applicantCount = position.getApplicants().size();
            if (applicantCount > maxApplicants) {
                maxApplicants = applicantCount;
                maxPosition = position.getName();
            }
        }
        return maxPosition;
    }
}
