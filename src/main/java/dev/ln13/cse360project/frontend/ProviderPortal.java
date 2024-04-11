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
    public Button viewPatientVitalsForm;
    public Button viewMessagesButton;
    public Button viewPatientIntakeButton;
    public Button settingsButton;

    public void initialize() {
        portalName.setText("Provider Access Portal");
    }

    public void viewAppointments(ActionEvent actionEvent) {
        MedicalApp.switchView("/dev/ln13/cse360project/layouts/recomendation-form.fxml", "Evaluation", (Stage)((Node) actionEvent.getSource()).getScene().getWindow());   
        System.out.println("Viewing  evaluation form");
    }

    public void viewPatientVitalsForm(ActionEvent actionEvent) {
      MedicalApp.switchView("/dev/ln13/cse360project/layouts/patient-vitals.fxml",
      "Patient Intake", (Stage)((Node) actionEvent.getSource()).getScene().getWindow());

      System.out.println("Viewing Patient Vitals Form");

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
