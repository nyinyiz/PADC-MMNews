package com.nyinyi.nw.sfc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyinyi.nw.sfc.R;
import com.nyinyi.nw.sfc.data.vo.NewsVO;
import com.nyinyi.nw.sfc.delegates.NewsItemDelegate;
import com.nyinyi.nw.sfc.viewholder.NewsViewHolder;

/**
 * Created by User on 11/4/2017.
 */

public class NewsAdapter extends
        BaseRecyclerAdapter<NewsViewHolder , NewsVO> {

    private NewsItemDelegate mDelegate;

    public NewsAdapter(Context context, NewsItemDelegate newsItemDelegate) {
        super(context);
        mDelegate = newsItemDelegate;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsItemVeiw = mLayoutInflator.inflate(R.layout.view_item_news,parent,false);
        return new NewsViewHolder(newsItemVeiw,mDelegate);
    }

}
