package com.epicodus.shoppinglist.models;

import org.parceler.Parcel;

@Parcel
public class Store {
    private String name;
    private String country;
    private String streetAddress;
    private String city;
    private String stateProvCode;
    private String zip;
    private String phoneNumber;
    private double latitude;
    private double longitude;
    private String address;

    public Store() {}

    public Store(String name, String country, String streetAddress, String city, String stateProvCode, String zip, String phoneNumber, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.streetAddress = streetAddress;
        this.city = city;
        this.stateProvCode = stateProvCode;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = streetAddress + ", " + city + ", " + stateProvCode + " " + zip;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvCode() {
        return stateProvCode;
    }

    public String getZip() {
        return zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() { return address; }
}
