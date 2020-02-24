package model;

import exceptions.PreExistingTermException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AcademicHistoryTest {

    AcademicHistory ah1;
    Term t1;
    Term t2;

    @BeforeEach
    void AcademicHistoryTest() {
        ah1 = new AcademicHistory();
        t1 = new Term("WT1 2018-2019");
        t2 = new Term("WT2 2018-2019");
    }

    @Test
    void addTermTest() {
        assertEquals(0, ah1.listOfTerms.size());
        try {
            ah1.addTerm(t1);
        } catch (PreExistingTermException e) {
            fail();
        }
        assertEquals(1, ah1.listOfTerms.size());
        try {
            ah1.addTerm(t2);
        } catch (PreExistingTermException e) {
            fail();
        }
        assertEquals(2, ah1.listOfTerms.size());
        try {
            ah1.addTerm(t2);
        } catch (PreExistingTermException e) {

        }
    }

    @Test
    void getListOfTermsTest() {
        try {
            ah1.addTerm(t1);
        } catch (PreExistingTermException e) {
            fail();
        }
        try {
            ah1.addTerm(t2);
        } catch (PreExistingTermException e) {
            fail();
        }
        ArrayList<Term> temp = new ArrayList<>();
        temp.add(t1);
        temp.add(t2);
        assertEquals(temp, ah1.getListOfTerms());
    }
}
