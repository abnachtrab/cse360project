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
	private long patientHeight;
	private long patientWeight;
	private int patientRestingHeartRate;
	private long patientBloodPressurekPa;
	private int nurseId;
	private String nursePassword;
	private ArrayList<Patient> patients = new ArrayList<>();

	public Nurse(int nurseId, String nursePassword, String name, Patient activePatient, String visitSummary, String prescribedMedication, String typedMessage, String patientName, String patientDob, long patientHeight,
	             long patientWeight, int patientRestingHeartRate, long patientBloodPressurekPa) {
		this.nurseId = nurseId;
		this.nursePassword = nursePassword;
		this.name = name;
		this.activePatient = activePatient;
		this.visitSummary = visitSummary;
		this.prescribedMedication = prescribedMedication;
		this.typedMessage = typedMessage;
		this.patientName = patientName;
		this.patientDob = patientDob;
		this.patientHeight = patientHeight;
		this.patientWeight = patientWeight;
		this.patientRestingHeartRate = patientRestingHeartRate;
		this.patientBloodPressurekPa = patientBloodPressurekPa;


	}
	public Nurse(String name, Patient activePatient, String visitSummary, String prescribedMedication, String typedMessage, String patientName, String patiendDob, long patientHeight,
	             long patientWeight, int patientRestinHeartRate, long patientBloodPressurekPa, String patientDob, int patientRestingHeartRate) {
		
		this.nurseId = (new Random()).nextInt(9999);
		this.name = name;
		this.activePatient = activePatient;
		this.visitSummary = visitSummary;
		this.prescribedMedication = prescribedMedication;
		this.typedMessage = typedMessage;
		this.patientName = patientName;
		this.patientDob = patientDob;
		this.patientHeight = patientHeight;
		this.patientWeight = patientWeight;
		this.patientRestingHeartRate = patientRestingHeartRate;
		this.patientBloodPressurekPa = patientBloodPressurekPa;


	}




	// Implement the operations...

	public Patient setPatientVitals(String activePatient, long patientHeight, long patientWeight, int patientRestingHeartRate, long patientBloodPressurekPa) {
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
		Patient newPatient = new Patient(activePatient, "", 0, 0, 0, 0, false, "", "", "", "");
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
//setters and getters most may be unndeeded but putting them here for now
	public String getVisitSummary() {
		return visitSummary;
	}

	public void setVisitSummary(String visitSummary) {
		this.visitSummary = visitSummary;
	}

	public String getPrescribedMedication() {
		return prescribedMedication;
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
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientDob() {
		return patientDob;
	}

	public void setPatientDob(String patientDob) {
		this.patientDob = patientDob;
	}

	public long getPatientHeight() {
		return patientHeight;
	}

	public void setPatientHeight(long patientHeight) {
		this.patientHeight = patientHeight;
	}

	public long getPatientWeight() {
		return patientWeight;
	}

	public void setPatientWeight(long patientWeight) {
		this.patientWeight = patientWeight;
	}

	public int getPatientRestingHeartRate() {
		return patientRestingHeartRate;
	}

	public void setPatientRestingHeartRate(int patientRestingHeartRate) {
		this.patientRestingHeartRate = patientRestingHeartRate;
	}

	public long getPatientBloodPressurekPa() {
		return patientBloodPressurekPa;
	}

	public void setPatientBloodPressurekPa(long patientBloodPressurekPa) {
		this.patientBloodPressurekPa = patientBloodPressurekPa;
	}
	public String getNursePassword() {
		return nursePassword;
	}
	public void setNursePassword(String nursePassword) {
		this.nursePassword = nursePassword;
	}
	public int getNurseId() {
		return nurseId;
	}
	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}
}
