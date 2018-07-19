package com.example.chenjiayou.myapplication;

/**
 * Created by chenjiayou on 2018/7/19.
 */

public enum CarType {

    CAR_TYPE_SUV("运动型-城市"),
    CAR_TYPE_CROSS_SUV("运动型-越野"),
    CAR_TYPE_COMPACT("紧凑型-轿车"),
    CAR_TYPE_MEDIUM("中型-轿车");

    private String type;

    private CarType(String type) {

        this.type = type;
    }
}
