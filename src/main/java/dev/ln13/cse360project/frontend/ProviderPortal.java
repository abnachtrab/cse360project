package dev.ln13.cse360project.frontend;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProviderPortal {

    public Label portalName;
    public Button viewAppointmentsButton;
    public Button viewPrescriptionsButton;
    public Button viewMessagesButton;
    public Button viewPatientIntakeButton;
    public Button settingsButton;

    public void initialize() {
        portalName.setText("Provider Access Portal");
    }

    public void viewAppointments(ActionEvent actionEvent) {
        System.out.println("Viewing appointments");
    }

    public void viewPrescriptions(ActionEvent actionEvent) {
        System.out.println("Viewing prescriptions");
    }

    public void viewMessages(ActionEvent actionEvent) {
        System.out.println("Viewing messages");
    }

    public void viewPatientIntake(ActionEvent actionEvent) {
  		  MedicalApp.switchView("/dev/ln13/cse360project/layouts/patient-intake.fxml",
  		  "Patient Intake", (Stage)((Node) actionEvent.getSource()).getScene().getWindow());

  		  System.out.println("Viewing Patient Intake");

    }

    public void settings(ActionEvent actionEvent) {
        System.out.println("Viewing settings");
    }
}
