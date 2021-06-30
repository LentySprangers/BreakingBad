package com.example.breakingbad;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CharacterAPITask extends AsyncTask<String, Void, List<Character>> {

    private final String TAG = getClass().getSimpleName();
    private final String JSON_NAME = "name";
    private final String JSON_NICKNAME = "nickname";
    private final String JSON_STATUS = "status";
    private final String JSON_IMG = "img";
    private final String JSON_CHAR_ID = "char_id";

    private CharacterListener listener = null;

    public CharacterAPITask(CharacterListener listener){
        this.listener = listener;
    }

    @Override
    protected List<Character> doInBackground(String... params) {
        Log.d(TAG, "doInBackground was called");
        if (params.length == 0) {
            return null;
        }

        String characterUrlString = params[0];

        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(characterUrlString);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String response = scanner.next();
                Log.d(TAG, "Response: " + response);

                ArrayList<Character> resultList = convertJsonToArrayList(response);
                return resultList;
            } else {
                return null;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Character> characters) {
        super.onPostExecute(characters);

        Log.d(TAG, "in onPostExecute: " + characters.size() + " items.");

        listener.onCharactersAvailable(characters);
    }

    private ArrayList<Character> convertJsonToArrayList(String response) {

        ArrayList<Character> resultList = new ArrayList<Character>();

        try {

            JSONArray characters = new JSONArray(response);
            for (int i = 0; i < characters.length(); i++) {
                JSONObject character = characters.getJSONObject(i);

                String name = character.getString(JSON_NAME);
                String nickname = character.getString(JSON_NICKNAME);
                String status = character.getString(JSON_STATUS);
                String img = character.getString(JSON_IMG);
                int charId = character.getInt(JSON_CHAR_ID);

                resultList.add(new Character(name, nickname, status, img,charId));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Returning " + resultList.size() + " items.");
        return resultList;

    }

    public interface CharacterListener {
        public void onCharactersAvailable(List<Character> characters);
    }
}
