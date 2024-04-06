package dev.ln13.cse360project.backend;

import java.util.ArrayList;

public class Parent extends Patient {
    private final ArrayList<Child> children = new ArrayList<>();

    public Parent(String name, String dob, long heightCm, long weightKg, int restingHeartRate, long bloodPressurekPa, boolean childAccount, String pharmacyName, String perscribedMedication, String patientHistory, int childId, String visitSummary) {
        super(name, dob, heightCm, weightKg, restingHeartRate, bloodPressurekPa, childAccount, pharmacyName, perscribedMedication, patientHistory, childId, visitSummary);
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

    @Override
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
                """.formatted(getName(), getDob(), getHeightCm(), getWeightKg(), getRestingHeartRate(), getBloodPressurekPa(), isChildAccount(), getPharmacyName(), getVisitSummary(), getPerscribedMedication(), getChildId(), childrenString.toString());
    }

}
