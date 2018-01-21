package com.nyinyi.nw.sfc.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nyi on 12/15/2017.
 */

public class MMNewsDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mmnews.db";
    private static final int DB_VERSION = 2;


    String SQL_CREATE_NEWS_TABLE = "CREATE TABLE " + MMNewsDBContract.NewsEntry.TABLE_NAME
            + " (" + MMNewsDBContract.NewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MMNewsDBContract.NewsEntry.COLUMN_NEWS_ID + " VARCHAR(256), "
            + MMNewsDBContract.NewsEntry.COLUMN_NEWS_BRIEF + " TEXT, "
            + MMNewsDBContract.NewsEntry.COLUMN_DETAIL + " TEXT, "
            + MMNewsDBContract.NewsEntry.COLUMN_POSTED_DATE + " TEXT, "
            + MMNewsDBContract.NewsEntry.COLUMN_PUBLICATION_ID + " TEXT, "
            + " UNIQUE ( "
            + MMNewsDBContract.NewsEntry.COLUMN_NEWS_ID
            + " ) ON CONFLICT REPLACE "
            + ");";

    String SQL_CREATE_NEWS_IMAGE_TABLE = "CREATE TABLE " + MMNewsDBContract.NewsImageEntry.TABLE_NAME
            + "(" + MMNewsDBContract.NewsImageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MMNewsDBContract.NewsImageEntry.COLUMN_NEWS_IMAGE_NAME + " TEXT,"
            + MMNewsDBContract.NewsImageEntry.COLUMN_NEWS_ID + " VARCHAR(256), "
            + " UNIQUE ( "
            + MMNewsDBContract.NewsImageEntry.COLUMN_NEWS_IMAGE_NAME
            + " ) ON CONFLICT REPLACE "
            + " );";

    String SQL_CREATE_NEWS_PUBLICATION_TABLE = "CREATE TABLE " + MMNewsDBContract.NewsPublicatonEntry.TABLE_NAME
            + "(" + MMNewsDBContract.NewsPublicatonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATION_ID + " VARCHAR(256),"
            + MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATIION_TITLE + " TEXT,"
            + MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATION_LOGO + " TEXT,"
            + " UNIQUE ( "
            + MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATION_ID
            + " ) ON CONFLICT REPLACE );";

    String SQL_CREATE_USER_TABLE = "CREATE TABLE " + MMNewsDBContract.UserEntery.TABLE_NAME
            + "(" + MMNewsDBContract.UserEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MMNewsDBContract.UserEntery.COLUMN_USER_ID + " VARCHAR(256),"
            + MMNewsDBContract.UserEntery.COLUMN_USER_NAME + " TEXT,"
            + MMNewsDBContract.UserEntery.COLUMN_PROFILE_IMAGE + " TEXT,"
            + " UNIQUE ( "
            + MMNewsDBContract.UserEntery.COLUMN_USER_ID
            + " ) ON CONFLICT REPLACE );";

    String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + MMNewsDBContract.FavoriteEntery.TABLE_NAME
            + "(" + MMNewsDBContract.FavoriteEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MMNewsDBContract.FavoriteEntery.COLUMN_FAVORITE_ID + " VARCHAR(256),"
            + MMNewsDBContract.FavoriteEntery.COLUMN_FAVORITE_DATE + " TEXT,"
            + MMNewsDBContract.FavoriteEntery.COLUMN_ACTED_USER_ID + " VARCHAR(256),"
            + MMNewsDBContract.FavoriteEntery.COLUMN_NEWS_ID + " VARCHAR(256),"
            + " UNIQUE ( "
            + MMNewsDBContract.FavoriteEntery.COLUMN_FAVORITE_ID
            + " ) ON CONFLICT REPLACE );";

    String SQL_CREATE_COMMENTS_TABLE = "CREATE TABLE " + MMNewsDBContract.CommentsEntery.TABLE_NAME
            + "(" + MMNewsDBContract.CommentsEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MMNewsDBContract.CommentsEntery.COLUMN_COMMENT_ID + " VARCHAR(256),"
            + MMNewsDBContract.CommentsEntery.COLUMN_COMMENT + " TEXT,"
            + MMNewsDBContract.CommentsEntery.COLUMN_COMMENT_DATE + " TEXT,"
            + MMNewsDBContract.CommentsEntery.COLUMN_ACTED_USER_ID + " VARCHAR(256),"
            + MMNewsDBContract.CommentsEntery.COLUMN_NEWS_ID + " VARCHAR(256),"
            + " UNIQUE ( "
            + MMNewsDBContract.CommentsEntery.COLUMN_COMMENT_ID
            + " ) ON CONFLICT REPLACE );";

    String SQL_CREATE_SEND_TO_TABLE = "CREATE TABLE " + MMNewsDBContract.SendToEntery.TABLE_NAME
            + "(" + MMNewsDBContract.SendToEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MMNewsDBContract.SendToEntery.COLUMN_SEND_TO_ID + " VARCHAR(256),"
            + MMNewsDBContract.SendToEntery.COLUMN_SEND_DATE + " TEXT,"
            + MMNewsDBContract.SendToEntery.COLUMN_ACTED_USER_ID + " VARCHAR(256),"
            + MMNewsDBContract.SendToEntery.COLUMN_RECEIVED_USER_ID + " VARCHAR(256),"
            + MMNewsDBContract.SendToEntery.COLUMN_NEWS_ID + " VARCHAR(256),"
            + " UNIQUE ( "
            + MMNewsDBContract.SendToEntery.COLUMN_SEND_TO_ID
            + " ) ON CONFLICT REPLACE );";

    public MMNewsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_NEWS_PUBLICATION_TABLE);
        db.execSQL(SQL_CREATE_NEWS_TABLE);
        db.execSQL(SQL_CREATE_NEWS_IMAGE_TABLE);

        db.execSQL(SQL_CREATE_FAVORITE_TABLE);
        db.execSQL(SQL_CREATE_COMMENTS_TABLE);
        db.execSQL(SQL_CREATE_SEND_TO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsDBContract.SendToEntery.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsDBContract.CommentsEntery.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsDBContract.FavoriteEntery.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsDBContract.NewsImageEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsDBContract.NewsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsDBContract.NewsPublicatonEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsDBContract.UserEntery.TABLE_NAME);

        onCreate(db);
    }
}
