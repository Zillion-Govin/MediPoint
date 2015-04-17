package com.djzass.medipoint.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Deka on 25/3/2015.
 */
public class Clinic implements Parcelable {
    private int id;
    private String name;
    private String address;
    private int zipCode;
    private int telNumber;
    private int faxNumber;
    private String email;
    private String country;

    /**
     * Return an object Clinic
     * empty Clinic constructor
     */
    public Clinic(){

    }

    /**
     * create an object Clinic
     * @param id clinic id
     * @param name clinic name
     * @param address clinic address
     * @param zipCode clinic zipcode
     * @param telNumber clinic tel number
     * @param faxNumber clinic fax
     * @param email clinic email
     * @param country clinic location
     */
    public Clinic(int id, String name, String address, int zipCode, int telNumber, int faxNumber, String email, String country){
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.faxNumber= faxNumber;
        this.telNumber = telNumber;
        this.email = email;
        this.country = country;
    }

    /**
     * create an object Clinic
     * @param name clinic name
     * @param address clinic address
     * @param country clinic location
     * @param zipCode clinic zipcode
     * @param telNumber clinic tel number
     * @param faxNumber clinic fax
     * @param email clinic email
     */
    public Clinic(String name, String address, String country, int zipCode, int telNumber, int faxNumber, String email){
        this.name = name;
        this.address = address;
        this.country = country;
        this.zipCode = zipCode;
        this.faxNumber= faxNumber;
        this.telNumber = telNumber;
        this.email = email;
    }

    /**
     * Returns a String object which consists of the mailing address of the clinic
     * @return the particulars of the clinic
     */
    public String print(){
        String temp = "";
        temp+= id + " ";
        temp+= name + " ";
        temp+= address + " ";
        temp+= zipCode + " ";
        temp+= zipCode + " ";
        temp+= faxNumber + " ";
        temp+= email + " ";
        temp+= country;
        return temp;
    }

    /**
     * a method to return an int which is the Id of the clinic
     * @return id of the clinic
     */
    public int getId() {
        return id;
    }

    /**
     * change the id of a clinic
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return a String object containing the name of the clinic
     * @return name of the clinic
     */
    public String getName() {
        return name;
    }

    /**
     * set clinic name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * return String object
     * @return clinic address
     */
    public String getAddress() {
        return address;
    }

    /**
     * set clinic address
     * @param address new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get zipcode of clinic
     * @return clinic zipcode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * cset clinic zipcode
     * @param zipCode new zipcode
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * return int
     * @return telephone number
     */
    public int getTelNumber() {
        return telNumber;
    }

    /**
     * set telephone number
     * @param telNumber new telnumber
     */
    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }

    /**
     * get int faxNumber
     * @return faxNumber
     */
    public int getFaxNumber() {
        return faxNumber;
    }

    /**
     * set faxNumber
     * @param faxNumber new faxNumber
     */
    public void setFaxNumber(int faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * get String object
     * @return clinic email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set clinic email
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get String of clinic location
     * @return clinic location
     */
    public String getCountry() {
        return country;
    }

    /**
     * set clinic location
     * @param country new country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Convert all the info to string
     * @return all clinic info
     */
    public String toString(){
        String tabSpace = "      ";
        if (faxNumber == 0)
            return this.name + "\n" +
                    tabSpace + "Address: " + this.address + ", " + this.country + ". " + this.zipCode + ".\n"+
                    tabSpace + "Tel: " + this.telNumber + " Fax: - \n"+
                    tabSpace + "Email: " + this.email + "\n";
        else
            return this.name + "\n" +
                    tabSpace + "Address: " + this.address + ", " + this.country + ". " + this.zipCode + ".\n"+
                    tabSpace + "Tel: " + this.telNumber + " Fax: " + this.faxNumber + "\n"+
                    tabSpace + "Email: " + this.email + "\n";
    }

    /**
     * construct clinic using parcelable
     * @param in specified parcel
     */
    public Clinic(Parcel in){
        readFromParcel(in);
    }

    /**
     * override method in Parcelable
     * @return a bitmask indicating the set of special object types marshalled by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * put clinic info to parcel
     * @param desc The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel desc, int flags) {
        desc.writeInt(this.id);
        desc.writeString(this.name);
        desc.writeInt(this.zipCode);
        desc.writeString(this.email);
        desc.writeInt(this.telNumber);
        desc.writeInt(this.faxNumber);
        desc.writeString(this.address);
        desc.writeString(this.country);
    }

    /**
     * Parcelable for creating Clinic object
     */
    public static final Parcelable.Creator<Clinic> CREATOR
            = new Parcelable.Creator<Clinic>() {
        public Clinic createFromParcel(Parcel in) {
            return new Clinic(in);
        }

        public Clinic[] newArray(int size) {
            return new Clinic[size];
        }
    };

    /**
     * get the info from a Parcel
     * @param in Parcel to be read
     */
    public void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.zipCode = in.readInt();
        this.telNumber = in.readInt();
        this.faxNumber = in.readInt();
        this.address = in.readString();
        this.country = in.readString();
    }
}
