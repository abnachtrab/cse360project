package dev.ln13.cse360project.frontend;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PatientPortal {

    public Label portalName;
    public Button viewAppointmentsButton;
    public Button viewPrescriptionsButton;
    public Button viewMedicalHistoryButton;
    public Button viewBillingButton;
    public Button settingsButton;
    public Button viewMessagesButton;

    public void initialize() {
        portalName.setText("Patient Health Portal");
    }

    public void viewAppointments(ActionEvent actionEvent) {
        System.out.println("Viewing appointments");
    }

    public void viewPrescriptions(ActionEvent actionEvent) {
        System.out.println("Viewing prescriptions");
    }

    public void viewMedicalHistory(ActionEvent actionEvent) {
        System.out.println("Viewing medical history");
    }

    public void viewBilling(ActionEvent actionEvent) {
        System.out.println("Viewing billing");
    }

    public void settings(ActionEvent actionEvent) {
        System.out.println("Viewing settings");
    }

    public void viewMessages(ActionEvent actionEvent) {
        MedicalApp.switchView("/dev/ln13/cse360project/layouts/messenger.fxml", "Messenger", (Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }
}
