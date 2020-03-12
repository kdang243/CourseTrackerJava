package ui;

import com.google.gson.Gson;
import exceptions.PreExistingCompException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AcademicHistory;
import model.Component;
import model.Course;
import model.Term;
import persistance.Reader;
import persistance.Writer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddNewComponentController {
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
    public TextArea componentNameBox;

    @FXML
    public TextArea componentWeightBox;

    @FXML
    public Button addComponentEnter;

    @FXML
    public Button addComponentBackToMainMenu;

    @FXML
    public Button addNewAssignment;

    @FXML
    public Text addComponentFeedback;

    @FXML
    void addComponentEnterClicked(MouseEvent event)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clickNoise();
        ah = loadData();

        String termName = whichTermBox.getText();
        String courseName = whichCourseBox.getText();
        String componentName = componentNameBox.getText();
        String componentWeight = componentWeightBox.getText();
        double weight = Double.parseDouble(componentWeight);

        Term term = getTerm(termName);
        ArrayList<Course> courses = term.getListOfCourse();
        Course course = getCourse(courseName, courses);
        Component component = new Component(weight, componentName);

        try {
            course.addComponent(component);
            addComponentFeedback.setText("Component has been added! Autosaving...");
        } catch (PreExistingCompException e) {
            addComponentFeedback.setText("This component already exists! Autosaving...");
        } finally {
            autoSave();
        }
    }

    @FXML
    void addComponentBackToMainMenuClicked(MouseEvent event)
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
    void addNewAssignmentClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent addNewAssignmentView = FXMLLoader.load(getClass().getResource("addNewAssignGUI.fxml"));
        Scene addNewAssignmentScene = new Scene(addNewAssignmentView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(addNewAssignmentScene);
        window.show();
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

    private void clickNoise() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File f = new File("./data/Click.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
