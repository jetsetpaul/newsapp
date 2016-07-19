package samcorp.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    LinearLayoutManager layoutManager;
    MyAdapter mAdapter;
    News mNewsList;
    String query;
    ListView mDrawerList;
    CategoryListAdapter listAdapter;
    List<NewsCategory> mCategoryList;
    GuardianNews.NewsListener newsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());

        mNewsList = News.getInstance();

        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

        mRecycler = (RecyclerView) findViewById(R.id.recycler);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();


        newsListener = new GuardianNews.NewsListener() {
            @Override
            public void onNewsLoaded(ArrayList<Story> stories) {
                for(Story story:stories){
                    mNewsList.addStory(story);
                    mAdapter = new MyAdapter(mNewsList.getStories(), MainActivity.this);
                    mRecycler.setAdapter(mAdapter);
                }
            }
        };


        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_FILM);

        layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mNewsList.clearStories();
                        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_US);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecycler.setLayoutManager(layoutManager);
                        break;
                    case 1:
                        mNewsList.clearStories();
                        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_WORLD);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecycler.setLayoutManager(layoutManager);
                        break;
                    case 2:
                        mNewsList.clearStories();
                        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_OPINION);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecycler.setLayoutManager(layoutManager);
                        break;
                    case 3:
                        mNewsList.clearStories();
                        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_POLITICS);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecycler.setLayoutManager(layoutManager);
                        break;
                    case 4:
                        mNewsList.clearStories();
                        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_SPORTS);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecycler.setLayoutManager(layoutManager);
                        break;
                    case 5:
                        mNewsList.clearStories();
                        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_TECH);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecycler.setLayoutManager(layoutManager);
                        break;
                    case 6:
                        mNewsList.clearStories();
                        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_TECH);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecycler.setLayoutManager(layoutManager);
                        break;
                    case 7:
                        mNewsList.clearStories();
                        new GuardianNews.DownloadUrlTask(newsListener).execute(Constants.GUARDIAN_CULTURE);
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecycler.setLayoutManager(layoutManager);
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
        mCategoryList.add(new NewsCategory("Politics", R.drawable.ic_action_monolog));
        mCategoryList.add(new NewsCategory("Sports", R.drawable.football));
        mCategoryList.add(new NewsCategory("Tech", R.drawable.ic_menu_share));
        mCategoryList.add(new NewsCategory("Arts", R.drawable.ic_color_lens));
        listAdapter = new CategoryListAdapter(MainActivity.this, mCategoryList);
        mDrawerList.setAdapter(listAdapter);
    }



}
