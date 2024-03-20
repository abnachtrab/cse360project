package dev.ln13.cse360project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProviderLogin {
    public Label appNameText;
    public Label portalName;
    public Label loginErrorText;
    public TextField usernameField;
    public PasswordField passwordField;

    public void initialize() {
        appNameText.setText("APP TITLE HERE");
        portalName.setText("Provider Access Portal");
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");
    }

    public void submitLogin(ActionEvent actionEvent) throws IOException {
        // TODO: Implement login functionality
        // FOR NOW, redirect to the logged-in view, but without a proper username
        MedicalApp.switchView("provider-portal.fxml", "Provider Access Portal",
                (Stage)((Node) actionEvent.getSource()).getScene().getWindow()
        );
    }
}
