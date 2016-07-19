package samcorp.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        FacebookSdk.sdkInitialize(getApplicationContext());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ImageView image = (ImageView) findViewById(R.id.fragmentImage);
            //set the text in my fragment
        DetailFragment df = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        Story story = (Story) bundle.getSerializable("story");
        df.setStoryText(story.getTitle(), story.getBlurb());
        Picasso.with(df.getContext()).load(story.getImage()).into(image);

        ShareButton fbShareButton = (ShareButton) findViewById(R.id.share_btn);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(story.getLink()))
        .build();
        fbShareButton.setShareContent(content);
    }
}
