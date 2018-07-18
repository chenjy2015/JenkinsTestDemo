package com.example.chenjiayou.myapplication;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created by chenjiayou on 2018/7/17.
 */

public class DebounceObservableTransformer<T> implements ObservableTransformer<T, T> {

    private static final long DEBOUNCE = 500;

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {

        return upstream.throttleFirst(DEBOUNCE, TimeUnit.MILLISECONDS);
    }
}
