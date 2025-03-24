package applications;

import java.util.HashMap;
import java.util.Map;

public class Applicant {
    final private String name;
    private Map<String, Integer> skillLevels = new HashMap<>();
    public Applicant(String name, Map<String, Integer> skillLevels){
        this.name = name;
        this.skillLevels = skillLevels;
    }
    public Map<String, Integer> getSkills(){
        return skillLevels;
    }
}
