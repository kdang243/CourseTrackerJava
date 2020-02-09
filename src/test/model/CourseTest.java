package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    Course co1;
    Course co2;
    Component c1;
    Component c2;

    @BeforeEach
    void CourseTest() {
        co1 = new Course("Math 101");
        co2 = new Course("CPSC 121");
        c1 = new Component(30,"Midterm 1");
        c2 = new Component(45,"Labs");
    }

    @Test
    void addComponentTest() {
        assertEquals(0, co1.listOfComponents.size());
        co1.addComponent(c1);
        assertEquals(1, co1.listOfComponents.size());
        co1.addComponent(c2);
        assertEquals(2, co1.listOfComponents.size());
    }

    @Test
    void getCourseNameTest() {
        assertEquals("Math 101", co1.getCourseName());
        assertEquals("CPSC 121", co2.getCourseName());
    }

    @Test
    void setCourseNameTest() {
        assertEquals("Math 101", co1.getCourseName());
        co1.setCourseName("Phys 101");
        assertEquals("Phys 101", co1.getCourseName());
    }

    @Test
    void getListOfComponents() {
        co1.addComponent(c1);
        co1.addComponent(c2);
        ArrayList<Component> temp = new ArrayList<>();
        temp.add(c1);
        temp.add(c2);
        assertEquals(temp, co1.getListOfComponents());
    }

    @Test
    void getFinalGradeTest() {
        assertEquals(0, co1.getFinalGrade());
        assertEquals(0, co2.getFinalGrade());
    }
}
