package com.padcmyanmar.sfcnn.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.sfcnn.R;
import com.padcmyanmar.sfcnn.data.vo.NewsVO;
import com.padcmyanmar.sfcnn.delegates.NewsItemDelegate;
import com.padcmyanmar.sfcnn.viewholders.NewsViewHolder;

/**
 * Created by aung on 11/4/17.
 */

public class NewsAdapter extends BaseRecyclerAdapter<NewsViewHolder, NewsVO> {

    private NewsItemDelegate mNewsItemDelegate;

    public NewsAdapter(Context context, NewsItemDelegate newsItemDelegate) {
        super(context);
        mNewsItemDelegate = newsItemDelegate;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsItemView = mLayoutInflator.inflate(R.layout.view_item_news, parent, false);
        return new NewsViewHolder(newsItemView, mNewsItemDelegate);
    }


}