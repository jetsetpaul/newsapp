package samcorp.newsapp;

import java.util.ArrayList;

/**
 * Created by samwyz on 7/18/16.
 */
public class News {

    private static News ourInstance = new News();
    private static ArrayList<Story> mStoryList;

    public static News getInstance() {
        return ourInstance;
    }

    private News() {
    }

    public void addStory(Story story){
        if (mStoryList == null) {
            mStoryList = new ArrayList<>();
        } mStoryList.add(story);
    }

    public ArrayList<Story> getStories(){
        return mStoryList;
    }

    public void clearStories(){
        mStoryList.clear();
    }
}