package samcorp.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pauljoiner on 7/19/16.
 */
public class NewsDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newsDB.db";
    public static final String TABLE_ARTICLES = "articles";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_BLURB = "blurb";
    public static final String COLUMN_IMAGE = "image";

    public NewsDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ARTICLES_TABLE = "CREATE TABLE " +
                TABLE_ARTICLES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE
                + " TEXT," + COLUMN_LINK + " TEXT," + COLUMN_BLURB
                + " TEXT," + COLUMN_IMAGE + " INTEGER)";
        db.execSQL(CREATE_ARTICLES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLES);
        onCreate(db);
    }

    public long addArticle(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long insertedRow = db.insert(TABLE_ARTICLES, null, values);
        return insertedRow;
    }
    public Cursor getAllArticles() {
        String[] projection = {COLUMN_ID, COLUMN_TITLE, COLUMN_LINK, COLUMN_BLURB, COLUMN_IMAGE};

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_ARTICLES,projection,null,null,null,null,null);
        return cursor;
    }
    public int deleteAllArticles() {
        SQLiteDatabase db = getWritableDatabase();

        int rowsDeleted = db.delete(TABLE_ARTICLES,null,null);
        return rowsDeleted;
    }
}
