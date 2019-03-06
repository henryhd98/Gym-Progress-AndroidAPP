package com.example.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Button enterBW;

    benchpressDetails BPDB;
    TextView dbView;
    TextView View;

    String[] userWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterBW= (Button) findViewById(R.id.enterBW) ;

        View = (TextView) findViewById(R.id.dbcontent);
        dbView = (TextView) findViewById(R.id.dbcontent);
        mDisplayDate = (Button) findViewById(R.id.tvDate);
        BPDB = new benchpressDetails(getApplicationContext());
        showFullDatabase();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();



                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog =new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();



            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month +1;
                Log.d(TAG,"onDateSet: mm/dd/yyyy:" +month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year ;
                mDisplayDate.setText(date);
            }
        };
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