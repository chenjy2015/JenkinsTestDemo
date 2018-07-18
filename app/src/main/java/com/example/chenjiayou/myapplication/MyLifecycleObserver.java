package com.example.chenjiayou.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by chenjiayou on 2018/7/18.
 * 实现对生命周期的回调监听
 */

public class MyLifecycleObserver<T> implements LifecycleObserver {

    private Context mContext;
    private T t;

    public MyLifecycleObserver(Context context) {

        mContext = context;
    }

    @OnLifecycleEvent (Lifecycle.Event.ON_RESUME)
    public void connectListener() {

        Toast.makeText(mContext, "链接开始...", Toast.LENGTH_SHORT).show();
    }

    @OnLifecycleEvent (Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {

        Toast.makeText(mContext, "链接断开...", Toast.LENGTH_SHORT).show();
    }
}
