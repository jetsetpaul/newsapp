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


    public GuardianNews() {
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

    public interface NewsListener{
        void onNewsLoaded(ArrayList<Story> stories);
    }

    public static class DownloadUrlTask extends AsyncTask<String, Void, ArrayList<Story>> {

        NewsListener newsListener;

        public DownloadUrlTask(NewsListener newsListener){
            this.newsListener = newsListener;
        }



        @Override
        protected ArrayList<Story> doInBackground(String... urls) {

            ArrayList<Story> list = null;

            try {
                String json = downloadUrl(urls[0]);
                Log.d("GAT", json);
                list = parseGuardian(json);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
               return list;
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


        @Override
        protected void onPostExecute(ArrayList<Story> stories) {
            newsListener.onNewsLoaded(stories);
        }
    }


}

