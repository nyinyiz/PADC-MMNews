package com.nyinyi.nw.sfc.dagger;

import com.nyinyi.nw.sfc.SFCNewsApp;
import com.nyinyi.nw.sfc.activities.NewsListActivity;
import com.nyinyi.nw.sfc.data.models.NewsModel;
import com.nyinyi.nw.sfc.mvp.presenters.NewsListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by user on 1/6/18.
 */


@Component(modules = {AppModule.class,UtilsModule.class,NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(SFCNewsApp app);

    void inject(NewsModel newsModel);

    void inject(NewsListPresenter newsListPresenter);

    void inject(NewsListActivity newsListActivity);


}
