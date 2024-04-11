package dev.ln13.cse360project.frontend;

import java.io.IOException;

import java.sql.SQLException;

import dev.ln13.cse360project.backend.Patient;
import dev.ln13.cse360project.backend.SQLInteraction;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatientVitals {
  public Label formTitle;
  public Label patientNameLabel;
  public Label patientDobLabel;
  public Label weightLabel;
  public Label heightLabel;
  public Label bloodPressureLabel;
  public Label heartRateLabel;

  public TextField patientNameField;
  public TextField patientDobField;
  public TextField weightField;
  public TextField heightField;
  public TextField bloodPressureField;
  public TextField heartRateField;

  public void submitData(ActionEvent actionEvent) {
    try {
      String patientName = patientNameField.getText();
      String patientDob = patientDobField.getText();
      double weight = Double.parseDouble(weightField.getText());
      double height = Double.parseDouble(heightField.getText());
      double bloodPressure = Double.parseDouble(bloodPressureField.getText());
      int heartRate = Integer.parseInt(heartRateField.getText());

      Patient p = SQLInteraction.getPatient(patientName, patientDob);
      if (p == null) {
        formTitle.setText("No Patient with that Data");
        return;
      }
      p.setWeightKg(weight);
      p.setHeightCm(weight);
      p.setRestingHeartRate(heartRate);
      p.setBloodPressurekPa(bloodPressure);

      SQLInteraction.editPatient(p);

      formTitle.setText("Done");
    } catch (NumberFormatException e) {
      formTitle.setText("Invalid Field Input");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
  }

}
