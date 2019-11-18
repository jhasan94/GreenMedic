package com.capstone.greenmedic.models;

import java.util.List;

public class OrderRequest {
    private String orderID;
    private String userPhone;
    private String userName;
    private String userCurrentGeoLocation;
    private String userAddress;
    private String orderTotalCost;
    private String orderStatus;
    private List<Order> medicines;
    private String acceptedPharmacyPhoneNo;
    private String acceptedPharmacyAddress;
    private String acceptedPharmacyGeoLocation;

    public OrderRequest() {
    }


    public OrderRequest(String orderID,String userName,String userPhone, String userAddress, String orderTotalCost,
                        List<Order> medicines) {
        this.orderID=orderID;
        this.userName=userName;
        this.userPhone = userPhone;
        this.userCurrentGeoLocation = "23.764881,90.364644";
        this.userAddress = userAddress;
        this.orderTotalCost = orderTotalCost;
        this.orderStatus = "0"; //default 0. 0=try to find pharmacy, 99=order accepted 1= completed
        this.medicines = medicines;
        this.acceptedPharmacyPhoneNo = "na";
        this.acceptedPharmacyAddress = "na";
        this.acceptedPharmacyGeoLocation = "0,0";
    }


    public OrderRequest(String orderID, String userPhone, String userName, String userCurrentGeoLocation, String userAddress, String orderTotalCost, String orderStatus, List<Order> medicines, String acceptedPharmacyPhoneNo, String acceptedPharmacyAddress, String acceptedPharmacyGeoLocation) {
        this.orderID = orderID;
        this.userPhone = userPhone;
        this.userName = userName;
        this.userCurrentGeoLocation = userCurrentGeoLocation;
        this.userAddress = userAddress;
        this.orderTotalCost = orderTotalCost;
        this.orderStatus = orderStatus;
        this.medicines = medicines;
        this.acceptedPharmacyPhoneNo = acceptedPharmacyPhoneNo;
        this.acceptedPharmacyAddress = acceptedPharmacyAddress;
        this.acceptedPharmacyGeoLocation = acceptedPharmacyGeoLocation;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCurrentGeoLocation() {
        return userCurrentGeoLocation;
    }

    public void setUserCurrentGeoLocation(String userCurrentGeoLocation) {
        this.userCurrentGeoLocation = userCurrentGeoLocation;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getOrderTotalCost() {
        return orderTotalCost;
    }

    public void setOrderTotalCost(String orderTotalCost) {
        this.orderTotalCost = orderTotalCost;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Order> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Order> medicines) {
        this.medicines = medicines;
    }

    public String getAcceptedPharmacyPhoneNo() {
        return acceptedPharmacyPhoneNo;
    }

    public void setAcceptedPharmacyPhoneNo(String acceptedPharmacyPhoneNo) {
        this.acceptedPharmacyPhoneNo = acceptedPharmacyPhoneNo;
    }

    public String getAcceptedPharmacyAddress() {
        return acceptedPharmacyAddress;
    }

    public void setAcceptedPharmacyAddress(String acceptedPharmacyAddress) {
        this.acceptedPharmacyAddress = acceptedPharmacyAddress;
    }

    public String getAcceptedPharmacyGeoLocation() {
        return acceptedPharmacyGeoLocation;
    }

    public void setAcceptedPharmacyGeoLocation(String acceptedPharmacyGeoLocation) {
        this.acceptedPharmacyGeoLocation = acceptedPharmacyGeoLocation;
    }
}
