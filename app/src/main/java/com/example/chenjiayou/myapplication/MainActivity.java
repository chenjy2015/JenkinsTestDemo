package com.example.chenjiayou.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mInforTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInforTv = findViewById(R.id.infor);
        mInforTv.setText("version:" + new BuildInfor().getVersionName(this) + "\r\n code: " + new
                BuildInfor().getVersionCode(this));
    }
}
