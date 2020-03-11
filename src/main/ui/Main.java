package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import model.AcademicHistory;

public class Main extends Application {


    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //source: https://www.youtube.com/watch?v=XCgcQTQCfJQ&feature=youtu.be
        AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("CourseTrackerGUI.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }



}
