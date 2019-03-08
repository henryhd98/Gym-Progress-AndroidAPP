package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class listDataActivity extends AppCompatActivity {
     private static final String TAG ="ListDataActivity";
    benchpressDetails BPDB;
    private ListView listView;



    @Override
     protected  void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_layout);
        listView=(ListView) findViewById(R.id.listView);
        BPDB = new benchpressDetails(this);
        populateListView();

    }

    private void populateListView(){
        Log.d(TAG, "populatinf listview: displaying data in list view");
        //get data and append it to list
        Cursor data = BPDB.getData();
        ArrayList<String> listData = new ArrayList<>();

        while(data.moveToNext()){

            //get values from data base in colum 1
            //then add to arraylist

            listData.add(data.getString(0));

            listData.add(data.getString(1);
        }

        //list adapter and setting adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
    }



}
