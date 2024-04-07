package dev.ln13.cse360project.backend;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

public class Doctor {
    private String firstName;
    private String lastName;
    private Patient activePatient;
    private String visitSummary;
    private String prescribedMedication;
    private String typedMessage;
    private ArrayList<Patient> patients = new ArrayList<>();

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Patient getActivePatient() {
        return activePatient;
    }

    public String getVisitSummary() {
        return visitSummary;
    }

    public String getPrescribedMedication() {
        return prescribedMedication;
    }

    public String getTypedMessage() {
        return typedMessage;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setActivePatient(Patient activePatient) {
        this.activePatient = activePatient;
    }

    public void setActivePatient(String patientName) {
        for (Patient patient : patients) { // assuming 'patients' is a list of Patient objects
            if (patient.getName().equals(patientName)) {
                this.activePatient = patient;
                break;
            }
        }
    }

    public void setPrescribedMedication(String prescribedMedication) {
        this.prescribedMedication = prescribedMedication;
    }

    public void setTypedMessage(String typedMessage) {
        this.typedMessage = typedMessage;
    }

    // Operations
    public Patient patientRetriever(String requestedName) {
        for (Patient patient : patients) {
            if (patient.getName().equals(requestedName)) {
                return patient;
            }
        }
        System.out.println("Patient not found.");
        return null;
    }
    public Patient getPatientVisitInfo(String activePatient, String patientPersonalInfo, String patientVitals) {
        // Implement this method
        return null;
    }

    public void getPerscribedMedicine(String activePatient, String perscribedMedicine) {
        // Implement this method
    }

    public void getLastVisitSummary(String activePatient) {
        // Implement this method
    }

    public void setPerscribedMedicine(String activePatient, String perscribedMedication) {
        // Implement this method
    }

    public void setVisitSummary(String activePatient, String visitSummary) {
        // Implement this method
    }

    public void addMessageToHistory(String message) {
        try {
            FileWriter writer = new FileWriter("messageHistory.txt", true); // true means the file is opened in append mode
            writer.write(message + "\n"); // write the message and a newline
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public String getMessageHistory() {
        String messageHistory = activePatient.getMessageHistory();
        return messageHistory;
    }

    public Messenger sendMessage(String activePatient, String typedMessage) {
        Messenger messenger = new Messenger();
        messenger.setMessageSent(typedMessage);
        messenger.setMessageSender(this.firstName);
        // Set the recipient and the received message as needed
        // messenger.setMessageRecipient(...);
        // messenger.setMessageReceived(...);
        messenger.deliverMessage();
        return messenger;
    }
}
