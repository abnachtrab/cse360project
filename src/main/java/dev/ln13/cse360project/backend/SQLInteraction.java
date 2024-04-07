package dev.ln13.cse360project.backend;

import java.sql.*;
import java.util.ArrayList;

public class SQLInteraction {
	static Connection conn;
	static Statement stmt;

	public static void main(String[] args) throws SQLException {
		conn = setupConnection();
		// test scenario
		Patient p = new Patient("Joe Mama", "1984", 193.2, 78.2, 90, 14.2, false, "CVS", "None", "None", "Eat more apples");
		addPatient(p);
		getPatient("Joe Mama","1984");
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
			stmt.execute("CREATE TABLE Patients (id INTEGER PRIMARY KEY, name TEXT NOT NULL, dob TEXT NOT NULL, height REAL, weight REAL, heart_rate INTEGER, blood_pressure REAL, child_account INTEGER, pharmacy TEXT, visit_summary TEXT, prescribed_medication TEXT, message_history TEXT, patient_history TEXT)");
		}
		return conn;
	}

	public static void addPatient(Patient patient) throws SQLException {
		String sql = String.format("INSERT INTO Patients VALUES ('%d', '%s', '%s', '%f', '%f', '%d', '%f', '%d', '%s', '%s', '%s', '%s', '%s')", patient.getPatientId(), patient.getName(), patient.getDob(), patient.getHeightCm(), patient.getWeightKg(), patient.getRestingHeartRate(), patient.getBloodPressurekPa(), patient.isChildAccount() ? 1 : 0, patient.getPharmacyName(), patient.getVisitSummary(), patient.getPerscribedMedication(), patient.getMessageHistory(), patient.getPatientHistory());
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public static Patient getPatient(String name, String dob) throws SQLException {
		String sql = String.format("SELECT * FROM Patients WHERE name='%s' AND dob='%s'", name, dob);                                                                                                                                                                                 // sql injection moment
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
