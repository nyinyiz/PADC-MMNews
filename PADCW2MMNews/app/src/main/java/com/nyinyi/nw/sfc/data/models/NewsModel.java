package com.nyinyi.nw.sfc.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.nyinyi.nw.sfc.SFCNewsApp;
import com.nyinyi.nw.sfc.data.vo.CommentsVO;
import com.nyinyi.nw.sfc.data.vo.FavoritesVO;
import com.nyinyi.nw.sfc.data.vo.NewsVO;
import com.nyinyi.nw.sfc.data.vo.PublicationVO;
import com.nyinyi.nw.sfc.data.vo.SendToVO;
import com.nyinyi.nw.sfc.events.RestApiEvents;
import com.nyinyi.nw.sfc.network.MMNewsDataAgent;
import com.nyinyi.nw.sfc.network.MMNewsDataAgentImpl;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;
import com.nyinyi.nw.sfc.utils.AppConstants;
import com.nyinyi.nw.sfc.utils.ConfigUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by User on 12/3/2017.
 */

public class NewsModel {

//    private static NewsModel objInstance;

    private List<NewsVO> mNews;

    @Inject
    MMNewsDataAgent mDataAgent;

    @Inject
    ConfigUtils mConfigUtils;


    public NewsModel(Context context) {
        EventBus.getDefault().register(this);
        mNews = new ArrayList<>();

        SFCNewsApp sfcNewsApp = (SFCNewsApp) context.getApplicationContext();
        sfcNewsApp.getmAppComponent().inject(this);

    }

    public void startloadingMMNews(Context context) {
        mDataAgent.loadMMNews(AppConstants.ACCESS_TOKEN,
                mConfigUtils.loadPageIndex(), context);
    }

    public List<NewsVO> getNews() {
        return mNews;
    }

    public void loadMoreNews(Context context) {
        int pageIndex = mConfigUtils.loadPageIndex();
        mDataAgent .loadMMNews(AppConstants.ACCESS_TOKEN,
               pageIndex , context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
        mNews.addAll(event.getLoadNews());
        mConfigUtils.savePageIndex(event.getLoadedpageIndex() + 1);

        //TODO LOGIC TO SAVE THE DATA IN PERSISTENCE LAYER
        ContentValues[] newsCVs = new ContentValues[event.getLoadNews().size()];
        List<ContentValues> publicationVOList = new ArrayList<>();
        List<ContentValues> imageInNewsCVList = new ArrayList<>();

        List<ContentValues> favoriteInNewsCVList = new ArrayList<>();
        List<ContentValues> userInFavoriteCVList = new ArrayList<>();

        List<ContentValues> commentInNewsCVList = new ArrayList<>();

        List<ContentValues> sendToInNewsCVList = new ArrayList<>();

        for (int index = 0; index < newsCVs.length; index++) {

            newsCVs[index] = event.getLoadNews().get(index).parseToContentValues();

            PublicationVO publicationVO = event.getLoadNews().get(index).getPublication();
            publicationVOList.add(publicationVO.parseToContentValues());


            for (String imageUrl : event.getLoadNews().get(index).getImages()) {
                ContentValues imageInNewsCV = new ContentValues();
                imageInNewsCV.put(MMNewsDBContract.NewsImageEntry.COLUMN_NEWS_ID, event.getLoadNews().get(index).getNewId());
                imageInNewsCV.put(MMNewsDBContract.NewsImageEntry.COLUMN_NEWS_IMAGE_NAME, imageUrl);

                imageInNewsCVList.add(imageInNewsCV);
            }

            for (FavoritesVO favoritesVO : event.getLoadNews().get(index).getFavoriteActions()) {
                ContentValues favoriteActionCV = favoritesVO.parseToContentValues(event.getLoadNews().get(index).getNewId());
                favoriteInNewsCVList.add(favoriteActionCV);

                ContentValues userInActionCV = favoritesVO.getActedUser().parseToContentValues();
                userInFavoriteCVList.add(userInActionCV);

            }

            for (CommentsVO commentsVO : event.getLoadNews().get(index).getComments()) {
                ContentValues commentActionCV = commentsVO.parseToContentValues(event.getLoadNews().get(index).getNewId());
                commentInNewsCVList.add(commentActionCV);

                ContentValues userInActionCV = commentsVO.getActedUser().parseToContentValues();
                userInFavoriteCVList.add(userInActionCV);
            }

            for (SendToVO sendToVO : event.getLoadNews().get(index).getSendTo())
            {
                ContentValues sendToActionCV = sendToVO.parseToContentValues(event.getLoadNews().get(index).getNewId());
                sendToInNewsCVList.add(sendToActionCV);

                ContentValues userInActionCV = sendToVO.getActedUser().parseToContentValues();
                userInFavoriteCVList.add(userInActionCV);

                ContentValues userInReceiveCV = sendToVO.getReceivedUser().parseToContentValues();
                userInFavoriteCVList.add(userInReceiveCV);

            }

        }


        int insertPublication = event.getContext().getContentResolver().bulkInsert(MMNewsDBContract.NewsPublicatonEntry.CONTENT_URI,
                publicationVOList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedPublication : " + insertPublication);

        int insertedImage = event.getContext().getContentResolver().bulkInsert(MMNewsDBContract.NewsImageEntry.CONTENT_URI,
                imageInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Image : " + insertedImage);

        int insertedFavorite = event.getContext().getContentResolver().bulkInsert(MMNewsDBContract.FavoriteEntery.CONTENT_URI,
                favoriteInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedFavorite : " + insertedFavorite);

        int inserteduserInFavorite = event.getContext().getContentResolver().bulkInsert(MMNewsDBContract.UserEntery.CONTENT_URI,
                userInFavoriteCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "inserteduserInFavorite : " + inserteduserInFavorite);

        int insertedComment = event.getContext().getContentResolver().bulkInsert(MMNewsDBContract.CommentsEntery.CONTENT_URI,
                commentInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedComment : " + insertedComment);

        int insertedSendTo = event.getContext().getContentResolver().bulkInsert(MMNewsDBContract.SendToEntery.CONTENT_URI,
                sendToInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "insertedSendTo : " + insertedSendTo);

        int insertNews = event.getContext().getContentResolver().bulkInsert(MMNewsDBContract.NewsEntry.CONTENT_URI, newsCVs);
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Row : " + insertNews);


    }

    public void forceRefreshnews(Context context) {
        mNews = new ArrayList<>();
        mConfigUtils.savePageIndex(1);
        startloadingMMNews(context);
    }
}
