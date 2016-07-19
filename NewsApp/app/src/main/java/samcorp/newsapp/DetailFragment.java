package samcorp.newsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
    public void setStoryText(String title, String blurb){
        //access our textView
        TextView titleText = (TextView) getView().findViewById(R.id.storyTitle);
        titleText.setText(title);
        TextView blurbText = (TextView) getView().findViewById(R.id.storyBlurb);
        blurbText.setText(blurb);
    }
}
