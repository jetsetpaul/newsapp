package samcorp.newsapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());

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
        ContentResolver.addPeriodicSync(mAccount, AUTHORITY, Bundle.EMPTY, 100);

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
        mCategoryList.add(new NewsCategory("U.S. News", R.drawable.usa));
        mCategoryList.add(new NewsCategory("World News", R.drawable.ic_action_globe));
        mCategoryList.add(new NewsCategory("Opinion", R.drawable.ic_action_monolog));
        mCategoryList.add(new NewsCategory("Travel", R.drawable.ic_action_monolog));
        mCategoryList.add(new NewsCategory("Sports", R.drawable.football));
        mCategoryList.add(new NewsCategory("Tech", R.drawable.ic_menu_share));
        mCategoryList.add(new NewsCategory("Movies", R.drawable.ic_color_lens));
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
}
