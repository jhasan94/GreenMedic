package com.capstone.greenmedic.models;


public class GpsCoordinate {
    private Double  longitude;
    private Double  latitude;

    public GpsCoordinate() {
    }

    public  GpsCoordinate(String latlonValue){
        String[] value = latlonValue.split(",");

        this.latitude = Double.parseDouble(value[0]);
        this.longitude = Double.parseDouble(value[1]);

    }

    public GpsCoordinate(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
