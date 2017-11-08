package com.example.burke.medicalapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private EditText dosageNum;
    private Spinner measure1;
    private EditText bottleNum;
    private Spinner measure2;
    private EditText editText5;
    private Spinner timesSpinner;
    private RadioGroup times;
    private CheckBox morningCB;
    private CheckBox noonCB;
    private CheckBox eveningCB;
    private CheckBox nightCB;
    private CheckBox customCB;
    //private RadioGroup days;
    private CheckBox everydayCB;
    private CheckBox mondayCB;
    private CheckBox tuesdayCB;
    private CheckBox wednesdayCB;
    private CheckBox thursdayCB;
    private CheckBox fridayCB;
    private CheckBox saturdayCB;
    private CheckBox sundayCB;
    private Button button;
    private Button save;
    SQLiteHelper myDb;
    private TextView TimeText;
    private Button btnAddData;
    private RadioGroup timeRG, daysRG;
    private String days,time;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    Format formatter;

//TODO dynamically set checkbox limit or # of time boxes based on Spinner(Times A Day)
//TODO dynamically check all if everyday is checked if unchecked uncheck all
//TODO Add individual medication to SharedPref using delimiter ";",
// set ADD condition that no strings have ";" in them multiple values ",".
// TODO edit list_medicine_single.xml to have valuable information to user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //testing layout of add.xml. Uncomment line beneath to load activity main first
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.add);
        myDb = new SQLiteHelper(this);

        editText = (EditText)findViewById( R.id.editText );
        dosageNum = (EditText)findViewById( R.id.dosage_num );
        measure1 = (Spinner)findViewById( R.id.measure1 );
        bottleNum = (EditText)findViewById( R.id.bottle_num );
        measure2 = (Spinner)findViewById( R.id.measure2 );
        editText5 = (EditText)findViewById( R.id.editText5 );
        timesSpinner = (Spinner)findViewById( R.id.timesSpinner );
        times = (RadioGroup)findViewById( R.id.times );
        morningCB = (CheckBox)findViewById( R.id.morningCB );
        noonCB = (CheckBox)findViewById( R.id.noonCB );
        eveningCB = (CheckBox)findViewById( R.id.eveningCB );
        nightCB = (CheckBox)findViewById( R.id.nightCB );
        customCB = (CheckBox)findViewById( R.id.customCB );
        //days = (RadioGroup)findViewById( R.id.days );
        everydayCB = (CheckBox)findViewById( R.id.everydayCB );
        mondayCB = (CheckBox)findViewById( R.id.mondayCB );
        tuesdayCB = (CheckBox)findViewById( R.id.tuesdayCB );
        wednesdayCB = (CheckBox)findViewById( R.id.wednesdayCB );
        thursdayCB = (CheckBox)findViewById( R.id.thursdayCB );
        fridayCB = (CheckBox)findViewById( R.id.fridayCB );
        saturdayCB = (CheckBox)findViewById( R.id.saturdayCB );
        sundayCB = (CheckBox)findViewById( R.id.sundayCB );
        button = (Button)findViewById( R.id.button );
        save = (Button)findViewById( R.id.save );

        //get views
        TimeText = (TextView) findViewById(R.id.Time);
        btnAddData = (Button) findViewById(R.id.save);

        timeRG = (RadioGroup) findViewById(R.id.times);
        daysRG = (RadioGroup) findViewById(R.id.days);
        //set Calendar Seconds to not display.
        dateTime.set(Calendar.SECOND, 00);

        //set onClickListener on Time TV
        TimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(MainActivity.this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
            }
        });

        timeRG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customCB.isChecked()){

                }
            }
        });

        AddData();
    }

    //function to set time
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            dateTime.set(Calendar.SECOND, 00);
            //updateTextLabel();
            //new formatter to get hours:minute and AM_PM and SET TimeTV to the selected time.
            formatter = new SimpleDateFormat("hh:mm a");
            TimeText.setText(formatter.format(dateTime.getTime()));
        }
    };
//insertData(String name,String dosage,String amtbtl,String numrefills,String timesaday,String alarm,String day)
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(customCB.isChecked()){
                            time = "6:00 AM,12:00 PM,6:00 PM,12:00 AM";
                        }
                        else{
                            if(morningCB.isChecked()){
                                time = time + ",6:00 AM";
                                //time.replaceFirst("^,", "")
                            }
                            if(noonCB.isChecked()){
                                time = time + ",12:00 PM";
                            }
                            if(eveningCB.isChecked()){
                                time = time + ",6:00 PM";
                            }
                            if(nightCB.isChecked()){
                                time = time + ",12:00 AM";
                            }
                            time = time.replaceFirst("^,", "");
                        }
                        if(everydayCB.isChecked()){
                            days = "Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday";
                        }
                        else{
                            if(mondayCB.isChecked()){
                                days = days +",Monday";
                            }
                            if(tuesdayCB.isChecked()){
                                days = days +",Tuesday";
                            }
                            if(wednesdayCB.isChecked()){
                                days = days +",Wednesday";
                            }
                            if(thursdayCB.isChecked()){
                                days = days +",Thursday";
                            }
                            if(fridayCB.isChecked()){
                                days = days +",Friday";
                            }
                            if(saturdayCB.isChecked()){
                                days = days +",Saturday";
                            }
                            if(sundayCB.isChecked()){
                                days = days +",Sunday";
                            }
                           days = days.replaceFirst("^,", "");
                        }
                        boolean isInserted = myDb.insertData(editText.getText().toString(),
                                dosageNum.getText().toString() + " " + measure1.getSelectedItem().toString(),
                                bottleNum.getText().toString() + " " + measure2.getSelectedItem().toString(),
                                editText5.getText().toString(),
                                time,
                                days);
                        if(isInserted == true){
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, TestView.class);
                            startActivity(intent);}
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
