package model;

import java.util.ArrayList;

public class CourseGradeRefresher {
    ArrayList<Component> listOfComponents;

    public CourseGradeRefresher(ArrayList<Component> listOfComponents) {
        this.listOfComponents = listOfComponents;
    }

    //MODIFIES: this
    //EFFECTS: recalculates the finalGrade in the object
    protected double refreshFinal() {

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
}
