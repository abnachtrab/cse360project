package dev.ln13.cse360project.frontend;

import java.io.IOException;
import java.sql.SQLException;

import dev.ln13.cse360project.backend.Patient;
import dev.ln13.cse360project.backend.SQLInteraction;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class doctorEval extends ProviderPortal {
 
    public Label patientNameLabel;
    public TextField patientNameField;
    public Label patientDOBLabel;
    public TextField patientDOBField;
    public TextField perscriptionField;
    public TextField recomendationField;
    public Label errorLabel;
    
   

    public void initializeEval() throws SQLException {
        patientNameLabel.setText("Patient Name: ");
        patientDOBLabel.setText("Patient DOB: ");
        perscriptionField.setPromptText("");
        recomendationField.setPromptText("Date of Birth (MM/DD/YYYY)");  
    }

    public void saveForm(ActionEvent actionEvent) throws SQLException, IOException {
        String patientName = patientNameField.getText().trim();
        String patientDOB = patientDOBField.getText().trim();
        String perscriptionString = perscriptionField.getText().trim();
        String recomendatioString = recomendationField.getText().trim();
        Patient patient = SQLInteraction.getPatient(patientName, patientDOB);
        if (patient == null) {
            System.out.println("No patient with that data");
            errorLabel.setText("No patient with that data");
            
        }
        patient.setPrescribedMedication(perscriptionString);
        patient.setVisitSummary(recomendatioString);
        SQLInteraction.editPatient(patient);
        System.out.println("Perscription: " + perscriptionString);  
        System.out.println("Recomendation: " + recomendatioString);
			 }
        
    }
