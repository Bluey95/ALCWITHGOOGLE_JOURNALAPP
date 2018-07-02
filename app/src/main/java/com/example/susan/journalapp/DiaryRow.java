/*
package com.example.susan.journalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiaryRow extends ArrayAdapter<String> {
    private List<String> list;
    private Context context;

    TextView removeMeal;

    public DiaryRow (Context context, List<String> myButton) {
        super(context, 0, myButton);
        this.list = myButton;
        this.context = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.diary_row,parent,false
            );
        }

        final String currentString = getItem(position);

        removeMeal = (TextView)listItemView.findViewById(R.id.delete_item);


        //OnClick listeners for all the buttons on the ListView Item


        removeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return listItemView;
    }

}*/
