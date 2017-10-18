package com.epicodus.shoppinglist.models;

public class Store {
    private String name;
    private String country;
    private String streetAddress;
    private String city;
    private String stateProvCode;
    private String zip;
    private String phoneNumber;

    public Store(String name, String country, String streetAddress, String city, String stateProvCode, String zip, String phoneNumber) {
        this.name = name;
        this.country = country;
        this.streetAddress = streetAddress;
        this.city = city;
        this.stateProvCode = stateProvCode;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
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
}
