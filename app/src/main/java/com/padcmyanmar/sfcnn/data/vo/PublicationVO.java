package com.padcmyanmar.sfcnn.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.sfcnn.persistence.MMNewsContract;

/**
 * Created by aung on 12/3/17.
 */

public class PublicationVO {

    @SerializedName("publication-id")
    private String publicationId;

    @SerializedName("title")
    private String title;

    @SerializedName("logo")
    private String logo;

    public String getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID, publicationId);
        contentValues.put(MMNewsContract.PublicationEntry.COLUMN_TITLE, title);
        contentValues.put(MMNewsContract.PublicationEntry.COLUMN_LOGO, logo);
        return contentValues;
    }

    public static PublicationVO parseFromCursor(Cursor cursor) {
        PublicationVO publication = new PublicationVO();
        publication.publicationId = cursor.getString(cursor.getColumnIndex(MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID));
        publication.title = cursor.getString(cursor.getColumnIndex(MMNewsContract.PublicationEntry.COLUMN_TITLE));
        publication.logo = cursor.getString(cursor.getColumnIndex(MMNewsContract.PublicationEntry.COLUMN_LOGO));
        return publication;
    }
}
