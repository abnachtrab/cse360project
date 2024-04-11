package dev.ln13.cse360project.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserType {
    public Label appNameText;
    public Label userTypeText;



    @FXML
    private void initialize() {
        appNameText.setText("APP TITLE HERE");
        userTypeText.setText("Are you signing in as a patient or a care provider?");
    }

    public void patientLogin(ActionEvent actionEvent) {
        MedicalApp.userType = "patient";
        MedicalApp.switchView("/dev/ln13/cse360project/layouts/patient-login.fxml", "Patient Health Portal",
                (Stage)((Node) actionEvent.getSource()).getScene().getWindow()
        );
    }

    public void providerLogin(ActionEvent actionEvent) {
        MedicalApp.userType = "provider";
        MedicalApp.switchView("/dev/ln13/cse360project/layouts/provider-login.fxml", "Provider Access Portal",
                (Stage)((Node) actionEvent.getSource()).getScene().getWindow()
        );
    }
}