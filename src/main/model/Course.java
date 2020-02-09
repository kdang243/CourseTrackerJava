package model;

import java.util.ArrayList;

public class Course {

    String courseName;
    ArrayList<Component> listOfComponents;
    double finalGrade;

    //EFFECTS: Contructs a course with a list of components and the final grade
    public Course(String name) {
        courseName = name;
        listOfComponents = new ArrayList<Component>();
        finalGrade = 0;   //TODO: Implement way to get finalGrade by calculating the weighted average of the components
    }

    //REQUIRES: This must be a new component, not a pre-existing component in the course
    //MODIFIES: this
    //EFFECTS: add a new component to the course
    public void addComponent(Component component) {
        this.listOfComponents.add(component);
    }

    //GETTERS AND SETTERS
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public ArrayList<Component> getListOfComponents() {
        return listOfComponents;
    }

    public double getFinalGrade() {
        return finalGrade;
    }
}
