package ui;

import com.google.gson.Gson;
import exceptions.PreExistingTermException;
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
import model.Term;
import persistance.Reader;
import persistance.Writer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddNewTermController {
    //TODO: addTermEnter.getText();
    //TODO:addTErmFeedback.setText();
    public static final String SAVE_PATH = "./data/saved";

    AcademicHistory ah;
    AcademicHistory ahObject;

    @FXML
    public TextArea termNameBox;

    @FXML
    public Button addTermEnter;

    @FXML
    public Text addTermFeedback;

    @FXML
    public Button addTermBackToMainMenu;

    @FXML
    public Button addNewCourse;

    @FXML
    void addTermEnterClicked(MouseEvent event)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clickNoise();
        ah = loadData(); //loads the data that we want to work on.

        String termName = termNameBox.getText();
        Term term = new Term(termName);
        try {
            ah.addTerm(term);
            addTermFeedback.setText("Term has been added! Autosaving...");
        } catch (PreExistingTermException e) {
            addTermFeedback.setText("That term already exists, please try again. Autosaving...");
        } finally {
            autoSave();
        }
    }


    @FXML
    void addTermBackToMainMenuClicked(MouseEvent event)
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
    void addNewCourseToTermClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent addNewCourseView = FXMLLoader.load(getClass().getResource("addNewCourseGUI.fxml"));
        Scene addNewCourseScene = new Scene(addNewCourseView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(addNewCourseScene);
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

    private void clickNoise() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File f = new File("./data/Click.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

}
