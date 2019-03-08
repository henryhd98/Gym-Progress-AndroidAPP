package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText editText;
    Button enterBW;

    benchpressDetails BPDB;
    Button dbView;

    String[] dbContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterBW= (Button) findViewById(R.id.enterBW) ;
        editText=(EditText) findViewById(R.id.editText);
        dbView = (Button) findViewById(R.id.dbView);
        mDisplayDate = (Button) findViewById(R.id.tvDate);
        BPDB = new benchpressDetails(getApplicationContext());






        enterBW.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                String newEntry = editText.getText().toString();

                if (editText.length() !=0){
                    AddRow(newEntry);
                    editText.setText("");

                }else{
                        toastMessage("you must enter something!");

                }
            }


        });

        dbView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listDataActivity.class);
                startActivity(intent);

            }
                                  });

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

               /* month=month +1;
                Log.d(TAG,"onDateSet: mm/dd/yyyy:" +month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year ;

                mDisplayDate.setText(date);
                */
                Intent intent = new Intent(MainActivity.this, listDataActivity.class);
                startActivity(intent);

            }
        };
    }


    public void AddRow(String newEntry){
        boolean insertData = BPDB.addRow(newEntry);

        if(insertData) {
            toastMessage("Data successfully inserted");
        }else{
            toastMessage("Something went wrong");
        }
    }

private void toastMessage(String message){

    Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
}


}