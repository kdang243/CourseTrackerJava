package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {
    Assignment a1;
    Assignment a2;

    @BeforeEach
    void AssignmentTest() {
        a1 = new Assignment("Lab 1",80);
        a2 = new Assignment("Lab 2", 73);
    }

    @Test
    void getScoreTest() {
        assertEquals(80, a1.getScore());
        assertEquals(73, a2.getScore());
    }

    @Test
    void getNameTest() {
        assertEquals("Lab 1", a1.getName());
        assertEquals("Lab 2", a2.getName());
    }

    @Test
    void setScoreTest() {
        a1.setScore(77);
        assertEquals(77, a1.getScore());
        a2.setScore(32);
        assertEquals(32, a2.getScore());
    }


    @Test
    void setNameTest() {
        a1.setName("Lab 3");
        assertEquals("Lab 3", a1.getName());
        a2.setName("Lab 4");
        assertEquals("Lab 4", a2.getName());
    }
}