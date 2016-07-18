package samcorp.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    List<Story> mStoryList;
    MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(llm);

        initializeData();
        intializeAdapter();

    }

    private void initializeData() {
        mStoryList = new ArrayList<>();
        mStoryList.add(new Story("Stuff happened somewhere", "http://www.google.com", R.drawable.com_facebook_profile_picture_blank_square, "blurblurblurblublurblburlblurb"));
        mStoryList.add(new Story("More stuff happened somewhere else", "http://www.chorus.fm", R.drawable.com_facebook_profile_picture_blank_square,"blurbyblurblurblurblurbylblub"));
        mStoryList.add(new Story("Not really much to read here", "http://www.reddit.com", R.drawable.com_facebook_profile_picture_blank_square,"BLURB! Blurblurb!"));
    }

    private void intializeAdapter() {
        adapter = new MyAdapter(mStoryList, this);
        mRecycler.setAdapter(adapter);
    }


}
