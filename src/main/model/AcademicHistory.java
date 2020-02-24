package model;

import exceptions.PreExistingTermException;

import java.util.ArrayList;

public class AcademicHistory {
    ArrayList<Term> listOfTerms;

    //EFFECTS: Contructs an AcademicHistory with a list of terms that the user had and currently has
    public AcademicHistory() {
        listOfTerms = new ArrayList<Term>();
    }


    //MODIFIES: this
    //EFFECTS: add a new term to the current list of terms
    public void addTerm(Term term) throws PreExistingTermException {
        ArrayList<String> temp = new ArrayList<>();

        for (Term t: listOfTerms) {
            String termName = t.getTermName();
            temp.add(termName);
        }

        String newTermName = term.getTermName();

        if (!temp.contains(newTermName)) {
            this.listOfTerms.add(term);
        } else {
            throw new PreExistingTermException();
        }
    }

    //GETTERS AND SETTERS
    public ArrayList<Term> getListOfTerms() {
        return listOfTerms;
    }
}
