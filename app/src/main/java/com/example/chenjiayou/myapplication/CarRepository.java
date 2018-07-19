package com.example.chenjiayou.myapplication;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenjiayou on 2018/7/19.
 */

public class CarRepository {

    private CarDao mCarDao;

    private LiveData<List<Car>> mLiveData;

    CarRepository(Application application) {

        CarRoomDataBase db = CarRoomDataBase.getDatabase(application);
        mCarDao = db.GetCarDao();
        mLiveData = mCarDao.getCars();
    }

    LiveData<List<Car>> getLiveData() {

        return mCarDao.getCars();
    }

    public void insert(Car car) {

        Observable.just(car)
                  .compose(new DebounceObservableTransformer<>())
                  .observeOn(Schedulers.io())
                  .map(new Function<Car, Boolean>() {

                      @Override
                      public Boolean apply(Car car) throws Exception {

                          mCarDao.insert(car);
                          return true;
                      }
                  })
                  .subscribeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<Boolean>() {

                      @Override
                      public void accept(Boolean aBoolean) throws Exception {

                      }
                  });
    }

    public void delete() {

        Observable.just("")
                  .compose(new DebounceObservableTransformer<>())
                  .observeOn(Schedulers.io())
                  .map(new Function<Object, Object>() {

                      @Override
                      public Object apply(Object object) throws Exception {

                          mCarDao.delete();
                          return object;
                      }
                  })
                  .subscribe(new Consumer<Object>() {

                      @Override
                      public void accept(Object object) throws Exception {

                      }
                  });

    }
}
