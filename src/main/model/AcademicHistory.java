package model;

import java.util.ArrayList;

public class AcademicHistory {
    ArrayList<Term> listOfTerms;

    //EFFECTS: Contructs an AcademicHistory with a list of terms that the user had and currently has
    public AcademicHistory() {
        listOfTerms = new ArrayList<Term>();
    }

    //REQUIRES: Must be a new term, not a pre-existing term
    //MODIFIES: this
    //EFFECTS: add a new term to the current list of terms
    public void addTerm(Term term) {
        this.listOfTerms.add(term);
    }

    //GETTERS AND SETTERS
    public ArrayList<Term> getListOfTerms() {
        return listOfTerms;
    }
}
