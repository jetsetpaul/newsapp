package samcorp.newsapp;

import android.os.AsyncTask;
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
 * Created by samwyz on 7/18/16.
 */
public class GuardianNews {

    News news;
    String query;
    private final String GUARDIAN_URL =
            "http://content.guardianapis.com/search?order-by=newest&show-elements=video&q="
                    +query+"&limit=10&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";
    private final String GUARDIAN_SECTIONS =
            "http://content.guardianapis.com/search?order-by=newest&section="
                    +query+"&limit=10&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";



    public GuardianNews() {
    }

    public ArrayList<Story> guardianNews(String query){

        new DownloadUrlTask().execute(GUARDIAN_SECTIONS);

    }


    private String downloadUrl(String url) throws IOException, JSONException {

        InputStream inputStream = null;

        try {

            URL nativeUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) nativeUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();
            inputStream = connection.getInputStream();

            return readInput(inputStream);

        } finally {

            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private String readInput(InputStream inputStream) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String read;

        while((read = bufferedReader.readLine()) != null) {
            stringBuilder.append(read);
        }

        return stringBuilder.toString();
    }

    private class DownloadUrlTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {

            try {
                String json = downloadUrl(urls[0]);
                Log.d("GAT", json);
                parseGuardian(json);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public ArrayList<Story> parseGuardian(String jsonString){

        news = News.getInstance();

        try {
            JSONObject newsObject = new JSONObject(jsonString);
            JSONObject responseObject = newsObject.getJSONObject("response");
            JSONArray resultsArray = responseObject.getJSONArray("results");
            Log.d("GAT2", resultsArray.toString());
            for (int i = 0; i < resultsArray.length(); i++){
                String headline = resultsArray.getJSONObject(i).getString("webTitle");
                String category = resultsArray.getJSONObject(i).getString("sectionName");
                String webLink = resultsArray.getJSONObject(i).getString("webUrl");
                String source = "The Guardian";
                Log.d("GAT3", category);
                Story story = new Story(headline, webLink, R.mipmap.ic_launcher, category, source);
                news.addStory(story);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
