package com.example.chenjiayou.myapplication;

import android.arch.lifecycle.ViewModel;

/**
 * Created by chenjiayou on 2018/7/18.
 */

public class NameViewModel extends ViewModel {

    private NameLiveData currentName;

    public NameLiveData getCurrentName() {

        if (currentName == null) {
            currentName = new NameLiveData();
        }
        return currentName;
    }
}
