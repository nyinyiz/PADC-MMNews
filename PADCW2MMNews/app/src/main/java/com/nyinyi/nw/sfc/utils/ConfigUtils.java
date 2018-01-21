package com.nyinyi.nw.sfc.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 12/24/17.
 */

public class ConfigUtils {


    private static final String KEY_PAGE_INDEX = "KEY_PAGE_INDEX";


    private SharedPreferences mSharePreferences;

//    private static ConfigUtils sObjInstance;

    public ConfigUtils(Context context) {
        mSharePreferences = context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE);
    }

   /* public static void initConfigUtils(Context context) {
        sObjInstance = new ConfigUtils(context);
    }*/

//    public static ConfigUtils getsObjInstance() {
//        return sObjInstance;
//    }

    public void savePageIndex(int pageIndex) {
        mSharePreferences.edit().putInt(KEY_PAGE_INDEX, pageIndex).apply();
    }

    public int loadPageIndex() {
        return mSharePreferences.getInt(KEY_PAGE_INDEX, 1);
    }

}
