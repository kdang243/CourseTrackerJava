package persistance;

import com.google.gson.Gson;
import exceptions.PreExistingAssignException;
import exceptions.PreExistingCompException;
import exceptions.PreExistingCourseException;
import exceptions.PreExistingTermException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {

    private static final String TEST_FILE = "./data/testFile1";
    private Writer testWriter;
    private AcademicHistory ah;
    private Term term;
    private Course course;
    private Component component;
    private Assignment assignment;

    @BeforeEach
    void runBefore() throws IOException {
        testWriter = new Writer(new File(TEST_FILE));
        assignment = new Assignment("Midterm 1", 80);
        component = new Component(25, "Midterm");
        course = new Course("Math 101");
        term = new Term("Term 1");
        ah = new AcademicHistory();

        try {
            component.addAssignment(assignment);
        } catch (PreExistingAssignException e) {
            fail();
        }
        try {
            course.addComponent(component);
        } catch (PreExistingCompException e) {
            fail();
        }
        try {
            term.addCourse(course);
        } catch (PreExistingCourseException e) {
            fail();
        }
        try {
            ah.addTerm(term);
        } catch (PreExistingTermException e) {
            fail();
        }
    }

    @Test
    void TestWriteData() {
        Gson gson = new Gson();
        String json = gson.toJson(ah);
        try {
            //writing to test file 1
            testWriter.write(json);
            testWriter.close();

            //now reading them back in and verifying that the ah has been saved correctly
            AcademicHistory ahObject = Reader.readAHistory(new File(TEST_FILE));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
