package com.softxpert.CarTask.network;

import com.softxpert.CarTask.model.CarsResponse;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarApiService {

    @GET("cars?")
    Observable<CarsResponse> getCars(@Query("page") int page);
}
