package com.nyinyi.nw.sfc.mvp.presenters;

import com.nyinyi.nw.sfc.delegates.NewsDetailsDelegate;
import com.nyinyi.nw.sfc.mvp.views.NewsDetailView;

/**
 * Created by user on 1/6/18.
 */

public class NewsDetailPresenter extends BasePresenter implements NewsDetailsDelegate {

    private NewsDetailView mView;

    public NewsDetailPresenter(NewsDetailView newsDetailView)
    {
        mView = newsDetailView;
    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSentTo() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
