package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        t1.addCourse(co1);
        assertEquals(1, t1.listOfCourse.size());
        t1.addCourse(co2);
        assertEquals(2, t1.listOfCourse.size());
    }

    @Test
    void getListOfCourseTest() {
        t1.addCourse(co1);
        t1.addCourse(co2);
        ArrayList<Course> temp = new ArrayList<>();
        temp.add(co1);
        temp.add(co2);
        assertEquals(temp, t1.getListOfCourse());
    }
}
