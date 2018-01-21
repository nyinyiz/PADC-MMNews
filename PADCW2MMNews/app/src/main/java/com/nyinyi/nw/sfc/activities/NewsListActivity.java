package com.nyinyi.nw.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.nyinyi.nw.sfc.R;
import com.nyinyi.nw.sfc.SFCNewsApp;
import com.nyinyi.nw.sfc.adapter.NewsAdapter;
import com.nyinyi.nw.sfc.components.EmptyViewPod;
import com.nyinyi.nw.sfc.components.SmartRecyclerView;
import com.nyinyi.nw.sfc.components.SmartScrollListener;
import com.nyinyi.nw.sfc.data.models.NewsModel;
import com.nyinyi.nw.sfc.data.vo.NewsVO;
import com.nyinyi.nw.sfc.delegates.NewsItemDelegate;
import com.nyinyi.nw.sfc.events.RestApiEvents;
import com.nyinyi.nw.sfc.events.TapNewsEvent;
import com.nyinyi.nw.sfc.mvp.presenters.NewsListPresenter;
import com.nyinyi.nw.sfc.mvp.views.NewsListView;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor>,NewsListView{

    private static final int NEWS_LIST_LOADER_ID = 100;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.rv_news)
    SmartRecyclerView rvNews;
    @BindView(R.id.vp_empty_news)
    EmptyViewPod vpEmptyNews;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    NewsListPresenter mPresenter;

    private SmartScrollListener mSmartScrollListener;

    private NewsAdapter newsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this,this);

        SFCNewsApp sfcNewsApp = (SFCNewsApp) getApplicationContext();
        sfcNewsApp.getmAppComponent().inject(this);

        mPresenter.onCreate(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = LoginRegisterActivity.newIntent(NewsListActivity.this);
               startActivity(intent);
            }
        });

        rvNews.setEmptyView(vpEmptyNews);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));
        newsAdapter = new NewsAdapter(getApplicationContext(),mPresenter);
        rvNews.setAdapter(newsAdapter);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Snackbar.make(rvNews,"Loading new data.",Snackbar.LENGTH_INDEFINITE).show();
                swipeRefreshLayout.setRefreshing(true);
                mPresenter.onNewsListEndReach(getApplicationContext());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onFourceRefresh(getApplicationContext());
            }
        });

        rvNews.addOnScrollListener(mSmartScrollListener);

        /**Load news form database*/
        getSupportLoaderManager().initLoader(NEWS_LIST_LOADER_ID,null,this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().unregister(this);
        mPresenter.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTapNewsEvent(TapNewsEvent event)
    {
//        Intent intent = NewsDetailsActivity.newIntent(getApplicationContext());
//        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event)
    {
        /*newsAdapter.appendNewData(event.getLoadNews());
        swipeRefreshLayout.setRefreshing(false);*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event)
    {
        Snackbar.make(rvNews,event.getErrorMsg(),Snackbar.LENGTH_INDEFINITE).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                MMNewsDBContract.NewsEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mPresenter.onDataLoaded(data,getApplicationContext());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void displayNewsList(List<NewsVO> newsList) {
        newsAdapter.setNewData(newsList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void navigateToNewsDetails(NewsVO newsVO) {
        Intent intent = NewsDetailsActivity.newIntent(getApplicationContext(),newsVO.getNewId());
        startActivity(intent);

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
