package dev.ln13.cse360project.frontend;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import dev.ln13.cse360project.backend.Doctor;
import dev.ln13.cse360project.backend.Nurse;
import dev.ln13.cse360project.backend.SQLInteraction;;

public class ProviderLogin {
    public Label appNameText;
    public Label portalName;
    public Label loginErrorText;
    public TextField usernameField;
    public PasswordField passwordField;

    public void initialize() {
        appNameText.setText("APP TITLE HERE");
        portalName.setText("Provider Access Portal");
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");
    }

    public void submitLogin(ActionEvent actionEvent) throws IOException {
        // TODO: Implement login functionality
        // FOR NOW, redirect to the logged-in view, but without a proper username
    	String providerID = usernameField.getText().trim();
        String dob = passwordField.getText().trim();


       try {
       		int ID = Integer.parseInt(providerID);
			Nurse nurse = SQLInteraction.getNurse(ID, dob);
			Doctor doctor = SQLInteraction.getDoctor(ID, dob);

			if (nurse != null || doctor != null) {
				 MedicalApp.switchView("/dev/ln13/cse360project/layouts/provider-portal.fxml", "Provider Access Portal",
			                (Stage)((Node) actionEvent.getSource()).getScene().getWindow()
			        );
			}
			else {
				loginErrorText.setText("Invalid credentials. Please try again.");
			  	}
		}catch (NumberFormatException e) {
	        loginErrorText.setText("Invalid provider ID.");
	        }
		catch (SQLException e) {
			  	e.printStackTrace();
			  	loginErrorText.setText("Database error occurred");
		}

       }
	   public void backToMain(ActionEvent actionEvent) throws IOException {
	        MedicalApp.switchView("/dev/ln13/cse360project/layouts/user-type.fxml", "Medical App",
	                (Stage)((Node) actionEvent.getSource()).getScene().getWindow()
	        );
	    }
		public void createAccount(ActionEvent actionEvent) throws IOException {
	        MedicalApp.switchView("/dev/ln13/cse360project/layouts/create-account.fxml", "Create Account",
	                (Stage)((Node) actionEvent.getSource()).getScene().getWindow());
					 
	        
	    }
	}
		



