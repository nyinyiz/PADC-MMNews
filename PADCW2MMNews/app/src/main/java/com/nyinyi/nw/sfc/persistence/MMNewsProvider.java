package com.nyinyi.nw.sfc.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.DecimalFormat;

/**
 * Created by User on 12/16/2017.
 */

public class MMNewsProvider extends ContentProvider {

    public static final int ACTED_USER = 100;
    public static final int PUBLICATION = 200;
    public static final int NEWS = 300;
    public static final int NEWS_IMAGE = 400;
    public static final int FAVORITE_ACTION = 500;
    public static final int COMMENT_ACTION = 600;
    public static final int SEND_TO_ACTION = 700;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final SQLiteQueryBuilder sNewsWithPublication_IJ;
    private static final SQLiteQueryBuilder sNewsWithFavorite_IJ;
    private static final SQLiteQueryBuilder sNewsWithComment_IJ;
    private static final SQLiteQueryBuilder sNewsWithSendto_IJ;


    //one on one pl ya tl
    static {
        sNewsWithPublication_IJ = new SQLiteQueryBuilder();
        sNewsWithPublication_IJ.setTables(
                MMNewsDBContract.NewsEntry.TABLE_NAME + " INNER JOIN " +
                        MMNewsDBContract.NewsPublicatonEntry.TABLE_NAME + " ON " +
                        MMNewsDBContract.NewsEntry.TABLE_NAME + "." + MMNewsDBContract.NewsEntry.COLUMN_PUBLICATION_ID + " = " +
                        MMNewsDBContract.NewsPublicatonEntry.TABLE_NAME + "." + MMNewsDBContract.NewsPublicatonEntry.COLUMN_PUBLICATION_ID
        );
    }

    static {
        sNewsWithFavorite_IJ = new SQLiteQueryBuilder();
        sNewsWithFavorite_IJ.setTables(
                MMNewsDBContract.FavoriteEntery.TABLE_NAME + " INNER JOIN " +
                        MMNewsDBContract.UserEntery.TABLE_NAME + " ON " +
                        MMNewsDBContract.FavoriteEntery.TABLE_NAME + "." + MMNewsDBContract.FavoriteEntery.COLUMN_ACTED_USER_ID + " = " +
                        MMNewsDBContract.UserEntery.TABLE_NAME + "." + MMNewsDBContract.UserEntery.COLUMN_USER_ID
        );

    }

    static {
        sNewsWithComment_IJ = new SQLiteQueryBuilder();
        sNewsWithComment_IJ.setTables(
                MMNewsDBContract.CommentsEntery.TABLE_NAME + " INNER JOIN " +
                        MMNewsDBContract.UserEntery.TABLE_NAME + " ON " +
                        MMNewsDBContract.CommentsEntery.TABLE_NAME + "." + MMNewsDBContract.CommentsEntery.COLUMN_ACTED_USER_ID + " = " +
                        MMNewsDBContract.UserEntery.TABLE_NAME + "." + MMNewsDBContract.UserEntery.COLUMN_USER_ID
        );

    }

    static {
        sNewsWithSendto_IJ = new SQLiteQueryBuilder();
        sNewsWithSendto_IJ.setTables(
                MMNewsDBContract.SendToEntery.TABLE_NAME + " INNER JOIN " +
                        MMNewsDBContract.UserEntery.TABLE_NAME + " ON " +
                        MMNewsDBContract.SendToEntery.TABLE_NAME+"."+MMNewsDBContract.SendToEntery.COLUMN_ACTED_USER_ID + " = " +
                        MMNewsDBContract.UserEntery.TABLE_NAME+"."+MMNewsDBContract.UserEntery.COLUMN_USER_ID + " INNER JOIN " +
                        MMNewsDBContract.UserEntery.TABLE_NAME + " AS au ON " +
                        MMNewsDBContract.SendToEntery.TABLE_NAME+"."+MMNewsDBContract.SendToEntery.COLUMN_RECEIVED_USER_ID + " = "+
                        "au." + MMNewsDBContract.UserEntery.COLUMN_USER_ID
        );
    }


    private MMNewsDBHelper mDBHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MMNewsDBContract.CONTENT_AUTHORITY, MMNewsDBContract.PATH_USER, ACTED_USER);
        uriMatcher.addURI(MMNewsDBContract.CONTENT_AUTHORITY, MMNewsDBContract.PATH_PUBLICATION, PUBLICATION);
        uriMatcher.addURI(MMNewsDBContract.CONTENT_AUTHORITY, MMNewsDBContract.PATH_NEWS, NEWS);
        uriMatcher.addURI(MMNewsDBContract.CONTENT_AUTHORITY, MMNewsDBContract.PATH_IMAGE, NEWS_IMAGE);
        uriMatcher.addURI(MMNewsDBContract.CONTENT_AUTHORITY, MMNewsDBContract.PATH_FAVORITE, FAVORITE_ACTION);
        uriMatcher.addURI(MMNewsDBContract.CONTENT_AUTHORITY, MMNewsDBContract.PATH_COMMENT, COMMENT_ACTION);
        uriMatcher.addURI(MMNewsDBContract.CONTENT_AUTHORITY, MMNewsDBContract.PAHT_SENT_TO, SEND_TO_ACTION);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ACTED_USER:
                return MMNewsDBContract.UserEntery.TABLE_NAME;
            case PUBLICATION:
                return MMNewsDBContract.NewsPublicatonEntry.TABLE_NAME;
            case NEWS:
                return MMNewsDBContract.NewsEntry.TABLE_NAME;
            case NEWS_IMAGE:
                return MMNewsDBContract.NewsImageEntry.TABLE_NAME;
            case FAVORITE_ACTION:
                return MMNewsDBContract.FavoriteEntery.TABLE_NAME;
            case COMMENT_ACTION:
                return MMNewsDBContract.CommentsEntery.TABLE_NAME;
            case SEND_TO_ACTION:
                return MMNewsDBContract.SendToEntery.TABLE_NAME;
            default:
                return null;
        }

    }

    private Uri getContentUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ACTED_USER:
                return MMNewsDBContract.UserEntery.CONTENT_URI;
            case PUBLICATION:
                return MMNewsDBContract.NewsPublicatonEntry.CONTENT_URI;
            case NEWS:
                return MMNewsDBContract.NewsEntry.CONTENT_URI;
            case NEWS_IMAGE:
                return MMNewsDBContract.NewsImageEntry.CONTENT_URI;
            case FAVORITE_ACTION:
                return MMNewsDBContract.FavoriteEntery.CONTENT_URI;
            case COMMENT_ACTION:
                return MMNewsDBContract.CommentsEntery.CONTENT_URI;
            case SEND_TO_ACTION:
                return MMNewsDBContract.SendToEntery.CONTENT_URI;
            default:
                return null;
        }
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new MMNewsDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor queryCursor;

        switch (sUriMatcher.match(uri)) {
            case NEWS:
                queryCursor = sNewsWithPublication_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case FAVORITE_ACTION:
                queryCursor = sNewsWithFavorite_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case COMMENT_ACTION:
                queryCursor = sNewsWithComment_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case SEND_TO_ACTION:
                queryCursor = sNewsWithSendto_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

        }

        if (getContext() != null) {
            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ACTED_USER:
                return MMNewsDBContract.UserEntery.DIR_TYPE;
            case PUBLICATION:
                return MMNewsDBContract.NewsPublicatonEntry.DIR_TYPE;
            case NEWS:
                return MMNewsDBContract.NewsEntry.DIR_TYPE;
            case NEWS_IMAGE:
                return MMNewsDBContract.NewsImageEntry.DIR_TYPE;
            case FAVORITE_ACTION:
                return MMNewsDBContract.FavoriteEntery.DIR_TYPE;
            case COMMENT_ACTION:
                return MMNewsDBContract.CommentsEntery.DIR_TYPE;
            case SEND_TO_ACTION:
                return MMNewsDBContract.SendToEntery.DIR_TYPE;

        }
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return insertedUri;
        }
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;
        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
//            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDelteted;
        String tableName = getTableName(uri);
        rowDelteted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDelteted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDelteted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
