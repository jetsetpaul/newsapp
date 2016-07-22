package samcorp.newsapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String AUTHORITY = "samcorp.newsapp.NewsContentProvider";
    public static final String ACCOUNT_TYPE = "example.com";
    public static final String ACCOUNT = "default_account";
    public static final String PREFS_NAME = "USER_PREFS";


    RecyclerView mRecycler;
    LinearLayoutManager layoutManager;
    MyAdapter mAdapter;
    News mNewsList;
    ListView mDrawerList;
    MyCursorAdapter mCursorAdapter;
    CategoryListAdapter listAdapter;
    List<NewsCategory> mCategoryList;
    GuardianNews.NewsListener newsListener;
    ContentResolver mResolver;
    Account mAccount;
    NewsDBHelper dbHelper;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        scheduleNotification();

        mNewsList = News.getInstance();
        mDrawerList = (ListView)findViewById(R.id.navList);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecycler.setLayoutManager(layoutManager);
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

        mResolver = getContentResolver();
        dbHelper = new NewsDBHelper(MainActivity.this, null, null, 1);


        mAccount = createSyncAccount(MainActivity.this);

        ContentResolver.setSyncAutomatically(mAccount, AUTHORITY, true);
        ContentResolver.addPeriodicSync(mAccount, AUTHORITY, Bundle.EMPTY, 500);

        Cursor cursor = mResolver.query(NewsContentProvider.CONTENT_URI, NewsDBHelper.projection,
                null, null, null);
        mCursorAdapter = new MyCursorAdapter(MainActivity.this, cursor);
        mRecycler.setAdapter(mCursorAdapter);

        mResolver.registerContentObserver(NewsContentProvider.CONTENT_URI, true,
                new MyContentObserver(new Handler(), mCursorAdapter, MainActivity.this));




        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Cursor cursor = mResolver.query(NewsContentProvider.CONTENT_URI, NewsDBHelper.projection,
                                NewsDBHelper.COLUMN_CATEGORY + "=?", new String[]{"U.S."}, null);
                        MyCursorAdapter cursorAdapter = new MyCursorAdapter(MainActivity.this, cursor);
                        Log.d("GAT", String.valueOf(cursor.getCount()));
                        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mRecycler.setAdapter(cursorAdapter);
                        break;
                    case 1:
                        cursor = mResolver.query(NewsContentProvider.CONTENT_URI, NewsDBHelper.projection,
                                NewsDBHelper.COLUMN_CATEGORY + "=?", new String[]{"World"}, null);
                        cursorAdapter = new MyCursorAdapter(MainActivity.this, cursor);
                        Log.d("GAT", String.valueOf(cursor.getCount()));
                        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mRecycler.setAdapter(cursorAdapter);
                        break;
                    case 2:
                        cursor = mResolver.query(NewsContentProvider.CONTENT_URI, NewsDBHelper.projection,
                                NewsDBHelper.COLUMN_CATEGORY + "=?", new String[]{"Opinion"}, null);
                        cursorAdapter = new MyCursorAdapter(MainActivity.this, cursor);
                        Log.d("GAT", String.valueOf(cursor.getCount()));
                        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mRecycler.setAdapter(cursorAdapter);
                        break;
                    case 3:
                        cursor = mResolver.query(NewsContentProvider.CONTENT_URI, NewsDBHelper.projection,
                                NewsDBHelper.COLUMN_CATEGORY + "=?", new String[]{"Travel"}, null);
                        cursorAdapter = new MyCursorAdapter(MainActivity.this, cursor);
                        Log.d("GAT", String.valueOf(cursor.getCount()));
                        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mRecycler.setAdapter(cursorAdapter);
                        break;
                    case 4:
                        cursor = mResolver.query(NewsContentProvider.CONTENT_URI, NewsDBHelper.projection,
                                NewsDBHelper.COLUMN_CATEGORY + "=?", new String[]{"Sports"}, null);
                        cursorAdapter = new MyCursorAdapter(MainActivity.this, cursor);
                        Log.d("GAT", String.valueOf(cursor.getCount()));
                        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mRecycler.setAdapter(cursorAdapter);
                        break;
                    case 5:
                        cursor = mResolver.query(NewsContentProvider.CONTENT_URI, NewsDBHelper.projection,
                                NewsDBHelper.COLUMN_CATEGORY + "=?", new String[]{"Technology"}, null);
                        cursorAdapter = new MyCursorAdapter(MainActivity.this, cursor);
                        Log.d("GAT", String.valueOf(cursor.getCount()));
                        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mRecycler.setAdapter(cursorAdapter);
                        break;
                    case 6:
                        cursor = mResolver.query(NewsContentProvider.CONTENT_URI, NewsDBHelper.projection,
                                NewsDBHelper.COLUMN_CATEGORY + "=?", new String[]{"Movies"}, null);
                        cursorAdapter = new MyCursorAdapter(MainActivity.this, cursor);
                        Log.d("GAT", String.valueOf(cursor.getCount()));
                        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        mRecycler.setAdapter(cursorAdapter);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void addDrawerItems() {
        mCategoryList = new ArrayList<>();
        mCategoryList.add(new NewsCategory("U.S. News", R.mipmap.ic_newspaper));
        mCategoryList.add(new NewsCategory("World News", R.mipmap.ic_earth));
        mCategoryList.add(new NewsCategory("Opinion", R.drawable.ic_feedback));
        mCategoryList.add(new NewsCategory("Travel", R.mipmap.ic_travel));
        mCategoryList.add(new NewsCategory("Sports", R.mipmap.ic_soccer));
        mCategoryList.add(new NewsCategory("Tech", R.mipmap.ic_tech));
        mCategoryList.add(new NewsCategory("Movies", R.mipmap.ic_film));
        listAdapter = new CategoryListAdapter(MainActivity.this, mCategoryList);
        mDrawerList.setAdapter(listAdapter);
    }

    public static Account createSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }
        return newAccount;
    }

    public void scheduleNotification() {
        Notification notification = getNotification("content");
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + 30000;
        //set actionIntent, pending intent
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Intent actionIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingAction = PendingIntent.getActivity(MainActivity.this, 897, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingAction);
        return builder.build();
    }



}
