package com.nyinyi.nw.sfc.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyinyi.nw.sfc.R;
import com.nyinyi.nw.sfc.viewitems.NewsDetailsImageViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/11/2017.
 */

public class NewsImagesPagerAdapter extends PagerAdapter {

    LayoutInflater mLayoutInflater;
    private List<String> mImages;

    public NewsImagesPagerAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mImages = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //child view ka viewPager hmr shi t' view hot ma hot check tr
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       NewsDetailsImageViewItem itemview = (NewsDetailsImageViewItem) mLayoutInflater.inflate(R.layout.view_item_news_details_image,container,false);

       itemview.setData(mImages.get(position));

       container.addView(itemview);
       return itemview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    public void setImage(List<String> images)
    {
        mImages = images;
        notifyDataSetChanged();
    }
}
