package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AcademicHistory;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;


public class MainScreenController {

    @FXML
    public Button addTermButton;

    @FXML
    public Button addCourseButton;

    @FXML
    public Button addComponentButton;

    @FXML
    public Button addAssignButton;

    @FXML
    public Button viewAcademicHistory;

    @FXML
    void addTermClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent addTermView = FXMLLoader.load(getClass().getResource("addNewTermGUI.fxml"));
        Scene addTermScene = new Scene(addTermView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(addTermScene);
        window.show();
    }

    @FXML
    void addCourseClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent addNewCourseView = FXMLLoader.load(getClass().getResource("addNewCourseGUI.fxml"));
        Scene addNewCourseScene = new Scene(addNewCourseView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(addNewCourseScene);
        window.show();
    }

    @FXML
    void addComponentClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent addNewComponentView = FXMLLoader.load(getClass().getResource("addNewComponentGUI.fxml"));
        Scene addNewComponentScene = new Scene(addNewComponentView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(addNewComponentScene);
        window.show();
    }

    @FXML
    void addAssignClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent addNewAssignmentView = FXMLLoader.load(getClass().getResource("addNewAssignGUI.fxml"));
        Scene addNewAssignmentScene = new Scene(addNewAssignmentView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(addNewAssignmentScene);
        window.show();
    }

    @FXML
    void viewAcademicHistoryClicked(MouseEvent event)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clickNoise();
        Parent academicHistoryView = FXMLLoader.load(getClass().getResource("viewAcademicHistory.fxml"));
        Scene academicHistoryScene = new Scene(academicHistoryView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(academicHistoryScene);
        window.show();
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
