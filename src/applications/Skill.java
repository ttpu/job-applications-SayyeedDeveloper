package applications;
import java.util.*;
import java.util.stream.Collectors;

public class Skill {
    final private String name;
    final private ArrayList<Position> positions = new ArrayList<>();
    public Skill(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public List<Position> getPositions() {
        List<Position> sortedPositions = new ArrayList<>(positions);
        Collections.sort(sortedPositions, Comparator.comparing(Position::getName));
        return sortedPositions;
    }
    public void addPosition(Position position){
        positions.add(position);
    }
}
