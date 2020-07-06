package ui;

import com.google.gson.Gson;
import exceptions.PreExistingAssignException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import persistance.Reader;
import persistance.Writer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddNewAssignController {
    //TODO: addTermEnter.getText();
    //TODO:addTErmFeedback.setText();
    public static final String SAVE_PATH = "./data/saved";

    AcademicHistory ah;
    AcademicHistory ahObject;

    @FXML
    public TextArea whichTermBox;

    @FXML
    public TextArea whichCourseBox;

    @FXML
    public TextArea whichComponentBox;

    @FXML
    public TextArea assignName;

    @FXML
    public TextArea assignGrade;

    @FXML
    public Button addAssignEnter;

    @FXML
    public Button addAssignBackToMainMenu;

    @FXML
    public Text addAssignFeedback;

    @FXML
    void addAssignBackToMainMenuClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent rootView = FXMLLoader.load(getClass().getResource("CourseTrackerGUI.fxml"));
        Scene rootScene = new Scene(rootView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(rootScene);
        window.show();
    }

    @FXML
    void addAssignEnterClicked(MouseEvent event)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clickNoise();
        ah = loadData();

        String termName = whichTermBox.getText();
        String courseName = whichCourseBox.getText();
        String componentName = whichComponentBox.getText();
        String assignmentName = assignName.getText();
        String assignmentGrade = assignGrade.getText();
        double grade = Double.parseDouble(assignmentGrade);

        Term term = getTerm(termName);
        ArrayList<Course> courses = term.getListOfCourse();

        Course course = getCourse(courseName, courses);
        ArrayList<Component> components = course.getListOfComponents();
        Component component = getComponent(componentName, components);

        Assignment assignment = new Assignment(assignmentName,grade);

        try {
            component.addAssignment(assignment);
            addAssignFeedback.setText("Assignment has been added! Autosaving...");
            whichComponentBox.clear();
            whichCourseBox.clear();
            whichTermBox.clear();
            assignName.clear();
            assignGrade.clear();
        } catch (PreExistingAssignException e) {
            addAssignFeedback.setText("This assignment already exists! Autosaving...");
        } finally {
            autoSave();
        }
    }


    private AcademicHistory loadData() {
        try {
            ahObject = Reader.readAHistory(new File(SAVE_PATH));
        } catch (IOException e) {
            System.out.println("File not found");
        }

        return ahObject;
    }

    //EFFECTS: saves the state of the academic history to SAVE_PATH
    private void autoSave() {
        Gson gson = new Gson();
        String json = gson.toJson(ah);

        try {
            Writer writer = new Writer(new File(SAVE_PATH));
            writer.write(json);
            writer.close();
            System.out.println("Your Academic History has been saved!");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    //REQUIRES: the list of terms inside academic history can't be empty
    //EFFECTS: returns a term with given name from academic history's list of terms
    private Term getTerm(String temp) {
        ArrayList<Term> temp2 = ah.getListOfTerms();
        ArrayList<String> temp3 = new ArrayList<>();

        for (Term t: temp2) {
            String termName = t.getTermName();
            temp3.add(termName);
        }

        int index = temp3.indexOf(temp);
        return temp2.get(index);
    }

    //REQUIRES: the list of courses input can't be empty
    //EFFECTS: returns a course with given name from the given list of courses
    private Course getCourse(String courseName, ArrayList<Course> courses) {
        ArrayList<String> temp = new ArrayList<>();

        for (Course c: courses) {
            String name = c.getCourseName();
            temp.add(name);
        }

        int index = temp.indexOf(courseName);
        return courses.get(index);
    }

    //REQUIRES: the list of components input can't be empty
    //EFFECTS: returns a component with given name from the given list of components
    private Component getComponent(String componentName, ArrayList<Component> components) {
        ArrayList<String> temp = new ArrayList<>();

        for (Component c: components) {
            String name = c.getComponentName();
            temp.add(name);
        }

        int index = temp.indexOf(componentName);
        return components.get(index);
    }

    private void clickNoise() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //source: https://stackoverflow.com/questions/10591852/how-to-cast-from-inputstream-to-audioinputstream
        File f = new File("./data/Click.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
