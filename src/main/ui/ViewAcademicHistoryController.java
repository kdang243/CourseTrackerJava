package ui;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.AcademicHistory;
import model.Term;
import persistance.Reader;
import persistance.Writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ViewAcademicHistoryController {
    public static final String SAVE_PATH = "./data/saved";

    AcademicHistory ah;
    AcademicHistory ahObject;

    @FXML
    public ListView ahListView;

    @FXML
    public Button ahEnter;

    @FXML
    public Button ahBackToMainMenu;

    @FXML
    public Button ahAddNewTerm;

    @FXML
    public Button ahRemoveTerm;

    @FXML
    void ahRemoveTermClicked(MouseEvent event) {
        ah = loadData();
        String selected = ahListView.getSelectionModel().getSelectedItem().toString();

        ah.removeTerm(selected);
        autoSave();
        ahListView.getItems().clear();

        ah = loadData();
        ArrayList<String> termNames = getTermNames();

        for (String name: termNames) {
            ahListView.getItems().add(name);
        }

    }

    @FXML
    void ahAddNewTermClicked(MouseEvent event) throws IOException {
        Parent addTermView = FXMLLoader.load(getClass().getResource("addNewTermGUI.fxml"));
        Scene addTermScene = new Scene(addTermView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(addTermScene);
        window.show();
    }

    @FXML
    void ahBackToMainMenuClicked(MouseEvent event) throws IOException {
        Parent rootView = FXMLLoader.load(getClass().getResource("CourseTrackerGUI.fxml"));
        Scene rootScene = new Scene(rootView);

        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be 13:12.
        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();

        window.setScene(rootScene);
        window.show();
    }

    @FXML
    void ahEnterClicked(MouseEvent event) {

        ah = loadData();
        ahListView.getItems().clear();
        ArrayList<String> termNames = getTermNames();

        for (String name: termNames) {
            ahListView.getItems().add(name);
        }

    }


    private ArrayList<String> getTermNames() {
        ArrayList<Term> terms = ah.getListOfTerms();
        ArrayList<String> termNames = new ArrayList<>();

        for (Term t: terms) {
            String name = t.getTermName();
            termNames.add(name);
        }

        return termNames;
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
}
