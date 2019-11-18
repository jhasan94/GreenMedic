package com.capstone.greenmedic.models;

public class Pharmacy {
    private boolean isPharmSignup;
    private String fullName;
    private String email;
    private String pharmacyName;
    private String pharmacyAddress;
    private String username;
    private String password;
    private String licenenceNo;
    private String geoLocation;

    public Pharmacy() {
    }

    public Pharmacy(boolean isPharmSignup,
                          String fullName,
                          String email,
                          String pharmacyName,
                          String pharmacyAddress,
                          String username,
                          String password,
                          String licenenceNo,
                          String geoLocation)
    {
        this.isPharmSignup = isPharmSignup;
        this.fullName = fullName;
        this.email = email;
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
        this.username = username;
        this.password = password;
        this.licenenceNo = licenenceNo;
        this.geoLocation = geoLocation;
    }

    public boolean isPharmSignup() {
        return isPharmSignup;
    }

    public void setPharmSignup(boolean pharmSignup) {
        isPharmSignup = pharmSignup;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
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

    public String getLicenenceNo() {
        return licenenceNo;
    }

    public void setLicenenceNo(String licenenceNo) {
        this.licenenceNo = licenenceNo;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }
}


