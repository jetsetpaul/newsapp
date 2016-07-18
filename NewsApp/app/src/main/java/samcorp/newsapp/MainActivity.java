package samcorp.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecycler;
    List<Story> stories;
    RecyclerAdapter adapter;
    RecyclerAdapter.OnItemClickListener listener;
    RecyclerView.ViewHolder storyViewHolder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(llm);

        initializeData();
        intializeAdapter();

        listener = new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Story story) {
                    Intent launchIntent = new Intent(MainActivity.this, DetailActivity.class);
                    launchIntent.putExtra(DetailActivity.EXTRA_STORY, "story");
                    launchIntent.putExtra(DetailActivity.NEW_EXTRA, story);
//                    startActivity(launchIntent);
            }
        };

    }

    private void initializeData() {
        stories = new ArrayList<>();
        stories.add(new Story("Stuff happened somewhere", "http://www.google.com", R.drawable.com_facebook_profile_picture_blank_square, "blurblurblurblublurblburlblurb"));
        stories.add(new Story("More stuff happened somewhere else", "http://www.chorus.fm", R.drawable.com_facebook_profile_picture_blank_square,"blurbyblurblurblurblurbylblub"));
        stories.add(new Story("Not really much to read here", "http://www.reddit.com", R.drawable.com_facebook_profile_picture_blank_square,"BLURB! Blurblurb!"));
    }

    private void intializeAdapter() {
        adapter = new RecyclerAdapter(stories, listener);
        mRecycler.setAdapter(adapter);
    }


}
