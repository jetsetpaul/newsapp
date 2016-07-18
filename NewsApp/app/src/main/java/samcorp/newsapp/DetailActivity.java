package samcorp.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class DetailActivity extends AppCompatActivity {
    Story testStory = new Story("Stuff happened somewhere", "http://www.google.com", R.drawable.com_facebook_profile_picture_blank_square, "blurblurblurblublurblburlblurb");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        FacebookSdk.sdkInitialize(getApplicationContext());
        ShareButton fbShareButton = (ShareButton) findViewById(R.id.share_btn);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(testStory.getLink()))
                .build();
        fbShareButton.setShareContent(content);
        Intent i = getIntent();
            Log.d("PaulsApp", i.getStringExtra("headline"));
            //set the text in my fragment
        DetailFragment df = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        df.setStoryText(i.getStringExtra("headline"), i.getStringExtra("blurb"),
                 i.getIntExtra("image", 1));
    }
}
