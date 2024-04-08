package dev.ln13.cse360project.backend;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
public class Nurse {
	private String name;
	private Patient activePatient;
	private String visitSummary;
	private String prescribedMedication;
	private String typedMessage;
	private String patientName;
	private String patientDob;
    private String messageHistory;
	private double patientHeight;
	private double patientWeight;
	private int patientRestingHeartRate;
	private double patientBloodPressurekPa;
	private String nurseId;
	private String nursePassword;
    private ArrayList<Patient> patients = new ArrayList<>();



    // Implementing the operations...
    public Nurse(String nurseId, String name) {
    	this.setNurseId(nurseId);
		this.name = name;
        this.activePatient = null; // Active patient is initially null
        this.patients = new ArrayList<>();
    }


    public Patient setPatientVitals(String activePatient,  double patientHeight, double patientWeight, int patientRestingHeartRate, double patientBloodPressurekPa) {
        // Assuming activePatient is the name of the patient
        if (this.activePatient.getName().equals(activePatient)) {
            this.activePatient.setHeightCm(patientHeight);
            this.activePatient.setWeightKg(patientWeight);
            this.activePatient.setRestingHeartRate(patientRestingHeartRate);
            this.activePatient.setBloodPressurekPa(patientBloodPressurekPa);
            return this.activePatient;
        } else {
            System.out.println("Active patient not found.");
            return null;
        }
    }

    public Patient createNewPatient(String activePatient) {
        Patient newPatient = new Patient(activePatient, "", 0, 0, 0, 0, false, "", "", "", 0, "");
        patients.add(newPatient);
        return newPatient;
    }

    public Patient getPatientInfo(String activePatient, String patientName, String patientDob) {
        for (Patient patient : patients) {
            if (patient.getName().equals(activePatient)) {
                if (patient.getName().equals(patientName) && patient.getDob().equals(patientDob)) {
                    return patient;
                }
            }
        }
        System.out.println("Patient not found.");
        return null;
    }

    public Messenger sendMessage(String activePatient, String typedMessage) {
        Messenger messenger = new Messenger();
        messenger.setMessageSent(typedMessage);
        messenger.setMessageSender(this.name);
        // Set the recipient and the received message as needed
        // messenger.setMessageRecipient(...);
        // messenger.setMessageReceived(...);
        messenger.deliverMessage();
        return messenger;
    }
//setters and getters most may be unneeded but putting them here for now
	public String getVisitSummary() {
    	if (activePatient != null) {
    		return activePatient.getVisitSummary();
    	}
    	else {
    		return "No active patient";
    	}
	}

	public void setVisitSummary(String visitSummary) {
		this.visitSummary = visitSummary;
	}

	public String getPrescribedMedication() {
    	if (activePatient != null) {
    		return activePatient.getPerscribedMedication();
    	}
    	else {
    		return "No active patient";
    	}
	}

	public void setPrescribedMedication(String prescribedMedication) {
		this.prescribedMedication = prescribedMedication;
	}

	public String getTypedMessage() {
		return typedMessage;
	}

	public void setTypedMessage(String typedMessage) {
		this.typedMessage = typedMessage;
	}

	public String getPatientName() {
    	if (activePatient != null) { //just checks if there is activePatient
    		return activePatient.getName(); //using activePatient as the patient object to get information
    	}
    	else {
    		return "No active patient";
    	}
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientDob() {
    	if (activePatient != null) { //just checks if there is activePatient
    		return activePatient.getDob(); //using activePatient as the patient object to get information
    	}
    	else {
    		return "No active patient";
    	}
	}

	public void setPatientDob(String patientDob) {
		this.patientDob = patientDob;
	}

	public double getPatientHeight() {
    	if (activePatient != null) { //just checks if there is activePatient
    		return activePatient.getHeightCm(); //using activePatient as the patient object to get information
    	}
    	else {
    		System.out.println("Patient not found.");
    		return 0;
    	}
	}

	public void setPatientHeight(long patientHeight) {
		this.patientHeight = patientHeight;
	}

	public double getPatientWeight() {
    	if (activePatient != null) { //just checks if there is activePatient
    		return activePatient.getWeightKg(); //using activePatient as the patient object to get information
    	}
    	else {
    		System.out.println("Patient not found.");
    		return 0;
    	}
	}

	public void setPatientWeight(long patientWeight) {
		this.patientWeight = patientWeight;
	}

	public int getPatientRestingHeartRate() {
    	if (activePatient != null) { //just checks if there is activePatient
    		return activePatient.getRestingHeartRate(); //using activePatient as the patient object to get information
    	}
    	else {
    		System.out.println("Patient not found.");
    		return 0;
    	}
	}

	public void setPatientRestingHeartRate(int patientRestingHeartRate) {
		this.patientRestingHeartRate = patientRestingHeartRate;
	}

	public double getPatientBloodPressurekPa() {
    	if (activePatient != null) { //just checks if there is activePatient
    		return activePatient.getBloodPressurekPa(); //using activePatient as the patient object to get information
    	}
    	else {
    		System.out.println("Patient not found.");
    		return 0;
    	}
	}

	public void setPatientBloodPressurekPa(long patientBloodPressurekPa) {
		this.patientBloodPressurekPa = patientBloodPressurekPa;
	}
    public Patient getActivePatient() {
        return activePatient;
    }

    public void setActivePatient(Patient activePatient) {
        for (Patient patient : patients) { // assuming 'patients' is a list of Patient objects
            if (patient.getName().equals(patientName)) {
                this.activePatient = patient;
                break;
            }
        }
    }
	public String getNursePassword() {
		return nursePassword;
	}
	public void setNursePassword(String nursePassword) {
		this.nursePassword = nursePassword;
	}
	public String getNurseId() {
		return nurseId;
	}
	public String setNurseId(String nurseId) {
		Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; // This will generate a random integer between 1000 and 9999
        char randomLetter = (char) ('A' + random.nextInt(26)); // This will generate a random letter from A to Z
        return randomNumber + String.valueOf(randomLetter);
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
	public void setMessageHistory(String messageHistory) {
		this.messageHistory = messageHistory;
	}
	public void setActivePatient(String patientName) {
	    for (Patient patient : patients) {
	        if (patient.getName().equals(patientName)) {
	            this.activePatient = patient;
	            return;
	        }
	    }
	    System.out.println("Patient not found.");
	}


}
