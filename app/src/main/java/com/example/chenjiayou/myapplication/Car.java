package com.example.chenjiayou.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by chenjiayou on 2018/7/19.
 */

@Entity (tableName = "car_table")
public class Car {



    @PrimaryKey (autoGenerate = true)
    @ColumnInfo
    int id;

    @NonNull
    @ColumnInfo (name = "brand")
    String brand;

    @NonNull
    @ColumnInfo (name = "type")
    String type;

    public Car(@NonNull String brand, @NonNull String type) {

        this.brand = brand;
        this.type = type;
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

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }
}
