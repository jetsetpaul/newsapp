package samcorp.newsapp;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by samwyz on 7/19/16.
 */
public class MyContentObserver extends ContentObserver {

    Context mContext;
    changeListener listener;

    public MyContentObserver(Handler handler, changeListener listener, Context context) {
        super(handler);
        this.listener = listener;
        mContext = context;
    }
    @Override
    public void onChange(boolean selfChange, Uri uri) {

        listener.change(mContext.getContentResolver().query(NewsContentProvider.CONTENT_URI,
                null, null, null, null));
    }

    public interface changeListener
    {
        void change(Cursor cursor);
    }
}
