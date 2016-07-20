package samcorp.newsapp;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by samwyz on 7/19/16.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    ContentResolver mContentResolver;
    ArrayList<Story> list;
    News newsList;
    NewsDBHelper dbHelper;
    ContentValues contentValues;


    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mContentResolver = context.getContentResolver();

    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs){
        super(context, autoInitialize, allowParallelSyncs);

        mContentResolver = context.getContentResolver();

    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        mContentResolver.delete(NewsContentProvider.CONTENT_URI,null,null);
        String data = "";

        try {
            URL url = new URL(Constants.GUARDIAN_POLITICS);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inStream = connection.getInputStream();
            data = getInputData(inStream);
            Log.d("GAT", data);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        list = parseGuardian(data);
        for (Story story:list){
            int i = 0;
            contentValues.put(NewsDBHelper.COLUMN_ID, i);
            contentValues.put(NewsDBHelper.COLUMN_TITLE, story.getTitle());
            contentValues.put(NewsDBHelper.COLUMN_BLURB, story.getBlurb());
            contentValues.put(NewsDBHelper.COLUMN_IMAGE, story.getImage());
            contentValues.put(NewsDBHelper.COLUMN_LINK, story.getLink());
            mContentResolver.insert(NewsContentProvider.CONTENT_URI, contentValues);
            i++;

        }



    }

    private String getInputData(InputStream inStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        String data = null;

        while ((data = reader.readLine()) != null){
            builder.append(data);
        }

        reader.close();

        return builder.toString();
    }

    public ArrayList<Story> parseGuardian(String jsonString) {

        ArrayList<Story> list = new ArrayList<>();

        try {
            JSONObject newsObject = new JSONObject(jsonString);
            JSONObject responseObject = newsObject.getJSONObject("response");
            JSONArray resultsArray = responseObject.getJSONArray("results");
            for (int i = 0; i < resultsArray.length(); i++) {
                String headline = resultsArray.getJSONObject(i).getString("webTitle");
                String category = resultsArray.getJSONObject(i).getString("sectionName");
                String webLink = resultsArray.getJSONObject(i).getString("webUrl");
                String imageUrl = resultsArray.getJSONObject(i).getJSONObject("fields").getString("thumbnail");
                String blurb = resultsArray.getJSONObject(i).getJSONObject("fields").getString("trailText");
                Log.d("GAT5", blurb);
                Story story = new Story(headline, webLink, category, imageUrl, blurb);
                list.add(story);
                Log.d("GAT4", String.valueOf(list.size()));
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
