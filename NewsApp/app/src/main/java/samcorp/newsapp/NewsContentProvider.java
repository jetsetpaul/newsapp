package samcorp.newsapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by pauljoiner on 7/19/16.
 */
public class NewsContentProvider extends ContentProvider {
    private NewsDBHelper myDB;
    private static final String AUTHORITY = "samcorp.newsapp.NewsContentProvider";
    private static final String ARTICLES_TABLE = "articles";
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + ARTICLES_TABLE);

    public static final int ARTICLES = 1;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, ARTICLES_TABLE, ARTICLES);
    }
    @Override
    public boolean onCreate() {
        myDB = new NewsDBHelper(getContext(), null, null, 1);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder){
        int uriType = sURIMatcher.match(uri);
        Cursor cursor = null;

        switch (uriType) {
            case ARTICLES:
                if (selection == null){
                    cursor = myDB.getAllArticles();
                } else {
                    cursor = myDB.getArticle(selection, selectionArgs);
                } break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);

        long id = 0;
        switch (uriType) {
            case ARTICLES:
                id = myDB.addArticle(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(ARTICLES_TABLE + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        int rowsDeleted = 0;

        switch (uriType) {
            case ARTICLES:
                rowsDeleted = myDB.deleteAllArticles();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri,
                      ContentValues values,
                      String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
