package dev.ln13.cse360project.frontend;

import java.io.IOException;

import java.sql.SQLException;

import dev.ln13.cse360project.backend.Patient;
import dev.ln13.cse360project.backend.SQLInteraction;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatientIntake {

    public Label nameLabel;
    public Label dobLabel;
    public Label heightLabel;
    public Label weightLabel;
    public Label heartRateLabel;
    public Label bpLabel;
    public Label pharmacyLabel;
    
    public TextField nameField;
    public TextField dobField;
    public TextField heightField;
    public TextField weightField;
    public TextField heartRateField;
    public TextField bloodPressureField;
    public TextField pharmacyField;
    public TextField patientHistoryField;
    
    public Button savePatientButton;
    
    public void savePatient(ActionEvent actionEvent) {
        String patientName = nameField.getText();
        String patientDOB = dobField.getText();
        String height = heightField.getText();
        String weight = weightField.getText();
        String heartRate = heartRateField.getText();
        String bloodPressure = bloodPressureField.getText();
        String patientPharmacy = pharmacyField.getText();
        String patientHistory = patientHistoryField.getText();
        
        double patientHeight = Double.parseDouble(height);
        double patientWeight = Double.parseDouble(weight);
        int patientHeartRate = Integer.parseInt(heartRate);
        double patientBloodPressure = Double.parseDouble(bloodPressure);
        
        try {
        	Patient patient = new Patient(patientName, patientDOB, patientHeight, patientWeight, patientHeartRate, patientBloodPressure, false,  "none", patientPharmacy, "none", patientHistory);
			SQLInteraction.addPatient(patient);
			System.out.println("Patient Created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
}