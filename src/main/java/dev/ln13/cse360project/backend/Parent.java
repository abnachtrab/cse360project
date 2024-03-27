package dev.ln13.cse360project.backend;

import java.util.ArrayList;

public class Parent extends Patient {
    private final ArrayList<Child> children = new ArrayList<>();

    public Parent(String name, String dob) {
        super(name, dob);
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
                    children: [
                        %s
                    ]
                """.formatted(getName(), getDob(), childrenString.toString());
    }
}
