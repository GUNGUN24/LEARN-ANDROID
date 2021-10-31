package com.letcodetogether.booklistingapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Adapter mAdapter;
//    private final String mUrl = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";
    private final String mUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<BookLayout> list = new ArrayList<>();
//        list.add(new BookLayout("HELLO WORLD"));
//        list.add(new BookLayout("TEST 123"));
        ListView listView = (ListView) findViewById(R.id.list);
        Log.d("Main: ", "Set Adapter");

        if(mUrl != null)
            list = ConnectApi.fetchBooks(mUrl);

        mAdapter = new Adapter(this, list);
        listView.setAdapter(mAdapter);

    }

}