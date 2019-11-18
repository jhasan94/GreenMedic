package com.capstone.greenmedic.models;

public class Order {

    private String medicineName;
    private String price;
    private String strength;
    private String company;
    private String quantity;
    private String Element;
    private String DosageForm;

    public Order() {
    }

    public Order(String medicineName, String price, String strength, String company, String quantity, String element, String dosageForm) {
        this.medicineName = medicineName;
        this.price = price;
        this.strength = strength;
        this.company = company;
        this.quantity = quantity;
        Element = element;
        DosageForm = dosageForm;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getElement() {
        return Element;
    }

    public void setElement(String element) {
        Element = element;
    }

    public String getDosageForm() {
        return DosageForm;
    }

    public void setDosageForm(String dosageForm) {
        DosageForm = dosageForm;
    }
}


