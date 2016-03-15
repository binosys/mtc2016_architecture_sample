package de.binosys.android.architecture.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;



/**
 * Created by Gabriel on 11.02.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class PackageUtil {

    private static final String TAG = PackageUtil.class.getSimpleName();

    public static String getPackageName(Context context) {

        PackageManager pm = context.getPackageManager();
        String packageString = "";
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            packageString = packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Could not determine package name");
        }
        return packageString;
    }
}
