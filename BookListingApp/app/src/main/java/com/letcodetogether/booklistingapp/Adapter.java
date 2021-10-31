package com.letcodetogether.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<BookLayout> implements ListAdapter {

    public Adapter(Context context, List<BookLayout> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_design, parent, false);

        BookLayout currentLayout = getItem(position);
        String Name = currentLayout.getName();

        TextView nameView = (TextView) listItemView.findViewById(R.id.textView);
        nameView.setText(Name);

        return listItemView;
    }
}
