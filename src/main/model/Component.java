package model;

import java.util.ArrayList;

public class Component {
    ArrayList<Assignment> listOfAssignments;
    double weight;
    double gradeOfComponent;
    String componentName;

    //REQUIRES: percentage > 0
    //EFFECTS: Contructs a component in a course with given weight (in percentage)
    public Component(double weight, String name) {
        componentName = name;
        listOfAssignments = new ArrayList<Assignment>();
        this.weight = weight;
        gradeOfComponent = 0;
    }

    //REQUIRES: assignment has to a new assignment, not a pre-existing one
    //MODIFIES: this
    //EFFECTS: add a new assignment to the list of assignment in the component
    public void addAssignment(Assignment assignment) {
        this.listOfAssignments.add(assignment);
        gradeOfComponent = refreshGrade();
    }

    private double refreshGrade() {
        ArrayList<Double> temp = new ArrayList<>();
        for (Assignment a: listOfAssignments) {
            double score = a.score;
            temp.add(score);
        }

        int size = temp.size();
        double sumOfScores = 0;

        for (Double d: temp) {
            sumOfScores += d;
        }

        return sumOfScores / size;
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
