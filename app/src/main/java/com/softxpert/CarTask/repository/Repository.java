package com.softxpert.CarTask.repository;

import com.softxpert.CarTask.model.CarsResponse;
import com.softxpert.CarTask.network.CarApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private CarApiService carApiService;

    @Inject
    public Repository(CarApiService carApiService) {
        this.carApiService = carApiService;
    }

    public Observable<CarsResponse> getCars(int page){
        return carApiService.getCars(page);
    }


}
