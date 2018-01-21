package com.nyinyi.nw.sfc.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nyinyi.nw.sfc.R;
import com.nyinyi.nw.sfc.SFCNewsApp;
import com.nyinyi.nw.sfc.data.vo.NewsVO;
import com.nyinyi.nw.sfc.delegates.NewsItemDelegate;
import com.nyinyi.nw.sfc.events.TapNewsEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 11/4/2017.
 */

public class NewsViewHolder extends BaseViewHolder<NewsVO>{

    @BindView(R.id.fl_comment_news)
    FrameLayout commentNews;
    @BindView(R.id.fl_sent_to)
    FrameLayout sentTo;
    @BindView(R.id.iv_publication_logo)
    ImageView publicationLogo;
    @BindView(R.id.tv_publication_name)
    TextView publicationName;
    @BindView(R.id.tv_publiched_date)
    TextView publichedDate;
    @BindView(R.id.tv_brief_news)
    TextView brefNews;
    @BindView(R.id.iv_news_hero_image)
    ImageView newHeroImage;
    @BindView(R.id.tv_news_statistical_data)
    TextView newsStatisticalData;


    private NewsItemDelegate mDelegate;

    private NewsVO mNewsVO;

    String strNewsStatistical;

    public NewsViewHolder(View itemView , NewsItemDelegate newsItemDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mDelegate = newsItemDelegate;
    }


    @OnClick(R.id.fl_comment_news)
    public void onTapCommment()
    {
        mDelegate.onTapComment();
    }

    @OnClick(R.id.fl_sent_to)
    public void onTapSent()
    {
        mDelegate.onTapSendTo();
    }

    @Override
    public void setmData(NewsVO data) {
        mNewsVO = data;
    }

    @Override
    public void bind(Context context) {
        if (mNewsVO != null )
        {
            if (mNewsVO.getPublication() == null)
            {
                Log.d(SFCNewsApp.LOG_TAG,"Null");
            }else {
                Glide.with(context)
                        .load(mNewsVO.getPublication().getLogo())
                        .centerCrop()
                        .into(publicationLogo);

                publicationName.setText(mNewsVO.getPublication().getTitle());
            }


            publichedDate.setText(mNewsVO.getPostedData());
            brefNews.setText(mNewsVO.getBrief());

            if (mNewsVO.getImages() == null || mNewsVO.getImages().size() <= 0 )
            {
                newHeroImage.setVisibility(View.GONE);
            }else {

                   Glide.with(context)
                           .load(mNewsVO.getImages().get(0))
                           .centerCrop()
                           .into(newHeroImage);

            }

            strNewsStatistical = ((mNewsVO.getFavoriteActions()) == null) ? "0" : mNewsVO.getFavoriteActions().size()+"";

            strNewsStatistical +=" Likes ";
            strNewsStatistical += ((mNewsVO.getComments()) == null) ? "0" : mNewsVO.getComments().size()+"";
            strNewsStatistical +=" Comments ";
            strNewsStatistical += " Sent to " ;
            strNewsStatistical += ((mNewsVO.getSendTo()) == null) ? "0" : mNewsVO.getSendTo().size()+"";
            strNewsStatistical += " People ";

            newsStatisticalData.setText(strNewsStatistical);
        }

    }

    @Override
    public void onClick(View view) {
        mDelegate.onTapNews(mNewsVO);

//        EventBus.getDefault().post(new TapNewsEvent());
    }
}
