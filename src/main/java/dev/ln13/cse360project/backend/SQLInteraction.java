package dev.ln13.cse360project.backend;

import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Random;

public class SQLInteraction {
	static Connection conn;
	static Statement stmt;

	 public static void main(String[] args) throws SQLException {
	 	conn = setupConnection();

	 	// test scenario
	 	Patient p = new Patient("Joe Mama","1984",178.2, 73.1, 90, 42.1, false, "CVS", "None", "None", "Eat less");
	 	Doctor d = new Doctor(1, "Gregory", "House", "gamerjoe", p,  "None", "None", "None");
     Nurse n = new Nurse(2, "Allison Cameron", "gamerjoe", p, "None", "None", "None", p.getName(), p.getDob(), p.getHeightCm(), p.getWeightKg(), p.getRestingHeartRate(), p.getBloodPressurekPa());
     Parent pa = new Parent("Bob", "1942", 128, 129, 29, 912,"CVS", "None", "None", "None");
     Child c = new Child("Bob2", "1942", 128, 129, 29, 912, true ,"CVS", "None", "None", "None");
	 	addPatient(p);
	 	addDoctor(d);
     addNurse(n);
     addNursePatient(n, p);
     addDoctorPatient(d,p);

     addChild(c);
     addParent(pa);
     addParentChild(pa, c);
     addNursePatient(n, c);
     addNursePatient(n, pa);
	 }

	public static Connection setupConnection() throws SQLException {
		// Check if database exists
		conn = DriverManager.getConnection("jdbc:sqlite:medical.db");
		stmt = conn.createStatement();

		ResultSet r = conn.getMetaData().getTables(null, null, null, null);
		ArrayList<String> tables = new ArrayList<>();
		while (r.next()) {
			tables.add(r.getString("TABLE_NAME"));
		}
		if (!tables.contains("Patients")) {
			System.out.println("Creating Patients table...");
			stmt.execute("CREATE TABLE Patients (id INTEGER PRIMARY KEY, name VARCHAR(255) NOT NULL, dob VARCHAR(255) NOT NULL, height REAL, weight REAL, heart_rate INTEGER, blood_pressure REAL, child_account INTEGER, pharmacy TEXT, visit_summary TEXT, prescribed_medication TEXT, message_history TEXT, patient_history TEXT)");
		}
		if (!tables.contains("Doctors")) {
			System.out.println("Creating Doctors table...");
			stmt.execute("CREATE TABLE Doctors ( id INTEGER PRIMARY KEY, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL, active_patient INTEGER, visit_summary TEXT, prescribed_medication TEXT, password VARCHAR(255) NOT NULL, salt VARCHAR(255) NOT NULL, FOREIGN KEY (active_patient) REFERENCES Patients(id))");
		}
		if (!tables.contains("Doctors_Patients")) {
			System.out.println("Creating Doctors_Patients table...");
			stmt.execute("CREATE TABLE Doctors_Patients ( doctor_id INTEGER, patient_id INTEGER, FOREIGN KEY (doctor_id) REFERENCES Doctors(id), FOREIGN KEY (patient_id) REFERENCES Patients(id), PRIMARY KEY (doctor_id, patient_id))");
		}
    if (!tables.contains("Nurses")) {
      System.out.println("Creating Nurses table...");
      stmt.execute("CREATE TABLE Nurses (id INTEGER PRIMARY KEY, name VARCHAR(255) NOT NULL, active_patient INTEGER, visit_summary TEXT, prescribed_medication TEXT, password VARCHAR(255) NOT NULL, salt VARCHAR(255) NOT NULL, FOREIGN KEY (active_patient) REFERENCES Patients(id))");
    }
    if (!tables.contains("Nurses_Patients")) {
			System.out.println("Creating Nurses_Patients table...");
			stmt.execute("CREATE TABLE Nurses_Patients ( nurse_id INTEGER, patient_id INTEGER, FOREIGN KEY (nurse_id) REFERENCES Nurses(id), FOREIGN KEY (patient_id) REFERENCES Patients(id), PRIMARY KEY (nurse_id, patient_id))");
		}
    if (!tables.contains("Parents")) {
			System.out.println("Creating Parents table...");
			stmt.execute("CREATE TABLE Parents ( id INTEGER PRIMARY KEY, patient_id INTEGER, FOREIGN KEY (patient_id) REFERENCES Patients(id))");
		}
    if (!tables.contains("Children")) {
			System.out.println("Creating Children table...");
			stmt.execute("CREATE TABLE Children ( id INTEGER PRIMARY KEY, patient_id INTEGER, FOREIGN KEY (patient_id) REFERENCES Patients(id))");
		}
    if (!tables.contains("Parents_Children")) {
			System.out.println("Creating Parents_Children table...");
			stmt.execute("CREATE TABLE Parents_Children ( parent_id INTEGER, child_id INTEGER, FOREIGN KEY (parent_id) REFERENCES Parents(id), FOREIGN KEY (child_id) REFERENCES Children(id), PRIMARY KEY (parent_id, child_id))");
		}
		return conn;
	}

	public static String sanitizeInput(String input) {
		// add more possible payloads
		String[] payloads = {
			"'",
			"--",
			"\"",
			"`",
			"#",
			"%"
		};
        for (String payload : payloads) {
            input = input.replaceAll(payload, "");
        }
		return input;
	}

	public static String getSHA256(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes());
			return bytesToHex(md.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xff & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
		return hexString.toString();
	}

	public static String getSaltString() {
		String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 32) {
			int index = (int) (rnd.nextFloat() * CHARS.length());
			salt.append(CHARS.charAt(index));
		}
        return salt.toString();
	}
  // PARENT
  public static void addParent(Parent parent) throws SQLException {
    String sql = String.format("INSERT INTO Patients VALUES ('%d', '%s', '%s', '%f', '%f', '%d', '%f', '%d', '%s', '%s', '%s', '%s', '%s')", parent.getPatientId(), parent.getName(), parent.getDob(), parent.getHeightCm(), parent.getWeightKg(), parent.getRestingHeartRate(), parent.getBloodPressurekPa(), 0, parent.getPharmacyName(), parent.getVisitSummary(), parent.getPrescribedMedication(), parent.getMessageHistory(), parent.getPatientHistory());
		stmt.executeUpdate(sql);
    sql = String.format("INSERT INTO Parents VALUES ('%d', '%d')", parent.getPatientId(), parent.getPatientId());
    stmt.executeUpdate(sql);
  }

  public static Parent getParent(String name, String dob) throws SQLException {
    String sql = String.format("SELECT * FROM Patients WHERE name='%s' AND dob='%s'", name, dob);
		// sql injection moment
		ResultSet r = stmt.executeQuery(sql);
		if (!r.next()) {
			return null;
		}
    // check if patient is a parent
    sql = String.format("SELECT * FROM Parents WHERE id='%d'", Integer.parseInt(r.getString("id")));
    r = stmt.executeQuery(sql);
    if (!r.next()) {
			return null;
		}
    sql = String.format("SELECT * FROM Patients WHERE name='%s' AND dob='%s'", name, dob);
    r = stmt.executeQuery(sql);
    r.next();
		Parent p = new Parent(Integer.parseInt(r.getString("id")), name, dob, Double.parseDouble(r.getString("height")), Double.parseDouble(r.getString("weight")), Integer.parseInt(r.getString("heart_rate")), Double.parseDouble(r.getString("blood_pressure")), r.getString("pharmacy"), r.getString("visit_summary"), r.getString("prescribed_medication"), r.getString("patient_history"));
		p.setMessageHistory(r.getString("message_history"));
    ArrayList<Child> children = getParentChildren(p);
    for (Child child : children) {
      p.addChild(child);
    }
		return p;
  }

  public static void addParentChild(Parent parent, Child child) throws SQLException {
    String sql = String.format("INSERT INTO Parents_Children VALUES ('%d', '%d')", parent.getPatientId(), child.getPatientId());
    stmt.execute(sql);
  }

  // CHILD
  public static void addChild(Child child) throws SQLException {
    String sql = String.format("INSERT INTO Patients VALUES ('%d', '%s', '%s', '%f', '%f', '%d', '%f', '%d', '%s', '%s', '%s', '%s', '%s')", child.getPatientId(), child.getName(), child.getDob(), child.getHeightCm(), child.getWeightKg(), child.getRestingHeartRate(), child.getBloodPressurekPa(), 1, child.getPharmacyName(), child.getVisitSummary(), child.getPrescribedMedication(), child.getMessageHistory(), child.getPatientHistory());
		stmt.executeUpdate(sql);
    sql = String.format("INSERT INTO Children VALUES ('%d', '%d')", child.getPatientId(), child.getPatientId());
    stmt.executeUpdate(sql);
  }
  public static ArrayList<Child> getParentChildren(Parent parent) throws SQLException {
    String sql = String.format("SELECT DISTINCT Patients.* FROM Parents JOIN Parents_Children ON Parents.id = Parents_Children.parent_id JOIN Children ON Parents_Children.child_id = Children.id JOIN Patients ON Children.patient_id = Patients.id WHERE Parents.id = '%d'", parent.getPatientId());
    ResultSet r = stmt.executeQuery(sql);

    ArrayList<Child> children = new ArrayList<Child>();
    while (r.next()) {
      Child c = new Child(Integer.parseInt(r.getString("id")),
      r.getString("name"), r.getString("dob"),
      Double.parseDouble(r.getString("height")),
      Double.parseDouble(r.getString("weight")),
      Integer.parseInt(r.getString("heart_rate")),
      Double.parseDouble(r.getString("blood_pressure")),
      true,
      r.getString("pharmacy"),
      r.getString("prescribed_medication"),
      r.getString("patient_history"),
      r.getString("visit_summary"));
  		c.setMessageHistory(r.getString("message_history"));
      children.add(c);
    }
    return children;
  }

  public static ArrayList<Parent> getChildParents(Child child) throws SQLException {
    String sql = String.format("SELECT DISTINCT Patients.* FROM Children JOIN Parents_Children ON Children.id = Parents_Children.child_id JOIN Parents ON Parents_Children.parent_id = Parents.id JOIN Patients ON Parents.patient_id = Patients.id WHERE Children.id = '%d'", child.getPatientId());
    ResultSet r = stmt.executeQuery(sql);

    ArrayList<Parent> parents = new ArrayList<Parent>();
    while (r.next()) {
      Parent p = new Parent(Integer.parseInt(r.getString("id")),
      r.getString("name"), r.getString("dob"),
      Double.parseDouble(r.getString("height")),
      Double.parseDouble(r.getString("weight")),
      Integer.parseInt(r.getString("heart_rate")),
      Double.parseDouble(r.getString("blood_pressure")),
      r.getString("pharmacy"),
      r.getString("prescribed_medication"),
      r.getString("patient_history"),
      r.getString("visit_summary"));
  		p.setMessageHistory(r.getString("message_history"));
      parents.add(p);
    }
    return parents;
  }

  // NURSE
  public static void addNurse(Nurse nurse) throws SQLException {
    String salt = getSaltString();
    String hashedPass = getSHA256(nurse.getPassword() + salt);
    String sql = String.format("INSERT INTO Nurses VALUES ( '%d', '%s', '%d', '%s', '%s', '%s', '%s' )", nurse.getNurseId(), nurse.getName(), nurse.getActivePatient().getPatientId(), nurse.getVisitSummary(), nurse.getPrescribedMedication(), hashedPass, salt);
    stmt.execute(sql);
  }

  public static Nurse getNurse(int nurseId, String password) throws SQLException {
		String sql = String.format("SELECT * FROM Nurses WHERE id='%d'", nurseId);
		ResultSet r = stmt.executeQuery(sql);
		if (!r.next()) {
			return null;
		}
		String hashedPass = getSHA256(password + r.getString("salt"));
		if (!hashedPass.equals(r.getString("password"))) {
			return null;
		}

		// getting active patient
		sql = String.format("SELECT * FROM Patients WHERE id='%s'", r.getString("active_patient"));
		r = stmt.executeQuery(sql);

    Patient p = null;
    if (r.next()) {
      p = new Patient(
  			Integer.parseInt(r.getString("id")),
  			r.getString("name"),
  			r.getString("dob"),
  			Double.parseDouble(r.getString("height")),
  			Double.parseDouble(r.getString("weight")),
  			Integer.parseInt(r.getString("heart_rate")),
  			Double.parseDouble(r.getString("blood_pressure")),
  			r.getString("child_account").equals("1") ? true : false,
  			r.getString("pharmacy"), r.getString("visit_summary"),
  			r.getString("prescribed_medication"),
  			r.getString("patient_history")
  			);
  		p.setMessageHistory(r.getString("message_history"));
    }

		// stupid crap because SQLite only supports TYPE_FORWARD_ONLY cursor
		sql = String.format("SELECT * FROM Nurses WHERE id='%d'", nurseId);
		r = stmt.executeQuery(sql);

    String pName = "";
    String pDob = "";
    double pHeight = 0;
    double pWeight = 0;
    int pHeartRate = 0;
    double pBloodPressure = 0;

    if (p != null) {
      pName = p.getName();
      pName = p.getName();
      pDob = p.getDob();
      pHeight = p.getHeightCm();
      pWeight = p.getWeightKg();
      pHeartRate = p.getRestingHeartRate();
      pBloodPressure = p.getBloodPressurekPa();
    }

		Nurse n = new Nurse(
			Integer.parseInt(r.getString("id")),
			r.getString("name"),
			password,
			p,
			r.getString("visit_summary"),
			r.getString("prescribed_medication"),
			null,
      pName,
      pDob,
      pHeight,
      pWeight,
      pHeartRate,
      pBloodPressure
			);
		return n;
	}

  public static void addNursePatient(Nurse nurse, Patient patient) throws SQLException {
    String sql = String.format("INSERT INTO Nurses_Patients VALUES ('%d', '%d')", nurse.getNurseId(), patient.getPatientId());
    stmt.execute(sql);
  }

  public static void deleteNursePatient(Nurse nurse, Patient patient) throws SQLException {
    String sql = String.format("DELETE FROM Doctors_Patients WHERE doctor_id = '%d' AND patient_id = '%d'", nurse.getNurseId(), patient.getPatientId());
    stmt.execute(sql);
  }

  public static ArrayList<Patient> getNursePatients(Nurse nurse) throws SQLException {
    String sql = String.format("SELECT Patients.* FROM Patients JOIN Nurses_Patients ON Patients.id = Nurses_Patients.patient_id WHERE Nurses_Patients.nurse_id = '%d'", nurse.getNurseId());
    ResultSet r = stmt.executeQuery(sql);

    Statement tmpStmt = conn.createStatement();

    ArrayList<Patient> patients = new ArrayList<Patient>();
    while (r.next()) {
      if (r.getString("child_account").equals("1")) {
        Child c = new Child(Integer.parseInt(r.getString("id")),
        r.getString("name"), r.getString("dob"),
        Double.parseDouble(r.getString("height")),
        Double.parseDouble(r.getString("weight")),
        Integer.parseInt(r.getString("heart_rate")),
        Double.parseDouble(r.getString("blood_pressure")),
        true,
        r.getString("pharmacy"),
        r.getString("prescribed_medication"),
        r.getString("patient_history"),
        r.getString("visit_summary"));
    		c.setMessageHistory(r.getString("message_history"));
        patients.add(c);
      } else if (tmpStmt.executeQuery(String.format("SELECT * FROM Parents WHERE id = '%s'", r.getString("id"))).next()) {
        Parent p = new Parent(Integer.parseInt(r.getString("id")),
        r.getString("name"), r.getString("dob"),
        Double.parseDouble(r.getString("height")),
        Double.parseDouble(r.getString("weight")),
        Integer.parseInt(r.getString("heart_rate")),
        Double.parseDouble(r.getString("blood_pressure")),
        r.getString("pharmacy"),
        r.getString("prescribed_medication"),
        r.getString("patient_history"),
        r.getString("visit_summary"));
        ArrayList<Child> children = getParentChildren(p);
        for (Child child : children) {
          p.addChild(child);
        }
    		p.setMessageHistory(r.getString("message_history"));
        patients.add(p);
      } else {
        Patient p = new Patient(Integer.parseInt(r.getString("id")),
        r.getString("name"), r.getString("dob"),
        Double.parseDouble(r.getString("height")),
        Double.parseDouble(r.getString("weight")),
        Integer.parseInt(r.getString("heart_rate")),
        Double.parseDouble(r.getString("blood_pressure")),
        false,
        r.getString("pharmacy"),
        r.getString("prescribed_medication"),
        r.getString("patient_history"),
        r.getString("visit_summary"));
    		p.setMessageHistory(r.getString("message_history"));
        patients.add(p);
      }
    }
    tmpStmt.close();
    return patients;
  }

  // DOCTOR
	public static void addDoctor(Doctor doctor) throws SQLException {
		String salt = getSaltString();
		String hashedPass = getSHA256(doctor.getPassword() + salt);
		String sql = String.format("INSERT INTO Doctors VALUES ( '%d', '%s', '%s', '%d', '%s', '%s', '%s', '%s' )", doctor.getDocId(), doctor.getFirstName(), doctor.getLastName(), doctor.getActivePatient().getPatientId(), doctor.getVisitSummary(), doctor.getPrescribedMedication(), hashedPass, salt);
		stmt.execute(sql);
	}

	public static Doctor getDoctor(int docId, String password) throws SQLException {
		String sql = String.format("SELECT * FROM Doctors WHERE id='%d'", docId);
		ResultSet r = stmt.executeQuery(sql);
		if (!r.next()) {
			return null;
		}
		String hashedPass = getSHA256(password + r.getString("salt"));
		if (!hashedPass.equals(r.getString("password"))) {
			return null;
		}

		// getting active patient
		sql = String.format("SELECT * FROM Patients WHERE id='%s'", r.getString("active_patient"));
		r = stmt.executeQuery(sql);
    Patient p = null;
		if (r.next()) {
      p = new Patient(
  			Integer.parseInt(r.getString("id")),
  			r.getString("name"),
  			r.getString("dob"),
  			Double.parseDouble(r.getString("height")),
  			Double.parseDouble(r.getString("weight")),
  			Integer.parseInt(r.getString("heart_rate")),
  			Double.parseDouble(r.getString("blood_pressure")),
  			r.getString("child_account").equals("1") ? true : false,
  			r.getString("pharmacy"), r.getString("visit_summary"),
  			r.getString("prescribed_medication"),
  			r.getString("patient_history")
  			);
  		p.setMessageHistory(r.getString("message_history"));
    }

		// stupid crap because SQLite only supports TYPE_FORWARD_ONLY cursor
		sql = String.format("SELECT * FROM Doctors WHERE id='%d'", docId);
		r = stmt.executeQuery(sql);

		Doctor d = new Doctor(
			Integer.parseInt(r.getString("id")),
			r.getString("first_name"),
			r.getString("last_name"),
			password,
			p,
			r.getString("visit_summary"),
			r.getString("prescribed_medication"),
			null
			);
		return d;
	}

  public static void addDoctorPatient(Doctor doctor, Patient patient) throws SQLException {
    String sql = String.format("INSERT INTO Doctors_Patients VALUES ('%d', '%d')", doctor.getDocId(), patient.getPatientId());
    stmt.execute(sql);
  }

  public static void deleteDoctorPatient(Doctor doctor, Patient patient) throws SQLException {
    String sql = String.format("DELETE FROM Doctors_Patients WHERE doctor_id = '%d' AND patient_id = '%d'", doctor.getDocId(), patient.getPatientId());
    stmt.execute(sql);
  }

  public static ArrayList<Patient> getDoctorPatients(Doctor doctor) throws SQLException {
    String sql = String.format("SELECT Patients.* FROM Patients JOIN Doctors_Patients ON Patients.id = Doctors_Patients.patient_id WHERE Doctors_Patients.doctor_id = '%d'", doctor.getDocId());
    ResultSet r = stmt.executeQuery(sql);

    Statement tmpStmt = conn.createStatement();

    ArrayList<Patient> patients = new ArrayList<Patient>();
    while (r.next()) {
      if (r.getString("child_account").equals("1")) {
        Child c = new Child(Integer.parseInt(r.getString("id")),
        r.getString("name"), r.getString("dob"),
        Double.parseDouble(r.getString("height")),
        Double.parseDouble(r.getString("weight")),
        Integer.parseInt(r.getString("heart_rate")),
        Double.parseDouble(r.getString("blood_pressure")),
        true,
        r.getString("pharmacy"),
        r.getString("prescribed_medication"),
        r.getString("patient_history"),
        r.getString("visit_summary"));
    		c.setMessageHistory(r.getString("message_history"));
        patients.add(c);
      } else if (stmt.executeQuery(String.format("SELECT * FROM Parents WHERE id = '%s'", r.getString("id"))).next()) {
        Parent p = new Parent(Integer.parseInt(r.getString("id")),
        r.getString("name"), r.getString("dob"),
        Double.parseDouble(r.getString("height")),
        Double.parseDouble(r.getString("weight")),
        Integer.parseInt(r.getString("heart_rate")),
        Double.parseDouble(r.getString("blood_pressure")),
        r.getString("pharmacy"),
        r.getString("prescribed_medication"),
        r.getString("patient_history"),
        r.getString("visit_summary"));
    		p.setMessageHistory(r.getString("message_history"));
        ArrayList<Child> children = getParentChildren(p);
        for (Child child : children) {
          p.addChild(child);
        }
        patients.add(p);
      } else {
        Patient p = new Patient(Integer.parseInt(r.getString("id")),
        r.getString("name"), r.getString("dob"),
        Double.parseDouble(r.getString("height")),
        Double.parseDouble(r.getString("weight")),
        Integer.parseInt(r.getString("heart_rate")),
        Double.parseDouble(r.getString("blood_pressure")),
        false,
        r.getString("pharmacy"),
        r.getString("prescribed_medication"),
        r.getString("patient_history"),
        r.getString("visit_summary"));
    		p.setMessageHistory(r.getString("message_history"));
        patients.add(p);
      }
    }
    tmpStmt.close();
    return patients;
  }

	public static void addPatient(Patient patient) throws SQLException {
		String sql = String.format("INSERT INTO Patients VALUES ('%d', '%s', '%s', '%f', '%f', '%d', '%f', '%d', '%s', '%s', '%s', '%s', '%s')", patient.getPatientId(), patient.getName(), patient.getDob(), patient.getHeightCm(), patient.getWeightKg(), patient.getRestingHeartRate(), patient.getBloodPressurekPa(), patient.isChildAccount() ? 1 : 0, patient.getPharmacyName(), patient.getVisitSummary(), patient.getPrescribedMedication(), patient.getMessageHistory(), patient.getPatientHistory());
		stmt.executeUpdate(sql);
	}

	public static Patient getPatient(String name, String dob) throws SQLException {
		String sql = String.format("SELECT * FROM Patients WHERE name='%s' AND dob='%s'", name, dob);
		// sql injection moment
		ResultSet r = stmt.executeQuery(sql);
		if (!r.next()) {
			return null;
		}
		Patient p = new Patient(Integer.parseInt(r.getString("id")), name, dob, Double.parseDouble(r.getString("height")), Double.parseDouble(r.getString("weight")), Integer.parseInt(r.getString("heart_rate")), Double.parseDouble(r.getString("blood_pressure")), r.getString("child_account").equals("1") ? true : false, r.getString("pharmacy"), r.getString("visit_summary"), r.getString("prescribed_medication"), r.getString("patient_history"));
		p.setMessageHistory(r.getString("message_history"));
		return p;
	}

	public static void closeConnection() throws SQLException {
		conn.close();
	}
}
