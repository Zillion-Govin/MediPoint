package com.djzass.medipoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Deka on 25/3/2015.
 */

public class Patient extends Account{
    private int PId;
    private String patientId;
    private int age;
    private ArrayList<Appointment> listOfAppointments;
    private String allergy;
    private String medicalHistory;
    private String listOfTreatments;
    private String listOfMedications;

    public Patient(){
        super();
    };

    public Patient(String username, String password, String name, String nric, String email, String phoneNumber, String gender,
        String address, String maritalStatus, Date dob, String citizenship, String countryOfResidence,String listOfTreatments, String listOfMedications, String allergy) {
        super(username, password, name, nric, email, phoneNumber, gender, address, maritalStatus, dob, citizenship, countryOfResidence);
        this.age = getAge();
        this.listOfTreatments = listOfTreatments;
        this.listOfMedications = listOfMedications;
        this.allergy = allergy;
        this.listOfAppointments = new ArrayList<Appointment>();
    }

    public Patient(String name, String nric, String email, String phoneNumber, String address, String listOfTreatments, String listOfMedications, String allergy) {
        super(name, nric, email, phoneNumber, address);
        this.age = getAge();
        this.listOfTreatments = listOfTreatments;
        this.listOfMedications = listOfMedications;
        this.allergy = allergy;
        this.listOfAppointments = new ArrayList<Appointment>();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId() {
        this.patientId = "P" + String.format("%07d", this.PId);
    }

    public int getAge() {
        Calendar dob = Calendar.getInstance();
        dob.setTime(this.getDob());
        Calendar now = Calendar.getInstance();
        int age =  now.YEAR - dob.YEAR;

        if ( (dob.MONTH > now.MONTH) || (dob.MONTH == now.MONTH) && (dob.DATE > now.DATE) ){
            age--;
        }

        return age;
    }

    public int getPId() {
        return this.PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Appointment> getListOfAppointments() {
        return listOfAppointments;
    }

    public String getListOfTreatments() {
        return listOfTreatments;
    }

    public void setListOfTreatments(String listOfTreatments) {
        this.listOfTreatments = listOfTreatments;
    }

    public String getListOfMedications() {
        return listOfMedications;
    }

    public void setListOfMedications(String listOfMedications) {
        this.listOfMedications = listOfMedications;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}