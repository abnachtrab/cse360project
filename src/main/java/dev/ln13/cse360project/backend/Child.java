package dev.ln13.cse360project.backend;

import java.util.ArrayList;
import java.util.Arrays;

public class Child extends Patient {
    public String toString() {
        StringBuilder parentsString = new StringBuilder();
        for (Parent parent : parents) {
            parentsString.append(parent.toString()).append("\n");
        }
        return """
                Child:
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
                    childId: "%s",
                    parents: [
                        %s
                    ]
                """.formatted(getName(), getDob(), getHeightCm(), getWeightKg(), getRestingHeartRate(), getBloodPressurekPa(), isChildAccount(), getPharmacyName(), getVisitSummary(), getPerscribedMedication(), getPatientId(), parentsString.toString());
    }
	private ArrayList<Parent> parents = new ArrayList<>();

	public Child(String name, String dob, long heightCm, long weightKg, int restingHeartRate, long bloodPressurekPa, boolean childAccount, String pharmacyName, String perscribedMedication, String patientHistory, String visitSummary) {
		super(name, dob, heightCm, weightKg, restingHeartRate, bloodPressurekPa, true, pharmacyName, perscribedMedication, patientHistory, visitSummary);
	}

	public Child(int id, String name, String dob, long heightCm, long weightKg, int restingHeartRate, long bloodPressurekPa, boolean childAccount, String pharmacyName, String perscribedMedication, String patientHistory, String visitSummary) {
		super(id, name, dob, heightCm, weightKg, restingHeartRate, bloodPressurekPa, true, pharmacyName, perscribedMedication, patientHistory, visitSummary);
	}

	public ArrayList<Parent> getParents() {
		return parents;
	}

	public void setParents(Parent[] parents) {
		this.parents = new ArrayList<>();
		this.parents.addAll(Arrays.asList(parents));
	}

	public void addParent(Parent parent) {
		parents.add(parent);
	}

	public void removeParent(Parent parent) {
		parents.remove(parent);
	}

	

	
}
