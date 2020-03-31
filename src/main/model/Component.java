package model;

import exceptions.PreExistingAssignException;

import java.util.ArrayList;

public class Component {
    ArrayList<Assignment> listOfAssignments;
    double weight;
    double gradeOfComponent;
    String componentName;
    ComponentGradeRefresher refresher;

    //REQUIRES: percentage > 0
    //EFFECTS: Contructs a component in a course with given weight (in percentage)
    public Component(double weight, String name) {
        componentName = name;
        listOfAssignments = new ArrayList<Assignment>();
        this.weight = weight;
        gradeOfComponent = 0;
    }


    //MODIFIES: this
    //EFFECTS: add a new assignment to the list of assignment in the component
    public void addAssignment(Assignment assignment) throws PreExistingAssignException {
        ArrayList<String> temp = new ArrayList<>();

        for (Assignment a: listOfAssignments) {
            temp.add(a.getName());
        }

        if (!temp.contains(assignment.getName())) {
            this.listOfAssignments.add(assignment);
        } else {
            throw new PreExistingAssignException();
        }

        refresher = new ComponentGradeRefresher(listOfAssignments);
        gradeOfComponent = refresher.refreshGrade();
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
