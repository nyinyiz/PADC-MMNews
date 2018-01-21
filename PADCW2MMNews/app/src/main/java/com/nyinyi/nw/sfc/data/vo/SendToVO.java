package com.nyinyi.nw.sfc.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;

import java.security.Principal;

/**
 * Created by User on 12/3/2017.
 */

public class SendToVO {

    @SerializedName("send-to-id")
     private String sendToId;
    @SerializedName("sent-date")
     private String sendDate;
    @SerializedName("acted-user")
     private ActedUserVO actedUser;
    @SerializedName("received-user")
     private ActedUserVO receivedUser;

    public String getSendToId() {
        return sendToId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public ActedUserVO getReceivedUser() {
        return receivedUser;
    }

    public ContentValues parseToContentValues(String newsId)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsDBContract.SendToEntery.COLUMN_SEND_TO_ID,sendToId);
        contentValues.put(MMNewsDBContract.SendToEntery.COLUMN_SEND_DATE,sendDate);
        contentValues.put(MMNewsDBContract.SendToEntery.COLUMN_ACTED_USER_ID,actedUser.getUserId());
        contentValues.put(MMNewsDBContract.SendToEntery.COLUMN_RECEIVED_USER_ID,receivedUser.getUserId());
        contentValues.put(MMNewsDBContract.SendToEntery.COLUMN_NEWS_ID,newsId);
        return contentValues;
    }

    public static SendToVO parseFromCursor(Context context, Cursor sendToActionInNewsCursor) {

        SendToVO sendToVO = new SendToVO();
        sendToVO.sendToId = sendToActionInNewsCursor.getString(sendToActionInNewsCursor.getColumnIndex(MMNewsDBContract.SendToEntery.COLUMN_SEND_TO_ID));
        sendToVO.sendDate = sendToActionInNewsCursor.getString(sendToActionInNewsCursor.getColumnIndex(MMNewsDBContract.SendToEntery.COLUMN_SEND_DATE));
        sendToVO.actedUser = ActedUserVO.parseFromCursor(sendToActionInNewsCursor);
        sendToVO.receivedUser = ActedUserVO.parseFromCursor(sendToActionInNewsCursor);

        return sendToVO;

    }
}
