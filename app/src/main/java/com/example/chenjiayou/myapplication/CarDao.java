package com.example.chenjiayou.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by chenjiayou on 2018/7/19.
 */

@Dao
public interface CarDao {

    @Insert
    void insert(Car car);

    @Delete
    void delete(Car car);

    @Query ("SELECT * from car_table ORDER BY id ASC")
    LiveData<List<Car>> getCars();
}
