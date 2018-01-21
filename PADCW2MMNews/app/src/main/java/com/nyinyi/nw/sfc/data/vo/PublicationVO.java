package com.nyinyi.nw.sfc.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.sfc.persistence.MMNewsDBContract;

/**
 * Created by User on 12/3/2017.
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

    public ContentValues parseToContentValues()
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATION_ID,publicationId);
        contentValues.put(MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATIION_TITLE,title);
        contentValues.put(MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATION_LOGO,logo);
        return contentValues;
    }

    public static PublicationVO parseFromCursor(Cursor data) {
        PublicationVO publicationVO = new PublicationVO();
        publicationVO.publicationId = data.getString(data.getColumnIndex(MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATION_ID));
        publicationVO.title = data.getString(data.getColumnIndex(MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATIION_TITLE));
        publicationVO.logo = data.getString(data.getColumnIndex(MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATION_LOGO));

        return publicationVO;
    }
}
