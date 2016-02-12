package com.englishnary.eridev.android.englishnary;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

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
import java.util.List;

/**
 * Created by eridev on 10/02/16.
 */

  public class FetchDefinitionTask extends AsyncTask<String, Void, List<Definitions>> {

        private final String LOG_TAG = FetchDefinitionTask.class.getSimpleName();
        private ArrayAdapter<String> mDefAdapter;

        private DefinitionsAdapter mDefinitionsAdapter;
        private Context mContext;

        public FetchDefinitionTask(Context context, DefinitionsAdapter definitionsAdapter){
            mDefinitionsAdapter = definitionsAdapter;
            mContext = context;
        }

        @Override
        protected List<Definitions> doInBackground(String... params){

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String definitionsJsonStr = null;


            try {
                // Construct the URL for the Dictionary query
                // Possible parameters are avaiable at OWM's dictionary API page,
                // at https://market.mashape.com/montanaflynn/dictionary



                String baseUrl = "https://montanaflynn-dictionary.p.mashape.com/define?word=irony";
                String apiKey = "&APPID=" + BuildConfig.X_MASHAPE_KEY;
                URL url = new URL(baseUrl.concat(apiKey));

                Log.v(LOG_TAG, "The Url is" + url.toString());

                // Create the request to Api and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                //Antes de hacer el conect hay que usar el setRequestProperty para el header
                urlConnection.setRequestProperty("X-Mashape-Key", "5WNRUjRRXgmshGBMXphs9Jjn9RK0p1hBRh0jsnoyP9sv5cJl4H");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                definitionsJsonStr = buffer.toString();
                // Log.v(LOG_TAG, "definitions Json Str" + definitionsJsonStr);

                Log.v(LOG_TAG, "definitions Json Str" + definitionsJsonStr);
//                    Log.i(LOG_TAG, definitionsJsonStr);
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }

            try {
                return getDefinitionsFromJSON(definitionsJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        private List<Definitions> getDefinitionsFromJSON(String definitionsJsonStr) throws JSONException {
            JSONObject json = new JSONObject(definitionsJsonStr);
            JSONArray jArray = json.getJSONArray("definitions");
            List<Definitions> definitionsData = new ArrayList<>();

            for(int i=0; i<jArray.length(); i++){
                JSONObject json_data = null;
                try {
                    json_data = jArray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String text = json_data.optString("text");
                String description = json_data.optString("attribution");


                definitionsData.add(new Definitions(text, description));

            }

            return definitionsData;
        }

        @Override
        protected void onPostExecute (List < Definitions > definitionsData) {
            if (mDefinitionsAdapter != null) {
                mDefinitionsAdapter.clear();
                mDefinitionsAdapter.setData(definitionsData);
                mDefinitionsAdapter.notifyDataSetChanged();
            } else {
                mDefinitionsAdapter = new DefinitionsAdapter(mContext, definitionsData);
            }
        }

    }

