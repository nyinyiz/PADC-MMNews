package com.nyinyi.nw.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nyinyi.nw.sfc.R;
import com.nyinyi.nw.sfc.adapter.NewsImagesPagerAdapter;
import com.nyinyi.nw.sfc.adapter.RelatedNewsRecyclerViewAdapter;
import com.nyinyi.nw.sfc.data.vo.NewsVO;
import com.nyinyi.nw.sfc.delegates.NewsDetailsDelegate;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 11/11/2017.
 */

public class NewsDetailsActivity extends BaseActivity
        implements NewsDetailsDelegate,LoaderManager.LoaderCallbacks<Cursor> {

    private static final String IE_NEWS_ID = "IE_NEWS_ID";

    private static final int NEWS_DETAIL_LOADER_ID = 1002;


    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;
    @BindView(R.id.btn_comment_news)
    TextView commentNews;
    @BindView(R.id.btn_sent_to_news)
    TextView sentToNews;
    @BindView(R.id.rv_related_news)
    RecyclerView relatedNewsRecycler;
    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;
    @BindView(R.id.tv_publication_name)
    TextView tvPublicationName;
    @BindView(R.id.tv_publiched_date)
     TextView tvPublication_date;
    @BindView(R.id.tv_news_details)
     TextView tvNewsDetail;


    NewsVO mNewsVO;

    private String mNewsId;
    private NewsImagesPagerAdapter mNewsImagesPagerAdapter;

    /**
     *
     * @param context
     * @param newsId : id of the news object.
     * @return
     */

    public static Intent newIntent(Context context,String newsId)
    {
        Intent intent = new Intent(context,NewsDetailsActivity.class);
        intent.putExtra(IE_NEWS_ID,newsId);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this,this);


        mNewsImagesPagerAdapter = new NewsImagesPagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(mNewsImagesPagerAdapter);

        RelatedNewsRecyclerViewAdapter adapter = new RelatedNewsRecyclerViewAdapter(getApplicationContext());
        relatedNewsRecycler.setHasFixedSize(true);
        relatedNewsRecycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));
        relatedNewsRecycler.setAdapter(adapter);

        mNewsId = getIntent().getStringExtra(IE_NEWS_ID);

        if (TextUtils.isEmpty(mNewsId))
        {
            throw new UnsupportedOperationException("newsId required for NewsDetailsActivity");
        }else {

            getSupportLoaderManager().initLoader(NEWS_DETAIL_LOADER_ID,null,this);
        }

    }

    @OnClick(R.id.btn_comment_news)
    public void commentNews()
    {
        this.onTapComment();
    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapComment() {
        Intent intent = NewsCommentActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    public void onTapSentTo() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                MMNewsDBContract.NewsEntry.CONTENT_URI,
                null,
                MMNewsDBContract.NewsEntry.COLUMN_NEWS_ID + " = ? ",
                new String []{mNewsId},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst())
        {
           NewsVO newsVO = NewsVO.parseFromCursor(getApplicationContext(),data);

           bindData(newsVO);
        }

    }

    private void bindData(NewsVO newsVO) {

        tvPublicationName.setText(newsVO.getPublication().getTitle());
        tvPublication_date.setText(newsVO.getPostedData());
        tvNewsDetail.setText(newsVO.getDetail());

        Glide.with(this)
                .load(newsVO.getPublication().getLogo())
                .into(ivPublicationLogo);

        if (newsVO.getImages().isEmpty())
        {

        }else {
            mNewsImagesPagerAdapter.setImage(newsVO.getImages());
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
