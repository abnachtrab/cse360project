package dev.ln13.cse360project.frontend;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class doctorEval {
    public Label appNameText;
    public Label portalName;
    public Label loginErrorText;
    public TextField nameField;
    public TextField dobField;
    
   

    public void initialize() throws SQLException {
        appNameText.setText("Doctor Eval Form");
        portalName.setText("Patient Health Portal");
        nameField.setPromptText("Patient Name");
        dobField.setPromptText("Date of Birth (MM/DD/YYYY)");
               
    }

    public void submitLogin(ActionEvent actionEvent) throws SQLException, IOException {
    	SQLInteraction.main(null);
    	 String patientName = nameField.getText().trim();
         String dob = dobField.getText().trim();
         
			
        try { 
			Patient patient = SQLInteraction.getPatient(patientName, dob);
			 
			if (patient != null) {	
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