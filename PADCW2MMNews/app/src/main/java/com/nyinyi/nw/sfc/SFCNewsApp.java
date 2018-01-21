package com.nyinyi.nw.sfc;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nyinyi.nw.sfc.dagger.AppComponent;
import com.nyinyi.nw.sfc.dagger.AppModule;
import com.nyinyi.nw.sfc.dagger.DaggerAppComponent;
import com.nyinyi.nw.sfc.dagger.NetworkModule;
import com.nyinyi.nw.sfc.dagger.UtilsModule;
import com.nyinyi.nw.sfc.data.models.NewsModel;

import javax.inject.Inject;

/**
 * Created by User on 11/4/2017.
 */

public class SFCNewsApp extends Application {

    public static final String LOG_TAG = "SFCNewsApp";

    private AppComponent mAppComponent;


    @Inject
    Context mContext;

    @Inject
    NewsModel mNewsModel;


    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = initDagger();//dagger init
        mAppComponent.inject(this);//register consumer

        mNewsModel.startloadingMMNews(getApplicationContext());

        Log.d(LOG_TAG,"mContext : "+ mContext);

    }

    private AppComponent initDagger()
    {
       return DaggerAppComponent.builder()
               .appModule(new AppModule(this))
               .utilsModule(new UtilsModule())
               .networkModule(new NetworkModule())
               .build();
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
