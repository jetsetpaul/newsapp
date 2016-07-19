package samcorp.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    LinearLayoutManager layoutManager;
    MyAdapter mAdapter;
    News mNewsList;
    String query;
    ListView mDrawerList;
    ArrayAdapter<String> mStringAdapter;

    private final String GUARDIAN_URL =
            "http://content.guardianapis.com/search?order-by=newest&show-elements=video&q="
                    + query + "&limit=10&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mNewsList = News.getInstance();
        query = "politics";
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();

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

        new GuardianNews.DownloadUrlTask(newsListener).execute(GUARDIAN_URL);



        mRecycler = (RecyclerView) findViewById(R.id.recycler);





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
        String[] osArray = { "U.S. News", "World News", "Opinion", "Sports", "Tech", "Arts" };
        mStringAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mStringAdapter);
    }

}
