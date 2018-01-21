package com.nyinyi.nw.sfc.dagger;

import android.content.Context;

import com.nyinyi.nw.sfc.SFCNewsApp;
import com.nyinyi.nw.sfc.data.models.NewsModel;
import com.nyinyi.nw.sfc.mvp.presenters.NewsListPresenter;
import com.nyinyi.nw.sfc.network.MMNewsDataAgent;
import com.nyinyi.nw.sfc.network.MMNewsDataAgentImpl;
import com.nyinyi.nw.sfc.utils.ConfigUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 1/6/18.
 */

@Module
public class AppModule {

    private SFCNewsApp mApp;

    public AppModule(SFCNewsApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }


    @Provides
    @Singleton
    public NewsModel provideNewsModel(Context context)  {
        return new NewsModel(context);
    }

    @Provides
    public NewsListPresenter provideNewsListPresenter() {
        return new NewsListPresenter();
    }



}
