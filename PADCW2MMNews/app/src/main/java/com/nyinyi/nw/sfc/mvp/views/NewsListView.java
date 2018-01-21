package com.nyinyi.nw.sfc.mvp.views;

import android.content.Context;
import android.view.View;

import com.nyinyi.nw.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by user on 1/6/18.
 */

public interface NewsListView {

    void displayNewsList(List<NewsVO> newsList);

    void showLoading();

    void navigateToNewsDetails(NewsVO newsVO);

    Context getContext();
}
