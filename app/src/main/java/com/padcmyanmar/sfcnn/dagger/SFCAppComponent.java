package com.padcmyanmar.sfcnn.dagger;

import com.padcmyanmar.sfcnn.SFCNewsApp;
import com.padcmyanmar.sfcnn.activities.NewsListActivity;
import com.padcmyanmar.sfcnn.data.models.NewsModel;
import com.padcmyanmar.sfcnn.mvp.presenters.NewsListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by aung on 1/6/18.
 */

@Component(modules = {SFCAppModule.class, UtilsModule.class, NetworkModule.class})
@Singleton
public interface SFCAppComponent {
    void inject(SFCNewsApp app);

    void inject(NewsModel newsModel);

    void inject(NewsListPresenter newsListPresenter);

    void inject(NewsListActivity newsListActivity);
}
