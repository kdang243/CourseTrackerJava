package model;

import exceptions.PreExistingCourseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TermTests {

    Term t1;
    Course co1;
    Course co2;

    @BeforeEach
    void TermTests() {
        t1 = new Term("WT1 2019-2020");
        co1 = new Course("Math 101");
        co2 = new Course("CPSC 210");
    }

    @Test
    void addCourseTest() {
        assertEquals(0, t1.listOfCourse.size());
        try {
            t1.addCourse(co1);
        } catch (PreExistingCourseException e) {
            fail();
        }
        assertEquals(1, t1.listOfCourse.size());
        try {
            t1.addCourse(co2);
        } catch (PreExistingCourseException e) {
            fail();
        }
        assertEquals(2, t1.listOfCourse.size());

        try {
            t1.addCourse(co2);
        } catch (PreExistingCourseException e) {

        }
    }

    @Test
    void getListOfCourseTest() {
        try {
            t1.addCourse(co1);
        } catch (PreExistingCourseException e) {
            fail();
        }
        try {
            t1.addCourse(co2);
        } catch (PreExistingCourseException e) {
            fail();
        }
        ArrayList<Course> temp = new ArrayList<>();
        temp.add(co1);
        temp.add(co2);
        assertEquals(temp, t1.getListOfCourse());
    }

    @Test
    void getTermNameTest() {
        assertEquals("WT1 2019-2020", t1.getTermName());
    }
}
