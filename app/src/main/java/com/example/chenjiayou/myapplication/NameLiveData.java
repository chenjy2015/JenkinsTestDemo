package com.example.chenjiayou.myapplication;

import android.arch.lifecycle.LiveData;
import android.util.Log;

/**
 * Created by chenjiayou on 2018/7/18.
 */

public class NameLiveData extends LiveData {

    @Override
    protected void onActive() {

        super.onActive();
        Log.d(NameLiveData.class.getName(), "onActive");
    }

    @Override
    protected void onInactive() {

        super.onInactive();
        Log.d(NameLiveData.class.getName(), "onInactive");
    }

    @Override
    protected void setValue(Object value) {

        super.setValue(value);
    }

    @Override
    protected void postValue(Object value) {

        super.postValue(value);
    }
}
