package model;

import exceptions.PreExistingCompException;

import java.util.ArrayList;

//This class represents a course that's inside of one term. A course consists of a list of components that makes up a
// course and a final grade for the course.
public class Course {

    String courseName;
    ArrayList<Component> listOfComponents;
    double finalGrade;
    CourseGradeRefresher refresher;

    //EFFECTS: Contructs a course with a list of components and the final grade
    public Course(String name) {
        courseName = name;
        listOfComponents = new ArrayList<Component>();
        finalGrade = 0;
    }

    //MODIFIES: this
    //EFFECTS: add a new component to the course
    public void addComponent(Component component) throws PreExistingCompException {
        ArrayList<String> temp = new ArrayList<>();

        for (Component c: listOfComponents) {
            temp.add(c.getComponentName());
        }

        if (!temp.contains(component.getComponentName())) {
            this.listOfComponents.add(component);
        } else {
            throw new PreExistingCompException();
        }

        refresher = new CourseGradeRefresher(listOfComponents);
        finalGrade = refresher.refreshFinal();
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
