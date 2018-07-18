package com.example.chenjiayou.myapplication;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.chenjiayou.myapplication.databinding.ActivityHomeBinding;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding mHomeBinding;
    NameViewModel       mNameViewModel;

    public static void startActivity(Activity context) {

        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        getLifecycle().addObserver(new MyLifecycleObserver(this));

        mNameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
        mNameViewModel.getCurrentName().observe(this, new Observer<String>() {

            @Override
            public void onChanged(@Nullable String s) {

                mHomeBinding.carName.setText(s);
            }
        });

        RxTextView
                .textChanges(mHomeBinding.editCarName)
                .subscribe(charSequence -> {
                    mNameViewModel.getCurrentName().setValue(charSequence.toString());
                });

        RxView.clicks(mHomeBinding.change)
              .compose(new DebounceObservableTransformer<>())
              .subscribe(object -> {

              });

    }
}
