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
        finalGrade = 0;
    }

    //REQUIRES: This must be a new component, not a pre-existing component in the course
    //MODIFIES: this
    //EFFECTS: add a new component to the course
    public void addComponent(Component component) {
        this.listOfComponents.add(component);
        finalGrade = refreshFinal();
    }

    //MODIFIES: this
    //EFFECTS: recalculates the finalGrade in the object
    private double refreshFinal() {

        ArrayList<Double> temp = new ArrayList<>();

        for (Component c: listOfComponents) {
            double weight = c.weight / 100;
            double grade = c.gradeOfComponent;
            double part = weight * grade;
            temp.add(part);
        }

        double answer = 0;

        for (Double d: temp) {
            answer += d;
        }

        return answer;
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
