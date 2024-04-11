package dev.ln13.cse360project.frontend;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dev.ln13.cse360project.backend.Doctor;
import dev.ln13.cse360project.backend.Nurse;
import dev.ln13.cse360project.backend.SQLInteraction;

import java.sql.SQLException;

public class createAccount {

    public Label appNameText;
    public Label portalName;
    public Label loginErrorText;
    public TextField firstName;
    public TextField lastName;
    public TextField password;
    public TextField confirmPassword;
    public CheckBox isNurse;
    public Label nurseCheckboxText;


    public void initialize() throws SQLException {

        appNameText.setText("APP TITLE HERE");
        portalName.setText("Account Creation Portal");
        firstName.setPromptText("Provider First Name");
        lastName.setPromptText("Provider Last Name");
        password.setPromptText("Enter Password");
        confirmPassword.setPromptText("Confirm Password");
        nurseCheckboxText.setText("Check if you are a Nurse");
    }

    public void submitCreateAccount(ActionEvent actionEvent) throws SQLException, IOException {
        if(!isNurse.isSelected()){
            String firstNameString = firstName.getText().trim();   
            String lastNameString = lastName.getText().trim();
            String passwordString = password.getText().trim();  
            String confirmPasswordString = confirmPassword.getText().trim();
            if(!passwordString.equals(confirmPasswordString)){
                loginErrorText.setText("Passwords do not match. Please try again.");
                return;
            }
            Doctor provider = new Doctor(firstNameString, lastNameString, confirmPasswordString, null,"","", "");
            int docId = provider.getDocId();
            appNameText.setText("Your ID is: " + docId);
            SQLInteraction.addDoctor(provider);
         
           
            
                if (provider != null) {	
                    MedicalApp.switchView("/dev/ln13/cse360project/layouts/provider-login.fxml", "Provider Health Portal", (Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
                    System.out.println("Account creation successful. Redirecting to provider login."); 
                      } 
                else {
                    loginErrorText.setText("Invalid credentials. Please try again."); 
                      }

                    }
        else{
            String firstNameString = firstName.getText().trim().concat(lastName.getText().trim());  
            String passwordString = password.getText().trim();  
            String confirmPasswordString = confirmPassword.getText().trim();
            Nurse provider = new Nurse(firstNameString, confirmPasswordString, null, "", "", "", "", "", 0, 0, 0,0);
            int nurseId = provider.getNurseId();
            appNameText.setText("Your ID is: " + nurseId);
            SQLInteraction.addNurse(provider);

                if (provider != null) {	
                    MedicalApp.switchView("/dev/ln13/cse360project/layouts/provider-login.fxml", "Provider Health Portal", (Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
                    System.out.println("Account creation successful. Redirecting to provider login."); 
                      } 
                else {
                    loginErrorText.setText("Invalid credentials. Please try again."); 
                      }
            }
          
        }
       public void checkForNurse(ActionEvent actionEvent){
           if(isNurse.isSelected()){
        
            
            lastName.setVisible(false);
           }
           else{
            lastName.setVisible(true);
           }
        }
        public void backToMain(ActionEvent actionEvent) throws IOException {
	        MedicalApp.switchView("/dev/ln13/cse360project/layouts/user-type.fxml", "Medical App",
	                (Stage)((Node) actionEvent.getSource()).getScene().getWindow()
	        );
	    }
        
           
       
       
   }


