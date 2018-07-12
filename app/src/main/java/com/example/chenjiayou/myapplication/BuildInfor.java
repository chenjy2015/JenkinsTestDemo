package com.example.chenjiayou.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by chenjiayou on 2018/7/12.
 */

public class BuildInfor implements IBuildInfor {

    //版本名
    @Override
    public String getVersionName(Context context) {

        return getPackageInfo(context).versionName;
    }

    //版本号
    @Override
    public int getVersionCode(Context context) {

        return getPackageInfo(context).versionCode;
    }

    @Override
    public PackageInfo getPackageInfo(Context context) {

        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                                   PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;

    }
}