package com.padcmyanmar.sfcnn.mvp.views;

import android.content.Context;

import com.padcmyanmar.sfcnn.data.vo.NewsVO;

import java.util.List;

/**
 * Created by aung on 1/6/18.
 */

public interface NewsListView {

    void displayNewsList(List<NewsVO> newsList);

    void showLoading();

    void navigateToNewsDetails(NewsVO news);

    Context getContext();
}
