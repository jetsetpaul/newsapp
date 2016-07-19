package samcorp.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    LinearLayoutManager layoutManager;
    MyAdapter mAdapter;
    News mNewsList;
    ListView mDrawerList;
    CategoryListAdapter listAdapter;
    List<NewsCategory> mCategoryList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());

        mNewsList = News.getInstance();
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Clicked " + mCategoryList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        mRecycler = (RecyclerView) findViewById(R.id.recycler);

        GuardianNews.NewsListener newsListener = new GuardianNews.NewsListener() {
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "clicked " + position, Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void addDrawerItems() {
        mCategoryList = new ArrayList<NewsCategory>();
        mCategoryList.add(new NewsCategory("U.S. News", R.drawable.usa));
        mCategoryList.add(new NewsCategory("World News", R.drawable.ic_action_globe));
        mCategoryList.add(new NewsCategory("Opinion", R.drawable.ic_action_monolog));
        mCategoryList.add(new NewsCategory("Sports", R.drawable.football));
        mCategoryList.add(new NewsCategory("Tech", R.drawable.ic_menu_share));
        mCategoryList.add(new NewsCategory("Arts", R.drawable.ic_color_lens));
//;
        listAdapter = new CategoryListAdapter(MainActivity.this, mCategoryList);
        mDrawerList.setAdapter(listAdapter);
    }



}
