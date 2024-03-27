package dev.ln13.cse360project.backend;

import java.util.ArrayList;
import java.util.Arrays;

public class Child extends Patient {
    private ArrayList<Parent> parents = new ArrayList<>();

    public Child(String name, String dob) {
        super(name, dob);
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

    @Override
    public String toString() {
        StringBuilder parentsString = new StringBuilder();
        for (Parent parent : parents) {
            parentsString.append(parent.toString()).append("\n");
        }
        return """
                Child:
                    name: "%s",
                    dob: "%s",
                    parents: [
                        %s
                    ]
                """.formatted(getName(), getDob(), parentsString.toString());
    }


}
