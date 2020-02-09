package model;

import java.util.ArrayList;

public class Component {
    ArrayList<Assignment> listOfAssignments;
    double weight;
    double gradeOfComponent;
    String componentName;

    //REQUIRES: percentage > 0
    //EFFECTS: Contructs a component in a course with given weight
    public Component(double weight, String name) {
        componentName = name;
        listOfAssignments = new ArrayList<Assignment>();
        this.weight = weight;
        gradeOfComponent = 0;
        //TODO: Implement way to calculate gradeOfComponent by finding the average score of assignments
    }

    //REQUIRES: assignment has to a new assignment, not a pre-existing one
    //MODIFIES: this
    //EFFECTS: add a new assignment to the list of assignment in the component
    public void addAssignment(Assignment assignment) {
        this.listOfAssignments.add(assignment);
    }

    //GETTERS AND SETTERS
    public void setWeight(double percentage) {
        this.weight = percentage;
    }

    public double getWeight() {
        return weight;
    }

    public ArrayList<Assignment> getListOfAssignments() {
        return listOfAssignments;
    }

    public double getGradeOfComponent() {
        return gradeOfComponent;
    }

    public void setName(String name) {
        this.componentName = name;
    }

    public String getComponentName() {
        return componentName;
    }
}
