package com.softxpert.CarTask.model;

public class Car {

    private int id;
    private String brand;
    private String constractionYear;
    private Boolean isUsed;
    private String imageUrl;


    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", constractionYear='" + constractionYear + '\'' +
                ", isUsed=" + isUsed +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Car(int id, String brand, String constractionYear, Boolean isUsed, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.constractionYear = constractionYear;
        this.isUsed = isUsed;
        this.imageUrl = imageUrl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConstractionYear() {
        return constractionYear;
    }

    public void setConstractionYear(String constractionYear) {
        this.constractionYear = constractionYear;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
