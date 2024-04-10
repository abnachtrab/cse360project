package dev.ln13.cse360project.frontend;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MedicalApp extends Application {
    public static final int DEFAULT_WINDOW_WIDTH = 800;
    public static final int DEFAULT_WINDOW_HEIGHT = 600;

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
          stage.getIcons().add(new Image(Objects.requireNonNull(MedicalApp.class.getResource
                ("/dev/ln13/cse360project/images/logo.jpg")).toExternalForm()));
      
        Parent root = new FXMLLoader(MedicalApp.class.getResource("/dev/ln13/cse360project/layouts/splash.fxml")).load();
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        PauseTransition splashScreenDelay = getPauseTransition(stage);
        splashScreenDelay.play();
    }

    private static PauseTransition getPauseTransition(Stage stage) {
        PauseTransition splashScreenDelay = new PauseTransition(javafx.util.Duration.seconds(2));
        splashScreenDelay.setOnFinished(e -> {
            try {
            	stage.hide();
            	Stage mainStage = new Stage();
            	mainStage.initStyle(javafx.stage.StageStyle.DECORATED);
                mainStage.setX(DEFAULT_WINDOW_WIDTH);
                mainStage.setY(DEFAULT_WINDOW_HEIGHT);
                
                switchView("/dev/ln13/cse360project/layouts/user-type.fxml", "Medical App", mainStage);
                
           
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        return splashScreenDelay;
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
                Objects.requireNonNull(MedicalApp.class.getResource("/dev/ln13/cse360project/styles/style.css")).toExternalForm()
        );
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(MedicalApp.class.getResourceAsStream("/dev/ln13/cse360project/images/logo.jpg"))));
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}