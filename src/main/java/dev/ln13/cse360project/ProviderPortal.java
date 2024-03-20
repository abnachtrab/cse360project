package dev.ln13.cse360project;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProviderPortal {

    public Label portalName;
    public Button viewAppointmentsButton;
    public Button viewPrescriptionsButton;
    public Button viewMessagesButton;
    public Button viewBillingButton;
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

    public void viewBilling(ActionEvent actionEvent) {
        System.out.println("Viewing billing");
    }

    public void settings(ActionEvent actionEvent) {
        System.out.println("Viewing settings");
    }
}
