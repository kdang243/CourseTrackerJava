package model;

import java.util.ArrayList;
import java.util.List;

// This class represents a refresher that manages the total grade for a component in a course.
public class ComponentGradeRefresher {

    ArrayList<Assignment> listOfAssignments;

    //EFFECTS: contructs a new grade refresher for a component.
    public ComponentGradeRefresher(ArrayList<Assignment> listOfAssignments) {
        this.listOfAssignments = listOfAssignments;
    }

    //MODIFIES: this
    //EFFECTS: recalculates the grade of component in the object
    protected double refreshGrade() {
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
}
