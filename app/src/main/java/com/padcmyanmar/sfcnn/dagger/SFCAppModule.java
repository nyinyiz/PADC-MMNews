package com.padcmyanmar.sfcnn.dagger;

import android.content.Context;

import com.padcmyanmar.sfcnn.SFCNewsApp;
import com.padcmyanmar.sfcnn.data.models.NewsModel;
import com.padcmyanmar.sfcnn.mvp.presenters.NewsListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aung on 1/6/18.
 */

@Module
public class SFCAppModule {

    private SFCNewsApp mApp;

    public SFCAppModule(SFCNewsApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public NewsModel provideNewsModel(Context context) {
        return new NewsModel(context);
    }

    @Provides
    public NewsListPresenter provideNewsListPresenter() {
        return new NewsListPresenter();
    }
}
