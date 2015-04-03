package com.djzass.medipoint.entity;

import java.lang.String;
import java.util.Calendar;
import java.util.Calendar;

public class Account {
    private int id;
	private String username;
	private String password;
	private String name;
	private String nric;
	private String email;
    private String phoneNumber;
	private String gender;
	private String address;
	private String maritalStatus;
	private Calendar dob;
	private String citizenship;
	private String countryOfResidence;

    public Account(){

    }

    public Account(String username, String password, String name, String nric,
                   String email, String phoneNumber, String gender, String address,
                   String maritalStatus, Calendar dob, String citizenship,
                   String countryOfResidence) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nric = nric;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.dob = dob;
        this.citizenship = citizenship;
        this.countryOfResidence = countryOfResidence;
    }

    public Account(int id, String username, String password, String name, String nric,
                   String email, String phoneNumber, String gender, String address,
                   String maritalStatus, Calendar dob, String citizenship,
                   String countryOfResidence) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.nric = nric;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.dob = dob;
        this.citizenship = citizenship;
        this.countryOfResidence = countryOfResidence;
    }

    public Account(String name, String nric, String email, String phoneNumber, String address) {

        this.name = name;
        this.nric = nric;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    public String print(){
        String temp = "";
        temp+= id + " ";
        temp+= username + " ";
        temp+= password + " ";
        temp+= name + " ";
        temp+= nric + " ";
        temp+= email + " ";
        temp+= phoneNumber + " ";
        temp+= gender + " ";
        temp+= address + " ";
        temp+= maritalStatus + " ";
        temp+= String.valueOf(dob) + " ";
        temp+= citizenship + " ";
        temp+= countryOfResidence + " ";
        return temp;
    }

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}