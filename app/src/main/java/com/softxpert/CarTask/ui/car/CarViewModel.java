package com.softxpert.CarTask.ui.car;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softxpert.CarTask.model.Car;
import com.softxpert.CarTask.model.CarsResponse;
import com.softxpert.CarTask.repository.Repository;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CarViewModel extends ViewModel {

    private static final String TAG = "CarViewModel";
    private Repository repository;
    private MutableLiveData<ArrayList<Car>> carList = new MutableLiveData<>();
    //private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    @ViewModelInject
    public CarViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Car>> getCarList() {
        return carList;
    }


    @SuppressLint("CheckResult")
    public void getCars(int page) {
        //isLoading.postValue(true);

        repository.getCars(page)
                .subscribeOn(Schedulers.io())
                .map(new Function<CarsResponse, ArrayList<Car>>() {
                    @Override
                    public ArrayList<Car> apply(CarsResponse carsResponse) throws Throwable {

                        if(page == 1) {
                            ArrayList<Car> list = carsResponse.getData();

                            Log.e(TAG, "apply: CarsResponse --> " + carsResponse.toString());

                            //isLoading.setValue(false);
                            //carList.setValue(list);

                            return list;
                        }
                        else{
                            ArrayList<Car> list = carsResponse.getData();

                            Log.e(TAG, "apply: 11111 CarsResponse --> " + carsResponse.toString());

                            //isLoading.setValue(false);
                            //carList.setValue(list);
                            list.addAll(carList.getValue());
                            return list;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> carList.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
        //isLoading.postValue(false);
    }





}
