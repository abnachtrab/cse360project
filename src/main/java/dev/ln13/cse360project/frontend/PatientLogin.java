package dev.ln13.cse360project.frontend;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dev.ln13.cse360project.backend.Patient;
import dev.ln13.cse360project.backend.SQLInteraction;

public class PatientLogin {
    public Label appNameText;
    public Label portalName;
    public Label loginErrorText;
    public TextField nameField;
    public TextField dobField;
    
    private Connection connection;

    public void initialize() throws SQLException {
        appNameText.setText("APP TITLE HERE");
        portalName.setText("Patient Health Portal");
        nameField.setPromptText("Patient Name");
        dobField.setPromptText("Date of Birth (MM/DD/YYYY)");
               
    }

    public void submitLogin(ActionEvent actionEvent) throws SQLException, IOException {
    	 String patientName = nameField.getText().trim();
         String dob = dobField.getText().trim();
         
			
        try { 
			Patient patient = SQLInteraction.getPatient(patientName, dob);
			 
			if (patient != null) {
                MedicalApp.patientName = patientName;
                MedicalApp.dob = dob;
				MedicalApp.switchView("/dev/ln13/cse360project/layouts/patient-portal.fxml", "Patient Health Portal", (Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
				System.out.println("Login Successful"); 
			  	} 
			else {
				loginErrorText.setText("Invalid credentials. Please try again."); 
			  	}
		}
		catch (SQLException e) { 
			  	e.printStackTrace();
			  	loginErrorText.setText("Database error occurred"); 
		}
        
    }
}

