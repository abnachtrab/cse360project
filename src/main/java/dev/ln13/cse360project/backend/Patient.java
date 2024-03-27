package dev.ln13.cse360project.backend;

public class Patient {
    private String name;
    private String dob;

    public Patient(String name, String dob) {
        this.name = name;
        this.dob = dob;
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

    @Override
    public String toString() {
        return """
                Patient:
                    name: "%s",
                    dob: "%s"
                """.formatted(name, dob);
    }
}
