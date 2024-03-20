package dev.ln13.cse360project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientLogin {
    public Label appNameText;
    public Label portalName;
    public Label loginErrorText;
    public TextField nameField;
    public TextField dobField;

    public void initialize() {
        appNameText.setText("APP TITLE HERE");
        portalName.setText("Patient Health Portal");
        nameField.setPromptText("Patient Name");
        dobField.setPromptText("Date of Birth (MM/DD/YYYY)");
    }

    public void submitLogin(ActionEvent actionEvent) throws IOException {
        // TODO: Implement login functionality
        // FOR NOW, redirect to the logged-in view, but without a proper username
        MedicalApp.switchView("patient-portal.fxml", "Patient Health Portal",
                (Stage)((Node) actionEvent.getSource()).getScene().getWindow()
        );
    }
}
