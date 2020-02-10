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
    Component c3;
    Component c4;
    Assignment a1;
    Assignment a2;
    Assignment a3;

    @BeforeEach
    void CourseTest() {
        co1 = new Course("Math 101");
        co2 = new Course("CPSC 121");
        c1 = new Component(30,"Midterm 1");
        c2 = new Component(45,"Labs");
        c3 = new Component(60, "Midterm 1");
        c4 = new Component(40,"Labs");
        a1 = new Assignment("Lab 1", 80);
        a2 = new Assignment("lab 2", 70);
        a3 = new Assignment("Midterm 1", 90);
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
    void refreshFinalTest() {
        c3.addAssignment(a3);
        c4.addAssignment(a1);
        c4.addAssignment(a2);
        assertEquals(0, co1.finalGrade);
        co1.addComponent(c3);
        assertEquals(54, co1.finalGrade);
        co1.addComponent(c4);
        assertEquals(84,co1.finalGrade);
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
