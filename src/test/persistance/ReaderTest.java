package persistance;

import model.AcademicHistory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderTest {

    private static final String TEST_FILE_2 = "./data/testFile2";
    private AcademicHistory ahObject = null;
    private Reader reader;

    @Test
    void testReader() {
        reader = new Reader();
    }

    @Test
    void testReadData() {

        try {
            ahObject = Reader.readAHistory(new File(TEST_FILE_2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(1,
                ahObject.getListOfTerms().size());
        assertEquals("Term 1",
                ahObject.getListOfTerms().get(0).getTermName());
        assertEquals(1,
                ahObject.getListOfTerms().get(0).getListOfCourse().size());
        assertEquals("Math 101",
                ahObject.getListOfTerms().get(0).getListOfCourse().get(0).getCourseName());
        assertEquals(1,
                ahObject.getListOfTerms().get(0).getListOfCourse().get(0).getListOfComponents().size());
        assertEquals(25,
                ahObject.getListOfTerms().get(0).getListOfCourse().get(0).getListOfComponents().get(0).getWeight());
        assertEquals("Midterm",
                ahObject.getListOfTerms().get(0).getListOfCourse().get(0).getListOfComponents().get(0).getComponentName());
        assertEquals(1,
                ahObject.getListOfTerms().get(0).getListOfCourse().get(0).getListOfComponents().get(0).getListOfAssignments().size());
        assertEquals("Midterm 1",
                ahObject.getListOfTerms().get(0).getListOfCourse().get(0).getListOfComponents().get(0).getListOfAssignments().get(0).getName());
        assertEquals(80,
                ahObject.getListOfTerms().get(0).getListOfCourse().get(0).getListOfComponents().get(0).getListOfAssignments().get(0).getScore());
    }
}
