package com.nyinyi.nw.sfc.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.nyinyi.nw.sfc.SFCNewsApp;

/**
 * Created by Nyi on 12/10/2017.
 */

public class MMNewsDBContract {

    public static final String CONTENT_AUTHORITY = SFCNewsApp.class.getPackage().getName();
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_NEWS = "news";
    public static final String PATH_IMAGE = "image";
    public static final String PATH_PUBLICATION = "publication";
    public static final String PATH_USER = "user";
    public static final String PATH_FAVORITE = "favorite";
    public static final String PATH_COMMENT = "comment";
    public static final String PAHT_SENT_TO = "sendto";

    public static final class NewsEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NEWS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;

        public static final String TABLE_NAME = PATH_NEWS;

        public static final String COLUMN_NEWS_ID = "news_id";
        public static final String COLUMN_NEWS_BRIEF = "brief";
        public static final String COLUMN_DETAIL = "detail";
        public static final String COLUMN_POSTED_DATE = "posted_date";
        public static final String COLUMN_PUBLICATION_ID = "publication_id";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class NewsImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_IMAGE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_IMAGE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_IMAGE;

        public static final String TABLE_NAME = PATH_IMAGE;

        public static final String COLUMN_NEWS_IMAGE_NAME = "news_image_name";
        public static final String COLUMN_NEWS_ID = "news_id";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }


    public static final class NewsPublicatonEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PUBLICATION).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PUBLICATION;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PUBLICATION;

        public static final String TABLE_NAME = PATH_PUBLICATION;

        public static final String COLUMN_PUBLICATION_ID = "publication_id";
        public static final String COLUMN_PUBLICATIION_TITLE = "publication_title";
        public static final String COLUMN_PUBLICATION_LOGO = "publication_logo";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class UserEntery implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String TABLE_NAME = PATH_USER;

        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_PROFILE_IMAGE = "user_profile_image";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class FavoriteEntery implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITE;

        public static final String TABLE_NAME = PATH_FAVORITE;

        public static final String COLUMN_FAVORITE_ID = "favorite_id";
        public static final String COLUMN_FAVORITE_DATE = "favorite_date";
        public static final String COLUMN_ACTED_USER_ID = "acted_user_id";
        public static final String COLUMN_NEWS_ID = "news_id";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }


    public static final class CommentsEntery implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMMENT).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMMENT;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMMENT;

        public static final String TABLE_NAME = PATH_COMMENT;

        public static final String COLUMN_COMMENT_ID = "comment_id";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_COMMENT_DATE = "comment_date";
        public static final String COLUMN_ACTED_USER_ID = "acted_user_id";
        public static final String COLUMN_NEWS_ID = "news_id";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }


    public static final class SendToEntery implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PAHT_SENT_TO).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PAHT_SENT_TO;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PAHT_SENT_TO;

        public static final String TABLE_NAME = PAHT_SENT_TO;

        public static final String COLUMN_SEND_TO_ID = "send_to_id";
        public static final String COLUMN_SEND_DATE = "send_date";
        public static final String COLUMN_ACTED_USER_ID = "acted_user_id";
        public static final String COLUMN_RECEIVED_USER_ID = "received_id";
        public static final String COLUMN_NEWS_ID = "news_id";

        public static Uri buildContentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }

}
