package com.example.chenjiayou.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.chenjiayou.myapplication.databinding.ActivityMainBinding;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding                mBinding;
    LifecycleProvider<Lifecycle.Event> provider;
    CarAdapter                         mCarAdapter;
    CarViewModel                       mCarViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        provider = AndroidLifecycle.createLifecycleProvider(this);
        mCarViewModel = ViewModelProviders.of(this).get(CarViewModel.class);

        init();
        initEvent();
    }

    public void init() {

        mCarAdapter = new CarAdapter(this);
        mBinding.recylerView.setAdapter(mCarAdapter);
        mBinding.recylerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initEvent() {

        mCarAdapter.setCallBack(new CarAdapter.CallBack() {

            @Override
            public void onClick(int position) {

                mCarViewModel.clear();
            }
        });

        mCarViewModel.getLiveData().observe(this, new Observer<List<Car>>() {

            @Override
            public void onChanged(
                    @Nullable List<Car> cars) {

                mCarAdapter.setCars(cars);
            }
        });

        Observable
                .combineLatest(RxTextView.textChanges(mBinding.carBrand), RxTextView.textChanges
                        (mBinding.carType), new BiFunction<CharSequence, CharSequence, Boolean>() {

                    @Override
                    public Boolean apply(CharSequence charSequence, CharSequence charSequence2)
                    throws Exception {

                        return !TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty
                                (charSequence2);
                    }
                }).subscribe(new Consumer<Boolean>() {

            @Override
            public void accept(Boolean aBoolean) throws Exception {

                mBinding.save.setEnabled(aBoolean);
            }
        });

        RxView.clicks(mBinding.save)
              .compose(new DebounceObservableTransformer<>())
              .compose(provider.bindToLifecycle())
              .subscribe(new Consumer<Object>() {

                  @Override
                  public void accept(Object object) throws Exception {

                      Single.just("")
                            .map(new Function<Object, Object>() {

                                @Override
                                public Object apply(Object object) throws Exception {

                                    mCarViewModel.insert(
                                            new Car(mBinding.carBrand.getText().toString()
                                                    , mBinding.carType.getText().toString()));
                                    return object;
                                }
                            })
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Object>() {

                                @Override
                                public void accept(Object object) throws Exception {

                                    Toast.makeText(getApplicationContext(), "更新成功!", Toast
                                            .LENGTH_SHORT).show();
                                }
                            });
                  }
              });
    }
}
