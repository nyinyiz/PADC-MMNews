package com.nyinyi.nw.sfc.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by User on 12/2/2017.
 */

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    /**generac so tr generalize lot lit tr*/
    private W mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

    }

    public abstract void setmData(W data);


    public abstract void bind(Context context);
}

