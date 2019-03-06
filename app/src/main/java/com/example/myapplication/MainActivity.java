package com.example.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Button enterBW;
    Button enterdate;
    benchpressDetails BPDB;
    TextView dbView;
    TextView View;

    String[] userWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterBW= (Button) findViewById(R.id.enterBW) ;
        enterdate= (Button) findViewById(R.id.enterdate) ;
        View = (TextView) findViewById(R.id.dbcontent);
        dbView = (TextView) findViewById(R.id.dbcontent);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        BPDB = new benchpressDetails(getApplicationContext());
        showFullDatabase();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Calendar cal = Calendar.getInstance();



                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog =new DatePickerDialog(MainActivity.this, android.R.style.Theme_Material_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();



            }
        });
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
}