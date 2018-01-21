package com.nyinyi.nw.sfc.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/2/2017.
 */

public class NewsVO {

    @SerializedName("news-id")
    private String newId;
    @SerializedName("brief")
    private String brief;
    @SerializedName("details")
    private String detail;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("posted-date")
    private String postedDate;

    @SerializedName("publication")
    private PublicationVO publication;

    @SerializedName("favorites")
    private List<FavoritesVO> favoriteActions;

    @SerializedName("comments")
    private List<CommentsVO> comments;

    @SerializedName("sent-tos")
    private List<SendToVO> sendTo;


    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<String> getImages() {

        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPostedData() {
        return postedDate;
    }

    public void setPostedData(String postedDate) {
        this.postedDate = postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public void setPublication(PublicationVO publication) {
        this.publication = publication;
    }

    public List<FavoritesVO> getFavoriteActions() {
        return favoriteActions;
    }

    public void setFavoriteActions(List<FavoritesVO> favoriteActions) {
        this.favoriteActions = favoriteActions;
    }

    public List<CommentsVO> getComments() {

        if (comments == null) {
            comments = new ArrayList<>();
        }
        return comments;
    }

    public List<SendToVO> getSendTo() {

        if (sendTo == null)
        {
            sendTo = new ArrayList<>();
        }
        return sendTo;
    }

    public void setSendTo(List<SendToVO> sendTo) {
        this.sendTo = sendTo;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsDBContract.NewsEntry.COLUMN_NEWS_ID, newId);
        contentValues.put(MMNewsDBContract.NewsEntry.COLUMN_NEWS_BRIEF, brief);
        contentValues.put(MMNewsDBContract.NewsEntry.COLUMN_DETAIL, detail);
        contentValues.put(MMNewsDBContract.NewsEntry.COLUMN_POSTED_DATE, postedDate);
        contentValues.put(MMNewsDBContract.NewsEntry.COLUMN_PUBLICATION_ID, publication.getPublicationId());
        return contentValues;
    }

    public static NewsVO parseFromCursor(Context context, Cursor data) {

        NewsVO newsVO = new NewsVO();
        newsVO.newId = data.getString(data.getColumnIndex(MMNewsDBContract.NewsEntry.COLUMN_NEWS_ID));
        newsVO.brief = data.getString(data.getColumnIndex(MMNewsDBContract.NewsEntry.COLUMN_NEWS_BRIEF));
        newsVO.detail = data.getString(data.getColumnIndex(MMNewsDBContract.NewsEntry.COLUMN_DETAIL));
        newsVO.postedDate = data.getString(data.getColumnIndex(MMNewsDBContract.NewsEntry.COLUMN_POSTED_DATE));

        newsVO.publication = PublicationVO.parseFromCursor(data);
        newsVO.images = loadImageInNews(context, newsVO.newId);
        newsVO.favoriteActions = loadFavoriteActionsInNews(context, newsVO.newId);
        newsVO.comments = loadCommentActionInNews(context, newsVO.newId);
        newsVO.sendTo = loadSendToActionInNews(context,newsVO.newId);
        return newsVO;
    }

    private static List<SendToVO> loadSendToActionInNews(Context context, String newId) {
        Cursor sendToActionInNewsCursor = context.getContentResolver().query(MMNewsDBContract.SendToEntery.CONTENT_URI,
                null,
                MMNewsDBContract.SendToEntery.COLUMN_NEWS_ID + " = ? ",
                new String[]{newId},
                null);

        if (sendToActionInNewsCursor != null && sendToActionInNewsCursor.moveToFirst())
        {
            List<SendToVO> sendToVOList = new ArrayList<>();
            do {
                SendToVO sendToVO = SendToVO.parseFromCursor(context,sendToActionInNewsCursor);
                sendToVOList.add(sendToVO);
            }while (sendToActionInNewsCursor.moveToNext());

            sendToActionInNewsCursor.close();
            return sendToVOList;
        }
        return null;
    }

    private static List<CommentsVO> loadCommentActionInNews(Context context, String newId) {
        Cursor commentActionInNewsCursor = context.getContentResolver().query(MMNewsDBContract.CommentsEntery.CONTENT_URI,
                null,
                MMNewsDBContract.CommentsEntery.COLUMN_NEWS_ID + " = ? ",
                new String[]{newId},
                null);

        if (commentActionInNewsCursor != null && commentActionInNewsCursor.moveToFirst()) {
            List<CommentsVO> commentsVOList = new ArrayList<>();
            do {
                CommentsVO commentsVO = CommentsVO.parseFromCursor(context, commentActionInNewsCursor);
                commentsVOList.add(commentsVO);

            } while (commentActionInNewsCursor.moveToNext());

            commentActionInNewsCursor.close();
            return commentsVOList;

        }
        return null;


    }

    private static List<FavoritesVO> loadFavoriteActionsInNews(Context context, String newsId) {
        Cursor favoriteActionInNewsCursor = context.getContentResolver().query(MMNewsDBContract.FavoriteEntery.CONTENT_URI,
                null,
                MMNewsDBContract.FavoriteEntery.COLUMN_NEWS_ID + " = ? ",
                new String[]{newsId},
                null);

        if (favoriteActionInNewsCursor != null && favoriteActionInNewsCursor.moveToFirst()) {
            List<FavoritesVO> favoritesVOList = new ArrayList<>();
            do {

                FavoritesVO favoritesVO = FavoritesVO.parseFromCursor(context, favoriteActionInNewsCursor);
                favoritesVOList.add(favoritesVO);
            } while (favoriteActionInNewsCursor.moveToNext());
            favoriteActionInNewsCursor.close();
            return favoritesVOList;
        }
        return null;

    }

    private static List<String> loadImageInNews(Context context, String newId) {

        Cursor imageInNewsCursor = context.getContentResolver().query(MMNewsDBContract.NewsImageEntry.CONTENT_URI,
                null,
                MMNewsDBContract.NewsImageEntry.COLUMN_NEWS_ID + " = ? ",
                new String[]{newId},
                null);

        if (imageInNewsCursor != null && imageInNewsCursor.moveToFirst()) {
            List<String> imageInNews = new ArrayList<>();
            do {
                imageInNews.add(
                        imageInNewsCursor.getString(
                                imageInNewsCursor.getColumnIndex(MMNewsDBContract.NewsImageEntry.COLUMN_NEWS_IMAGE_NAME)
                        )
                );
            } while (imageInNewsCursor.moveToNext());
            imageInNewsCursor.close();

            return imageInNews;

        }
        return null;
    }

}














