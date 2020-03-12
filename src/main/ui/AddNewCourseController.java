package ui;

import com.google.gson.Gson;
import exceptions.PreExistingCourseException;
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
import model.Course;
import model.Term;
import persistance.Reader;
import persistance.Writer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddNewCourseController {
    //TODO: addTermEnter.getText();
    //TODO:addTErmFeedback.setText();
    public static final String SAVE_PATH = "./data/saved";

    AcademicHistory ah;
    AcademicHistory ahObject;

    @FXML
    public TextArea whichTermBox;

    @FXML
    public TextArea courseNameBox;

    @FXML
    public Text addCourseFeedback;

    @FXML
    public Button addCourseEnter;

    @FXML
    public Button addCourseBackToMainMenu;

    @FXML
    public Button addNewComponent;

    @FXML
    void addCourseEnterClicked(MouseEvent event)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clickNoise();
        ah = loadData();

        String termName = whichTermBox.getText();
        String courseName = courseNameBox.getText();
        Term term = getTerm(termName);
        Course course = new Course(courseName);

        try {
            term.addCourse(course);
            addCourseFeedback.setText("Course has been added! Autosaving...");
        } catch (PreExistingCourseException e) {
            addCourseFeedback.setText("That course already exists in this term! Autosaving...");
        } finally {
            autoSave();
        }


    }

    @FXML
    void addCourseBackToMainMenuClicked(MouseEvent event)
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
    void addNewComponentClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent addNewComponentView = FXMLLoader.load(getClass().getResource("addNewComponentGUI.fxml"));
        Scene addNewComponentScene = new Scene(addNewComponentView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(addNewComponentScene);
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

    private void clickNoise() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File f = new File("./data/Click.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
