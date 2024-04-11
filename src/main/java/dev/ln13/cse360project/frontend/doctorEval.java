package dev.ln13.cse360project.frontend;

import java.io.IOException;
import java.sql.SQLException;

import dev.ln13.cse360project.backend.Patient;
import dev.ln13.cse360project.backend.SQLInteraction;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class doctorEval extends ProviderPortal {
 
    public Label patientName;
    public TextField perscriptionField;
    public TextField recomendationField;
    
   

    public void initializeEval() throws SQLException {
        perscriptionField.setPromptText("");
        recomendationField.setPromptText("Date of Birth (MM/DD/YYYY)");  
    }

    public void saveForm(ActionEvent actionEvent) throws SQLException, IOException {
    	
    	 String perscriptionString = perscriptionField.getText().trim();
         String recomendatioString = recomendationField.getText().trim();
        System.out.println("Perscription: " + perscriptionString);  
        System.out.println("Recomendation: " + recomendatioString);
			 }
        
    }
