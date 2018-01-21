package com.nyinyi.nw.sfc.mvp.presenters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.nyinyi.nw.sfc.SFCNewsApp;
import com.nyinyi.nw.sfc.activities.NewsDetailsActivity;
import com.nyinyi.nw.sfc.activities.NewsListActivity;
import com.nyinyi.nw.sfc.data.models.NewsModel;
import com.nyinyi.nw.sfc.data.vo.NewsVO;
import com.nyinyi.nw.sfc.delegates.NewsItemDelegate;
import com.nyinyi.nw.sfc.mvp.views.NewsListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by user on 1/6/18.
 */

public class NewsListPresenter extends BasePresenter<NewsListView> implements NewsItemDelegate {

    @Inject
    NewsModel mNewsModel;

    public NewsListPresenter() {

    }

    @Override
    public void onCreate(NewsListView view) {
        super.onCreate(view);

        SFCNewsApp sfcNewsApp = (SFCNewsApp) view.getContext();
        sfcNewsApp.getmAppComponent().inject(this);
    }

    @Override
    public void onStart() {

        List<NewsVO> newsVOList = mNewsModel.getNews();

        if (!newsVOList.isEmpty())
        {
           mView.displayNewsList(newsVOList);

        }else {
            mView.showLoading();
        }

    }



    @Override
    public void onStop() {

    }

    public void onNewsListEndReach(Context context) {

        mNewsModel.loadMoreNews(context);
    }

    public void onFourceRefresh(Context context)
    {
        mNewsModel.forceRefreshnews(context);
    }

    public void onDataLoaded(Cursor data,Context context)
    {
        if (data != null && data.moveToFirst())
        {
            List<NewsVO> newsVOList = new ArrayList<>();
            do {
                NewsVO newsVO = NewsVO.parseFromCursor(context,data);
                newsVOList.add(newsVO);
            }while (data.moveToNext());

            mView.displayNewsList(newsVOList);

        }
    }
    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatistics() {

    }

    @Override
    public void onTapNews(NewsVO news) {
        mView.navigateToNewsDetails(news);
    }


}
