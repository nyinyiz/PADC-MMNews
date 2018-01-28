package com.padcmyanmar.sfcnn.mvp.presenters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.padcmyanmar.sfcnn.SFCNewsApp;
import com.padcmyanmar.sfcnn.data.models.NewsModel;
import com.padcmyanmar.sfcnn.data.vo.NewsVO;
import com.padcmyanmar.sfcnn.delegates.NewsItemDelegate;
import com.padcmyanmar.sfcnn.mvp.views.NewsListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by aung on 1/6/18.
 */

public class NewsListPresenter extends BasePresenter<NewsListView> implements NewsItemDelegate {

    @Inject
    NewsModel mNewsModel;

    public NewsListPresenter() {

    }

    @Override
    public void onCreate(NewsListView view) {
        super.onCreate(view);
        SFCNewsApp sfcNewsApp = (SFCNewsApp) mView.getContext();
        sfcNewsApp.getSFCAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        /*
        List<NewsVO> newsList = mNewsModel.getNews();
        if (!newsList.isEmpty()) {
            mView.displayNewsList(newsList);
        } else {
            mView.showLoading();
        }
        */
    }

    @Override
    public void onStop() {

    }

    public void onNewsListEndReach(Context context) {
        mNewsModel.loadMoreNews(context);
    }

    public void onForceRefresh(Context context) {
        mNewsModel.forceRefreshNews(context);
    }

    public void onDataLoaded(Context context, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<NewsVO> newsList = new ArrayList<>();

            do {
                NewsVO news = NewsVO.parseFromCursor(context, data);
                newsList.add(news);
            } while (data.moveToNext());

            mView.displayNewsList(newsList);
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

    public void onSuccessGoogleSignIn(GoogleSignInAccount signInAccount) {
        mNewsModel.authenticateUserWithGoogleAccount(signInAccount, new NewsModel.UserAuthenticateDelegate() {
            @Override
            public void onSuccessAuthenticate(GoogleSignInAccount account) {
                Log.d(SFCNewsApp.LOG_TAG, "onSuccessAuthenticate : " + account.getDisplayName());
                Toast.makeText(mView.getContext(), "Name : "+account.getDisplayName() +" Email :"+account.getEmail(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailureAuthenticate(String errorMsg) {
                Log.d(SFCNewsApp.LOG_TAG, "onFailureAuthenticate : " + errorMsg);
            }
        });
    }
}
