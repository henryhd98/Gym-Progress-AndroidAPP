package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button enterBW;
    Button enterdate;
    benchPressDB BPDB;
    TextView View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterBW= (Button) findViewById(R.id.enterBW) ;
        enterdate= (Button) findViewById(R.id.enterdate) ;
        View = (TextView) findViewById(R.id.dbcontent);
        BPDB = new benchPressDB(getApplicationContext());
        showFullDatabase();
    }
}
    private void showFullDatabase() {
        print(BPDB.getAll());
    }

    private void print(String[] content) {
        dbView.setText("");
        for (int i=0; i<content.length; i++) {
            dbView.append(content[i]+"\n");
        }
    }