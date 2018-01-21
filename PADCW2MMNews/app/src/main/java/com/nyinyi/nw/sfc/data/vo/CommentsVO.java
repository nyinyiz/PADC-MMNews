package com.nyinyi.nw.sfc.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;

/**
 * Created by User on 12/3/2017.
 */

public class CommentsVO {

     @SerializedName("comment-id")
     private String commentId;

     @SerializedName("comment")
     private String comment;

     @SerializedName("comment-date")
     private String commentDate;

     @SerializedName("acted-user")
     private ActedUserVO actedUser;

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public ContentValues parseToContentValues(String newsId)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsDBContract.CommentsEntery.COLUMN_COMMENT_ID,commentId);
        contentValues.put(MMNewsDBContract.CommentsEntery.COLUMN_COMMENT,comment);
        contentValues.put(MMNewsDBContract.CommentsEntery.COLUMN_COMMENT_DATE,commentDate);
        contentValues.put(MMNewsDBContract.CommentsEntery.COLUMN_ACTED_USER_ID,actedUser.getUserId());
        contentValues.put(MMNewsDBContract.CommentsEntery.COLUMN_NEWS_ID,newsId);
        return contentValues;
    }

    public static CommentsVO parseFromCursor(Context context, Cursor commentActionInNewsCursor) {
        CommentsVO commentsVO = new CommentsVO();
        commentsVO.commentId = commentActionInNewsCursor.getString(commentActionInNewsCursor.getColumnIndex(MMNewsDBContract.CommentsEntery.COLUMN_COMMENT_ID));
        commentsVO.comment = commentActionInNewsCursor.getString(commentActionInNewsCursor.getColumnIndex(MMNewsDBContract.CommentsEntery.COLUMN_COMMENT));
        commentsVO.commentDate = commentActionInNewsCursor.getString(commentActionInNewsCursor.getColumnIndex(MMNewsDBContract.CommentsEntery.COLUMN_COMMENT_DATE));
        commentsVO.actedUser = ActedUserVO.parseFromCursor(commentActionInNewsCursor);
        return commentsVO;
    }
}
