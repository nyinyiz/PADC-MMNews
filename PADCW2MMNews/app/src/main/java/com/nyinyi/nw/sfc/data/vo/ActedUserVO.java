package com.nyinyi.nw.sfc.data.vo;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;

/**
 * Created by User on 12/3/2017.
 */

public class ActedUserVO {

    @SerializedName("user-id")
    private String userId;
    @SerializedName("user-name")
    private String userName;
    @SerializedName("profile-image")
    private String profileImage;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImage() {
        return profileImage;
    }



    public ContentValues parseToContentValues()
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsDBContract.UserEntery.COLUMN_USER_ID,userId);
        contentValues.put(MMNewsDBContract.UserEntery.COLUMN_USER_NAME,userName);
        contentValues.put(MMNewsDBContract.UserEntery.COLUMN_PROFILE_IMAGE,profileImage);
        return contentValues;
    }

    public static ActedUserVO parseFromCursor(Cursor cursor) {
        ActedUserVO actedUserVO = new ActedUserVO();
        actedUserVO.userId = cursor.getString(cursor.getColumnIndex(MMNewsDBContract.UserEntery.COLUMN_USER_ID));
        actedUserVO.userName = cursor.getString(cursor.getColumnIndex(MMNewsDBContract.UserEntery.COLUMN_USER_NAME));
        actedUserVO.profileImage = cursor.getString(cursor.getColumnIndex(MMNewsDBContract.UserEntery.COLUMN_PROFILE_IMAGE));
        return actedUserVO;
    }

}
