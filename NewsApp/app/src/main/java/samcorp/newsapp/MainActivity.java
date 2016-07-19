package samcorp.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.FacebookSdk;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    LinearLayoutManager layoutManager;
    MyAdapter mAdapter;
    News mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());

        mNewsList = News.getInstance();

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


    }


}
