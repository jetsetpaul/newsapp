package samcorp.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
            Log.d("PaulsApp", i.getStringExtra("headline"));
            //set the text in my fragment
        DetailFragment df = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        df.setStoryText(i.getStringExtra("headline"), i.getStringExtra("blurb"),
                 R.mipmap.ic_launcher);
    }
}
