package com.example.chenjiayou.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;

/**
 * Created by chenjiayou on 2018/7/12.
 */

public interface IBuildInfor {

    int getVersionCode(Context context);

    String getVersionName(Context context);

    PackageInfo getPackageInfo(Context context);
}
