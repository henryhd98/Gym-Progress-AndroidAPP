package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

            listData.add(data.getString(1));

            listData.add(data.getString(2));
        }

        //list adapter and setting adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String date = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + date);

                Cursor data = BPDB.getItemID(date); //get the id associated with that name
                int weightInputID = -1;
                while(data.moveToNext()){
                    weightInputID = data.getInt(0);
                }
                if(weightInputID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + weightInputID);
                    Intent editScreenIntent = new Intent(listDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("WEIGHT",weightInputID);
                    editScreenIntent.putExtra("DATE",date);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }





}
