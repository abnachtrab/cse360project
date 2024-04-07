package dev.ln13.cse360project.backend;

import java.util.ArrayList;

public class Parent extends Patient {
	
    public String toString() {
        StringBuilder childrenString = new StringBuilder();
        for (Child child : children) {
            childrenString.append(child.toString()).append("\n");
        }
        return """
                Parent:
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
                    children: [
                        %s
                    ]
                """.formatted(getName(), getDob(), getHeightCm(), getWeightKg(), getRestingHeartRate(), getBloodPressurekPa(), isChildAccount(), getPharmacyName(), getVisitSummary(), getPerscribedMedication(), getChildId(getName()), childrenString.toString());
    }
	private final ArrayList<Child> children = new ArrayList<>();

	public Parent(String name, String dob, long heightCm, long weightKg, int restingHeartRate, long bloodPressurekPa, String pharmacyName, String perscribedMedication, String patientHistory, String visitSummary) {
		super(name, dob, heightCm, weightKg, restingHeartRate, bloodPressurekPa, false, pharmacyName, perscribedMedication, patientHistory, visitSummary);
	}

	public Parent(int id, String name, String dob, long heightCm, long weightKg, int restingHeartRate, long bloodPressurekPa, String pharmacyName, String perscribedMedication, String patientHistory, String visitSummary) {
		super(id, name, dob, heightCm, weightKg, restingHeartRate, bloodPressurekPa, false, pharmacyName, perscribedMedication, patientHistory, visitSummary);
	}

	public void addChild(Child child) {
		children.add(child);
	}

	public void removeChild(Child child) {
		children.remove(child);
	}

	public ArrayList<Child> getChildren() {
		return children;
	}

	public Child getChild(String name) {
		for (Child child : children) {
			if (child.getName().equals(name)) {
				return child;
			}
		}
		return null;
	}
	public int getChildId(String name) {
		for(Child child: children) {
			if(child.getName().equals(name)){
				return getPatientId();
			}
		}
		return 0;
	}



}

