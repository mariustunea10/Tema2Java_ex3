package com.model;


import java.io.Serializable;

public class Car implements Serializable {
    private int id;
    private String licensePlate;
    private int year;
    private String color;
    private int kilometers;
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    @Override
    public String toString()
    {
        return "Car id: " + id+", License Plate: "+ licensePlate + ", Brand: " + brand + ", Production: "+ year +", Color: "+ color +", Kilometers: "+ kilometers;
    }

}
