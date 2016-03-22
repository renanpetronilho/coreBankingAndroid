package com.sensedia.corebanking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by renan on 19/03/16.
 */
public class Address implements Serializable {

    private String state;
    @SerializedName("street_name")
    private String streetName;
    @SerializedName("street_number")
    private String streetNumber;
    private String city;
    private String zip;

    public Address(String state, String streetName, String streetNumber, String city, String zip) {
        this.state = state;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zip = zip;
    }

    public Address(){}

    public String getState() {
        return state;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }


    public void setState(String state) {
        this.state = state;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
