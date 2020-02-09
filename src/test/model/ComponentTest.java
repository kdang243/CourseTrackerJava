package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComponentTest {

    Component c1;
    Component c2;
    Assignment a1;
    Assignment a2;

    @BeforeEach
    void ComponentTest() {
        c1 = new Component(20, "Labs");
        c2 = new Component(35,"Midterm 1");
        a1 = new Assignment("Lab 1", 80);
        a2 = new Assignment("Midterm", 90);
    }

    @Test
    void addAssignmentTest() {
        c1.addAssignment(a1);
        assertEquals(1, c1.listOfAssignments.size());
        c1.addAssignment(a2);
        assertEquals(2, c1.listOfAssignments.size());
    }

    @Test
    void setWeightTest() {
        assertEquals(20, c1.getWeight());
        c1.setWeight(45);
        assertEquals(45, c1.getWeight());
    }

    @Test
    void getWeightTest() {
        assertEquals(20, c1.getWeight());
        assertEquals(35, c2.getWeight());
    }

    @Test
    void getListOfAssignmentsTest(){
        c1.addAssignment(a1);
        c1.addAssignment(a2);
        ArrayList<Assignment> temp = new ArrayList<>();
        temp.add(a1);
        temp.add(a2);
        assertEquals(temp, c1.getListOfAssignments());
    }

    @Test
    void getGradeOfComponentTest() {
        assertEquals(0, c1.getGradeOfComponent());
        assertEquals(0, c2.getGradeOfComponent());
    }

    @Test
    void setNameTest() {
        assertEquals("Labs", c1.getComponentName());
        c1.setName("Midterm 2");
        assertEquals("Midterm 2", c1.getComponentName());

    }

    @Test
    void getComponentNameTest() {
        assertEquals("Labs", c1.getComponentName());
        assertEquals("Midterm 1", c2.getComponentName());

    }
}
