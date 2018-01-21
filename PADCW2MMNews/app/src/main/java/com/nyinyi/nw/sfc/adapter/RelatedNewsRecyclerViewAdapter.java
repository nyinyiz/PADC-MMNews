package com.nyinyi.nw.sfc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyinyi.nw.sfc.R;

/**
 * Created by User on 11/16/2017.
 */

public class RelatedNewsRecyclerViewAdapter extends
        RecyclerView.Adapter<RelatedNewsRecyclerViewAdapter.ViewHolder> {

    Context context;
    LayoutInflater inflater;

    public RelatedNewsRecyclerViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_related_news,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
