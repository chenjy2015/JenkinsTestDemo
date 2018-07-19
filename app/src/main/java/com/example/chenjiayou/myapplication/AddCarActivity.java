package com.example.chenjiayou.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.chenjiayou.myapplication.databinding.ActivityAddCarBinding;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.functions.Consumer;

public class AddCarActivity extends AppCompatActivity {

    ActivityAddCarBinding              mBinding;
    LifecycleProvider<Lifecycle.Event> mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_car);
        init();
        initEvent();
    }

    public void init() {

        mProvider = AndroidLifecycle.createLifecycleProvider(this);
    }

    public void initEvent() {

        RxView.clicks(mBinding.add)
              .compose(mProvider.bindToLifecycle())
              .compose(new DebounceObservableTransformer<>())
              .subscribe(new Consumer<Object>() {

                  @Override
                  public void accept(Object object) throws Exception {

                  }
              });
    }
}
