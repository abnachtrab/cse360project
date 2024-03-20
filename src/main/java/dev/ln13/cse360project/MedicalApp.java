package dev.ln13.cse360project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MedicalApp extends Application {
    public static final int DEFAULT_WINDOW_WIDTH = 800;
    public static final int DEFAULT_WINDOW_HEIGHT = 600;

    @Override
    public void start(Stage stage) throws IOException {
        switchView("user-type.fxml", "Medical App", stage);
    }

    public static void switchView(String fxml, String title, Stage stage) throws IOException {
        Parent root = new FXMLLoader(MedicalApp.class.getResource(fxml)).load();
        double winX = stage.getWidth();
        double winY = stage.getHeight();
        // If the window size is NaN, set it to the default size
        if (Double.isNaN(winX) || Double.isNaN(winY)) {
            winX = DEFAULT_WINDOW_WIDTH;
            winY = DEFAULT_WINDOW_HEIGHT;
        }
        Scene scene = new Scene(root, winX, winY);
        scene.getStylesheets().add(
                Objects.requireNonNull(MedicalApp.class.getResource("style.css")).toExternalForm()
        );
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}