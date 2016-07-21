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
 * Created by pauljoiner on 7/19/16.
 */
public class NewYorkTimes {

    public NewYorkTimes(){
    }

    public static String downloadUrl(String url) throws IOException, JSONException {

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
    public static String readInput(InputStream inputStream) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String read;

        while ((read = bufferedReader.readLine()) != null) {
            stringBuilder.append(read);
        }

        return stringBuilder.toString();
    }

    public interface nytNewsListener{
        void onNewsLoaded(ArrayList<Story> stories);
    }

    public static class DownloadUrlTask extends AsyncTask<String, Void, ArrayList<Story>> {

        nytNewsListener newsListener;

        public DownloadUrlTask(nytNewsListener newsListener){
            this.newsListener = newsListener;
        }

        @Override
        protected ArrayList<Story> doInBackground(String... urls) {

            ArrayList<Story> list = null;

            try {
                String json = downloadUrl(urls[0]);
                Log.d("GAT", json);
                list = parseNYT(json);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return list;
        }

        public ArrayList<Story> parseNYT(String jsonString) {

            ArrayList<Story> list = new ArrayList<>();

            try {
                JSONObject newsObject = new JSONObject(jsonString);
                JSONArray resultsArray = newsObject.getJSONArray("results");
                for (int i = 0; i < resultsArray.length(); i++) {
                    String imageUrl = "";
                    String headline = resultsArray.getJSONObject(i).getString("title");
                    String category = resultsArray.getJSONObject(i).getString("section");
                    String webLink = resultsArray.getJSONObject(i).getString("url");
                    JSONArray mediaArray = resultsArray.getJSONObject(i).getJSONArray("multimedia");
                    Log.d("NYT", "Image array size: " + mediaArray.length());
                    if(mediaArray.length() < 5){
                        imageUrl ="http://24.media.tumblr.com/tumblr_m2igzcMtyU1qze0hyo1_500.jpg";
                    }else {
                        imageUrl = mediaArray.getJSONObject(4).getString("url");
                    }
                    Log.d("NYT", "Image url: " + imageUrl);
                    String blurb = resultsArray.getJSONObject(i).getString("abstract");
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
        @Override
        protected void onPostExecute(ArrayList<Story> stories) {
           newsListener.onNewsLoaded(stories);
        }
    }
}
