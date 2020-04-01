package model;

import exceptions.PreExistingCourseException;

import java.lang.reflect.Array;
import java.util.ArrayList;

//This class represents a school term inside the user's academic history. A term consists of a list of courses that
// the user is taking in the term.
public class Term {
    ArrayList<Course> listOfCourse;
    String termName;

    //EFFECTS: Contructs a new term with a list of courses
    public Term(String name) {
        termName = name;
        listOfCourse = new ArrayList<Course>();
    }


    //MODIFIES: this
    //EFFECTS: add a new course to the listOfCourses in the term
    public void addCourse(Course course) throws PreExistingCourseException {
        ArrayList<String> temp = new ArrayList<>();

        for (Course c: listOfCourse) {
            String courseName = c.getCourseName();
            temp.add(courseName);
        }

        String newCourseName = course.getCourseName();

        if (!temp.contains(newCourseName)) {
            this.listOfCourse.add(course);
        } else {
            throw new PreExistingCourseException();
        }

    }

    //GETTERS AND SETTERS
    public ArrayList<Course> getListOfCourse() {
        return listOfCourse;
    }

    public String getTermName() {
        return this.termName;
    }
}
