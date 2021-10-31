package com.letcodetogether.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class ConnectApi {
    public static final String LOG_TAG = ConnectApi.class.getSimpleName();

    private ConnectApi(){

    }

    private static URL createUrl(String stringUrl)
    {
        URL url = null;
        try {
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG, "Problem Building URL: ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url)throws IOException{
        String jsonResponse = "";
        if (url == null)
            return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e(LOG_TAG, "Error Response Code: " + urlConnection.getResponseCode() );
            }
        }catch (IOException e){
            Log.e(LOG_TAG, "Problem reterving data: ", e);
        }finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (inputStream != null)
                inputStream.close();
        }
        return jsonResponse;
    }

    public static List<BookLayout> fetchBooks(String requestUrl)
    {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("ConnectApi: ", "Error Loading Books Data: ", e);
        }

        return extractBooks(jsonResponse);
    }

    private static List<BookLayout> extractBooks(String sampleJsonResponse) {
        if (sampleJsonResponse == null)
            return null;

        List<BookLayout> list = new ArrayList<>();
        list.add(new BookLayout("ADAD"));
        list.add(new BookLayout("AdffD"));
        list.add(new BookLayout("AdffdfgfgD"));
        list.add(new BookLayout("AdffdfgfgdgdfgD"));
//        try {
////            JSONObject root = new JSONObject(sampleJsonResponse);
////            JSONArray featureArray = root.getJSONArray("features");
////            for(int i=0; i<featureArray.length(); i++)
////            {
////                JSONObject currentEarthquake = featureArray.getJSONObject(i);
////                JSONObject properties = currentEarthquake.getJSONObject("properties");
//////                double mag = properties.getDouble("mag");
////                String location = properties.getString("place");
//////                String url = properties.getString("url");
////
//////                long time = properties.getLong("time");
////
////                BookLayout earthquake = new BookLayout(location);
////
////                list.add(earthquake);
////            }
//
//        } catch (JSONException e) {
//            Log.e("ConnectApi: ", "Problem parsing the Books JSON results", e);
//        }

        return list;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
