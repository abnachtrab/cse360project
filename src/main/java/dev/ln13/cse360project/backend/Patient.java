package dev.ln13.cse360project.backend;

public class Patient {
    private String name;
    private String dob;
    private long heightCm;
    private long weightKg;
    private int restingHeartRate;
    private long bloodPressurekPa;
    private boolean childAccount;
    private String pharmacyName;
    private String visitSummary;
    private String perscribedMedication;
    private String messageHistory;
    private String typedMessage;
    private int childId;
    private String patientHistory;

    public Patient(String name, String dob, long heightCm, long weightKg, int restingHeartRate, long bloodPressurekPa, boolean childAccount, String pharmacyName, String perscribedMedication, String patientHistory, int childId, String visitSummary) {
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
        this.childId = childId;
        this.visitSummary = visitSummary;
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

	public long getHeightCm() {
		return heightCm;
	}

	public void setHeightCm(long heightCm) {
		this.heightCm = heightCm;
	}

	public long getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(long weightKg) {
		this.weightKg = weightKg;
	}

	public int getRestingHeartRate() {
		return restingHeartRate;
	}

	public void setRestingHeartRate(int restingHeartRate) {
		this.restingHeartRate = restingHeartRate;
	}

	public long getBloodPressurekPa() {
		return bloodPressurekPa;
	}

	public void setBloodPressurekPa(long bloodPressurekPa) {
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

	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public String getPatientHistory() {
		return patientHistory;
	}

	public void setPatientHistory(String patientHistory) {
		this.patientHistory = patientHistory;
	}
	public Child setChildInfo(int childId) {
		return null;
        // return a Child object
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

    public Messenger sendMessage(String typedMessage) {
        Messenger messenger = new Messenger();
        messenger.setMessageSent(typedMessage);
        messenger.setMessageSender(this.name);
        // Set the recipient and the received message as needed
        // messenger.setMessageRecipient(...);
        // messenger.setMessageReceived(...);
        messenger.deliverMessage();
        return messenger;
    }


    public void setVitals(long heightCm, long weightKg, int restingHeartRate, long bloodPressurekPa) {
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
