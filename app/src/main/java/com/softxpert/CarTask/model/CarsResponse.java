package com.softxpert.CarTask.model;

import java.util.ArrayList;

public class CarsResponse {
    private int status;
    private ArrayList<Car> data;

    public CarsResponse(int status, ArrayList<Car> data) {
        this.status = status;
        this.data = data;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Car> getData() {
        return data;
    }

    public void setData(ArrayList<Car> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "CarsResponse{" +
                "status=" + status +
                ", data=" + data.toString() +
                '}';
    }
}
