package com.djzass.medipoint;

import java.util.ArrayList;

/**
 * Created by Deka on 26/3/2015.
 */
public class Doctor {
    private int DID;
    private String doctorId;
    private String name;
    private int specializationId;
    private int practiceDuration;
    //per clinic
    private ArrayList<DoctorSchedule> doctorSchedules;
    private ArrayList<Appointment> appointments;

    public Doctor(){
    }

    public Doctor(String name, Specialty specialization,
                  int practiceDuration) {
        this.name = name;
        this.specializationId = specialization.getId();
    }

    public Doctor(String name, int specializationId, int practiceDuration) {
        this.name = name;
        this.specializationId = specializationId;
        this.practiceDuration = practiceDuration;
        this.appointments = new ArrayList<Appointment>();
        this.doctorSchedules = new ArrayList<DoctorSchedule>();
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId() {
        this.doctorId = "D" + String.format("%07d", this.DID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpecializationId() {
        return this.specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public int getPracticeDuration() {
        return practiceDuration;
    }

    public void setPracticeDuration(int practiceDuration) {
        this.practiceDuration = practiceDuration;
    }

    public ArrayList<DoctorSchedule> getDoctorSchedule() {
        return doctorSchedules;
    }

    public boolean addDoctorSchedule(DoctorSchedule doctorSchedule) {
        for (DoctorSchedule d: doctorSchedules){
            if(d.getDay().equalsIgnoreCase(doctorSchedule.getDay())){
                System.out.println("Day clashes");
                return false;
            }
        }
        return doctorSchedules.add(doctorSchedule);
    }

    public void printDoctorSchedule(){
        if (doctorSchedules.isEmpty())
            System.out.println("No practice schedule");
        else{
            for (DoctorSchedule ds: doctorSchedules){
                System.out.println(ds);
            }
        }
    }

    public int getDID() {
        return this.DID;
    }

    public void setId(int DID) {
        this.DID = DID;
    }

    public String toString(){
        return 	"=== Printing Doctor Info ==="+"\n"+
                "ID: " + this.DID + "\n" +
                "Name: " + this.name + "\n" +
                "Practice Duration: " + this.practiceDuration+ "\n" +
                "Specialization: " + this.specializationId + "\n"
                ;
    }
}
