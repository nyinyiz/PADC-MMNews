package com.nyinyi.nw.sfc.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;

/**
 * Created by User on 12/3/2017.
 */

public class FavoritesVO {

     @SerializedName("favorite-id")
     private String favoriteId;
     @SerializedName("favorite-date")
     private String favoriteDate;
     @SerializedName("acted-user")
     private ActedUserVO actedUser;

     public String getFavoriteId() {
          return favoriteId;
     }

     public String getFavoriteData() {
          return favoriteDate;
     }

     public ActedUserVO getActedUser() {
          return actedUser;
     }

     public  ContentValues parseToContentValues(String newsId)
     {
          ContentValues contentValues = new ContentValues();
          contentValues.put(MMNewsDBContract.FavoriteEntery.COLUMN_FAVORITE_ID,favoriteId);
          contentValues.put(MMNewsDBContract.FavoriteEntery.COLUMN_FAVORITE_DATE,favoriteDate);
          contentValues.put(MMNewsDBContract.FavoriteEntery.COLUMN_ACTED_USER_ID,actedUser.getUserId());
          contentValues.put(MMNewsDBContract.FavoriteEntery.COLUMN_NEWS_ID,newsId);
          return contentValues;
     }

     public static FavoritesVO parseFromCursor(Context context, Cursor favoriteActionInNewsCursor) {
          FavoritesVO favoritesVO = new FavoritesVO();
          favoritesVO.favoriteId = favoriteActionInNewsCursor.getString(favoriteActionInNewsCursor.getColumnIndex(MMNewsDBContract.FavoriteEntery.COLUMN_FAVORITE_ID));
          favoritesVO.favoriteDate = favoriteActionInNewsCursor.getString(favoriteActionInNewsCursor.getColumnIndex(MMNewsDBContract.FavoriteEntery.COLUMN_FAVORITE_DATE));
          favoritesVO.actedUser = ActedUserVO.parseFromCursor(favoriteActionInNewsCursor);

          return favoritesVO;
     }
}
