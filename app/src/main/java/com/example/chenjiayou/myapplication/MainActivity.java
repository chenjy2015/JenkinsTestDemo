package com.example.chenjiayou.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.chenjiayou.myapplication.databinding.ActivityMainBinding;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding                mBinding;
    LifecycleProvider<Lifecycle.Event> provider =
            AndroidLifecycle.createLifecycleProvider(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
        initEvent();
    }

    public void init() {

        String infor = "version:" + new BuildInfor().getVersionName(this) +
                       "\n code: " + new BuildInfor().getVersionCode(this);
        mBinding.infor.setText(infor);
    }

    public void initEvent(){

        RxView.clicks(mBinding.launcher)
              .compose(new DebounceObservableTransformer<>())
              .compose(provider.bindToLifecycle())
              .subscribe(new Consumer<Object>() {

                  @Override
                  public void accept(Object object) throws Exception {
                        HomeActivity.startActivity(MainActivity.this);
                  }
              });
    }
}
