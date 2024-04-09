package dev.ln13.cse360project.backend;

import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Random;
import java.util.ArrayList;

public class SQLInteraction {
	static Connection conn;
	static Statement stmt;

	public static void main(String[] args) throws SQLException {
		conn = setupConnection();
    
		// test scenario
		Patient p = new Patient("Joe Mama","1984",178.2, 73.1, 90, 42.1, false, "CVS", "None", "None", "Eat less");
		Doctor d = new Doctor(1, "Gregory", "House", "gamerjoe", p,  "None", "None", "None");
		addPatient(p);
		addDoctor(d);
    relateDoctorPatient(d,p);

		System.out.println(getDoctorPatients(d).size());
	}

	private static Connection setupConnection() throws SQLException {
		// Check if database exists
		conn = DriverManager.getConnection("jdbc:sqlite:medical.db");
		stmt = conn.createStatement();

		ResultSet r = conn.getMetaData().getTables(null, null, null, null);
		ArrayList<String> tables = new ArrayList<String>();
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
		for (int i = 0; i < payloads.length; i++) {
			input = input.replaceAll(payloads[i], "");
		}
		return input;
	}

	public static String getSHA256(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes());
			return new String(bytesToHex(md.digest()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xff & bytes[i]);
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
		String saltStr = salt.toString();
		return saltStr;
	}

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
		r.next();
		Patient p = new Patient(
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

    ArrayList<Patient> patients = new ArrayList<Patient>();
    while (r.next()) {
      Patient p = new Patient(Integer.parseInt(r.getString("id")), r.getString("name"), r.getString("dob"), Double.parseDouble(r.getString("height")), Double.parseDouble(r.getString("weight")), Integer.parseInt(r.getString("heart_rate")), Double.parseDouble(r.getString("blood_pressure")), r.getString("child_account").equals("1") ? true : false, r.getString("pharmacy"), r.getString("visit_summary"), r.getString("prescribed_medication"), r.getString("patient_history"));
  		p.setMessageHistory(r.getString("message_history"));
      patients.add(p);
    }
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
