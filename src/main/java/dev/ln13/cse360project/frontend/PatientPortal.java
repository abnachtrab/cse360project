package dev.ln13.cse360project.frontend;

import dev.ln13.cse360project.backend.Patient;
import dev.ln13.cse360project.backend.SQLInteraction;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class PatientPortal {

    public Label portalName;
    public Button viewAppointmentsButton;
    public Button viewPrescriptionsButton;
    public Button viewMedicalHistoryButton;
    public Button viewBillingButton;
    public Button settingsButton;
    public Button viewMessagesButton;
    public Label name;
    public Label dob;
    public Label heightCm;
    public Label weightKg;
    public Label restingHeartRate;
    public Label bloodPressurekPa;
    public Label visitSummary;

    public void initialize() {
        portalName.setText("Patient Health Portal");
        try {
            Patient patient = SQLInteraction.getPatient(MedicalApp.patientName, MedicalApp.dob);
            setPatientInfo(patient.getName(), patient.getDob(), patient.getHeightCm(), patient.getWeightKg(), patient.getRestingHeartRate(), patient.getBloodPressurekPa(), patient.getVisitSummary());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void setPatientInfo(String name, String dob, double heightCm, double weightKg, int restingHeartRate, double bloodPressurekPa, String visitSummary) {
        this.name.setText("Name: " + name);
        this.dob.setText("Date of Birth: " + dob);
        this.heightCm.setText("Height: " + heightCm + " cm");
        this.weightKg.setText("Weight: " + weightKg + " kg");
        this.restingHeartRate.setText("Resting Heart Rate: " + restingHeartRate + " bpm");
        this.bloodPressurekPa.setText("Blood Pressure: " + bloodPressurekPa + " kPa");
        this.visitSummary.setText("Visit Summary: " + visitSummary);
    }
}
