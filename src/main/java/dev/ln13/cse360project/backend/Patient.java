package dev.ln13.cse360project.backend;

import java.util.Random;

public class Patient {
	private String name;
	private String dob;
	private double heightCm;
	private double weightKg;
	private int restingHeartRate;
	private double bloodPressurekPa;
	private boolean childAccount;
	private String pharmacyName;
	private String visitSummary;
	private String perscribedMedication;
	private String messageHistory;
	private String typedMessage;
	private String patientHistory;
	private int patientId;
	protected int childId;


	public Patient(String name, String dob, double heightCm, double weightKg, int restingHeartRate, double bloodPressurekPa, boolean childAccount, String pharmacyName, String perscribedMedication, String patientHistory, String visitSummary) {
		this.patientId = (new Random()).nextInt(9999);
		this.name = name;
		this.dob = dob;
		this.heightCm = heightCm;
		this.weightKg = weightKg;
		this.restingHeartRate = restingHeartRate;
		this.bloodPressurekPa = bloodPressurekPa;
		this.childAccount = childAccount;
		this.pharmacyName = pharmacyName;
		this.perscribedMedication = perscribedMedication;
		this.patientHistory = patientHistory;
		this.visitSummary = visitSummary;
	}
	public Patient(int patientId, String name, String dob, double heightCm, double weightKg, int restingHeartRate, double bloodPressurekPa, boolean childAccount, String pharmacyName, String perscribedMedication, String patientHistory, String visitSummary) {
		this.name = name;
		this.dob = dob;
		this.heightCm = heightCm;
		this.weightKg = weightKg;
		this.restingHeartRate = restingHeartRate;
		this.bloodPressurekPa = bloodPressurekPa;
		this.childAccount = childAccount;
		this.pharmacyName = pharmacyName;
		this.perscribedMedication = perscribedMedication;
		this.patientHistory = patientHistory;
		this.visitSummary = visitSummary;
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public String getDob() {
		return dob;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getHeightCm() {
		return heightCm;
	}

	public void setHeightCm(double heightCm) {
		this.heightCm = heightCm;
	}

	public double getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(double weightKg) {
		this.weightKg = weightKg;
	}

	public int getRestingHeartRate() {
		return restingHeartRate;
	}

	public void setRestingHeartRate(int restingHeartRate) {
		this.restingHeartRate = restingHeartRate;
	}

	public double getBloodPressurekPa() {
		return bloodPressurekPa;
	}

	public void setBloodPressurekPa(double bloodPressurekPa) {
		this.bloodPressurekPa = bloodPressurekPa;
	}

	public boolean isChildAccount() {
		return childAccount;
	}

	public void setChildAccount(boolean childAccount) {
		this.childAccount = childAccount;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getVisitSummary() {
		return visitSummary;
	}

	public void setVisitSummary(String visitSummary) {
		this.visitSummary = visitSummary;
	}

	public String getPerscribedMedication() {
		return perscribedMedication;
	}

	public void setPerscribedMedication(String perscribedMedication) {
		this.perscribedMedication = perscribedMedication;
	}

	public String getMessageHistory() {
		return messageHistory;
	}

	public void setMessageHistory(String messageHistory) {
		this.messageHistory = messageHistory;
	}

	public String getTypedMessage() {
		return typedMessage;
	}

	public void setTypedMessage(String typedMessage) {
		this.typedMessage = typedMessage;
	}

	public String getPatientHistory() {
		return patientHistory;
	}

	public void setPatientHistory(String patientHistory) {
		this.patientHistory = patientHistory;
	}

	public void setPersonalInformation(String name, String dob) {
		this.name = name;
		this.dob = dob;
	}

	public void setPharmacyData(String pharmacyName, String perscribedMedicine) {
		this.pharmacyName = pharmacyName;
		this.perscribedMedication = perscribedMedicine;
	}

	public void getMessageHistory(String messageHistory) {
		this.messageHistory = messageHistory;
	}

	public int getPatientId() {
		return this.patientId;
	}

	public Messenger sendMessage(String typedMessage) {
		Messenger messenger = new Messenger();
		messenger.getMessageRecipient();
		messenger.setMessageSent(typedMessage);
		messenger.setMessageSender(this.name);

		messenger.deliverMessage();
		return messenger;
	}


	public void setVitals(double heightCm, double weightKg, int restingHeartRate, double bloodPressurekPa) {
		this.heightCm = heightCm;
		this.weightKg = weightKg;
		this.restingHeartRate = restingHeartRate;
		this.bloodPressurekPa = bloodPressurekPa;
	}

	public void getPatientHistory(String patientHistory) {
		this.patientHistory = patientHistory;
	}

	public void setPatientHistory(String patientHistory, String visitSummary) {
		this.patientHistory = patientHistory;
		this.visitSummary = visitSummary;
	}

	@Override
	public String toString() {
	    return """
	            Patient:
	                name: "%s",
	                dob: "%s",
	                heightCm: "%s",
	                weightKg: "%s",
	                restingHeartRate: "%s",
	                bloodPressurekPa: "%s",
	                childAccount: "%s",
	                pharmacyName: "%s",
	                visitSummary: "%s",
	                perscribedMedication: "%s",
	                messageHistory: "%s",
	                typedMessage: "%s",
	                childId: "%s",
	                patientHistory: "%s"
	            """.formatted(name, dob, heightCm, weightKg, restingHeartRate, bloodPressurekPa, childAccount, pharmacyName, visitSummary, perscribedMedication, messageHistory, typedMessage, childId, patientHistory);
	}

}
