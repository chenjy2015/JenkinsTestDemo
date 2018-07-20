package com.example.chenjiayou.myapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by chenjiayou on 2018/7/19.
 */

public class CarViewModel extends AndroidViewModel {

    private CarRepository mCarRepository;

    private LiveData<List<Car>> mLiveData;

    public CarViewModel(@NonNull Application application) {

        super(application);
        mCarRepository = new CarRepository(application);
        mLiveData = mCarRepository.getLiveData();
    }

    public LiveData<List<Car>> getLiveData() {

        return mLiveData;
    }

    public void insert(Car car) {

        mCarRepository.insert(car);
    }

    public void delete(Car car) {

        mCarRepository.delete(car);
    }
}
