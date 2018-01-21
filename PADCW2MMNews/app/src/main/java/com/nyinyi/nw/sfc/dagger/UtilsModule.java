package com.nyinyi.nw.sfc.dagger;

import android.content.Context;

import com.nyinyi.nw.sfc.SFCNewsApp;
import com.nyinyi.nw.sfc.utils.ConfigUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 1/7/18.
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    public ConfigUtils provideConfigUtils(Context context) {
        return new ConfigUtils(context);
    }


}
