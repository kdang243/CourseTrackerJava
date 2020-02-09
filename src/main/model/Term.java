package model;

import java.util.ArrayList;

public class Term {
    ArrayList<Course> listOfCourse;
    String termName;

    //EFFECTS: Contructs a new term with a list of courses
    public Term(String name) {
        termName = name;
        listOfCourse = new ArrayList<Course>();
    }

    //REQUIRES: Must be a new course, not a pre-existing course
    //MODIFIES: this
    //EFFECTS: add a new course to the listOfCourses in the term
    public void addCourse(Course course) {
        this.listOfCourse.add(course);
    }

    //GETTERS AND SETTERS
    public ArrayList<Course> getListOfCourse() {
        return listOfCourse;
    }
}
