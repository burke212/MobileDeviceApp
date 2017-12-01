package com.example.burke.medicalapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by Juwan on 11/7/2017.
 */

public class AddActivity extends AppCompatActivity {
    Notification notification = new Notification();

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
    private int timeCount = 0;
    SQLiteHelper myDb;
    private TextView TimeText1,TimeText2,TimeText3,TimeText4;
    private Button btnAddData;
    private RadioGroup timeRG, daysRG;
    private String days,time;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    Format formatter;

    //TODO NEED TO ADD ALARMS/NOTIFICATIONS
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

        time = "";
        days = "";
        //get views
        TimeText1 = (TextView) findViewById(R.id.Time1);
        TimeText1.setVisibility(View.GONE);
        TimeText2 = (TextView) findViewById(R.id.Time2);
        TimeText2.setVisibility(View.GONE);
        TimeText3 = (TextView) findViewById(R.id.Time3);
        TimeText3.setVisibility(View.GONE);
        TimeText4 = (TextView) findViewById(R.id.Time4);
        TimeText4.setVisibility(View.GONE);
        btnAddData = (Button) findViewById(R.id.save);

        timeRG = (RadioGroup) findViewById(R.id.times);
        daysRG = (RadioGroup) findViewById(R.id.days);
        //set Calendar Seconds to not display.
        dateTime.set(Calendar.SECOND, 00);

        //set onClickListener on Time TV
        TimeText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(AddActivity.this, t1, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
            }
        });

        TimeText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(AddActivity.this, t2, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
            }
        });
        TimeText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(AddActivity.this, t3, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
            }
        });
        TimeText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(AddActivity.this, t4, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
            }
        });


        timesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(customCB.isChecked()){
                    if(position == 0){
                        TimeText1.setVisibility(View.VISIBLE);
                        TimeText2.setVisibility(View.GONE);
                        TimeText3.setVisibility(View.GONE);
                        TimeText4.setVisibility(View.GONE);
                    }
                    else if(position == 1){
                        TimeText1.setVisibility(View.VISIBLE);
                        TimeText2.setVisibility(View.VISIBLE);
                        TimeText3.setVisibility(View.GONE);
                        TimeText4.setVisibility(View.GONE);
                    }
                    else if(position == 2){
                        TimeText1.setVisibility(View.VISIBLE);
                        TimeText2.setVisibility(View.VISIBLE);
                        TimeText3.setVisibility(View.VISIBLE);
                        TimeText4.setVisibility(View.GONE);
                    }
                    else if(position == 3){
                        TimeText1.setVisibility(View.VISIBLE);
                        TimeText2.setVisibility(View.VISIBLE);
                        TimeText3.setVisibility(View.VISIBLE);
                        TimeText4.setVisibility(View.VISIBLE);
                    }

                }
                else{
                            customCB.performClick();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                timesSpinner.setSelection(0);
            }
        });
        customCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customCB.isChecked()){
                    timeCount = 0;
                    morningCB.setChecked(false);
                    noonCB.setChecked(false);
                    eveningCB.setChecked(false);
                    nightCB.setChecked(false);
                    if (timesSpinner.getSelectedItem().toString().equals("1")){
                        Log.d(TAG, "on.Custom: gettingIN");
                        TimeText1.setVisibility(View.VISIBLE);
                        TimeText2.setVisibility(View.GONE);
                        TimeText3.setVisibility(View.GONE);
                        TimeText4.setVisibility(View.GONE);
                    }else if(timesSpinner.getSelectedItem().toString().equals("2")){
                        TimeText1.setVisibility(View.VISIBLE);
                        TimeText2.setVisibility(View.VISIBLE);
                        TimeText3.setVisibility(View.GONE);
                        TimeText4.setVisibility(View.GONE);
                    }else if(timesSpinner.getSelectedItem().toString().equals("3")){
                        TimeText1.setVisibility(View.VISIBLE);
                        TimeText2.setVisibility(View.VISIBLE);
                        TimeText3.setVisibility(View.VISIBLE);
                        TimeText4.setVisibility(View.GONE);
                    }else if(timesSpinner.getSelectedItem().toString().equals("4")){
                        TimeText1.setVisibility(View.VISIBLE);
                        TimeText2.setVisibility(View.VISIBLE);
                        TimeText3.setVisibility(View.VISIBLE);
                        TimeText4.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    TimeText1.setVisibility(View.GONE);
                    TimeText2.setVisibility(View.GONE);
                    TimeText3.setVisibility(View.GONE);
                    TimeText4.setVisibility(View.GONE);
                    customCB.setChecked(false);
                }
            }
        });

        morningCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(morningCB.isChecked()&& timeCount < Integer.parseInt(timesSpinner.getSelectedItem().toString())){
                    TimeText1.setVisibility(View.GONE);
                    TimeText2.setVisibility(View.GONE);
                    TimeText3.setVisibility(View.GONE);
                    TimeText4.setVisibility(View.GONE);
                    timeCount++;
                    customCB.setChecked(false);
                }else if(morningCB.isChecked() && timeCount >= Integer.parseInt(timesSpinner.getSelectedItem().toString())){
                    Toast.makeText(AddActivity.this, "Reached Limit of Times: " + timeCount, Toast.LENGTH_LONG).show();
                    morningCB.setChecked(false);
                }
                else{
                    timeCount--;
                }
            }
        });
        noonCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noonCB.isChecked() && timeCount < Integer.parseInt(timesSpinner.getSelectedItem().toString())){
                    TimeText1.setVisibility(View.GONE);
                    TimeText2.setVisibility(View.GONE);
                    TimeText3.setVisibility(View.GONE);
                    TimeText4.setVisibility(View.GONE);
                    timeCount++;
                    customCB.setChecked(false);

            }else if(noonCB.isChecked() && timeCount >= Integer.parseInt(timesSpinner.getSelectedItem().toString())){
                    Toast.makeText(AddActivity.this, "Reached Limit of Times: " + timeCount, Toast.LENGTH_LONG).show();
                    noonCB.setChecked(false);
            }
                else{
                timeCount--;
            }
            }
        });
        eveningCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eveningCB.isChecked() && timeCount < Integer.parseInt(timesSpinner.getSelectedItem().toString())){
                    TimeText1.setVisibility(View.GONE);
                    TimeText2.setVisibility(View.GONE);
                    TimeText3.setVisibility(View.GONE);
                    TimeText4.setVisibility(View.GONE);
                    timeCount++;
                    customCB.setChecked(false);
                }else if(eveningCB.isChecked() && timeCount >= Integer.parseInt(timesSpinner.getSelectedItem().toString())){
                    Toast.makeText(AddActivity.this, "Reached Limit of Times: " + timeCount, Toast.LENGTH_LONG).show();
                    eveningCB.setChecked(false);
                }
                else{
                    timeCount--;
                }
            }
        });
        nightCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nightCB.isChecked()&& timeCount < Integer.parseInt(timesSpinner.getSelectedItem().toString())){
                    TimeText1.setVisibility(View.GONE);
                    TimeText2.setVisibility(View.GONE);
                    TimeText3.setVisibility(View.GONE);
                    TimeText4.setVisibility(View.GONE);
                    timeCount++;
                    customCB.setChecked(false);
                }else if(nightCB.isChecked() && timeCount >= Integer.parseInt(timesSpinner.getSelectedItem().toString())){
                    Toast.makeText(AddActivity.this, "Reached Limit of Times: " + timeCount, Toast.LENGTH_LONG).show();
                    nightCB.setChecked(false);
                }
                else{
                    timeCount--;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, ViewMed.class);
                startActivity(intent);
            }
        });

        AddData();
    }

    //function to set time
    TimePickerDialog.OnTimeSetListener t1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            dateTime.set(Calendar.SECOND, 00);
            //updateTextLabel();
            //new formatter to get hours:minute and AM_PM and SET TimeTV to the selected time.
            formatter = new SimpleDateFormat("hh:mm a");
            TimeText1.setText(formatter.format(dateTime.getTime()));
        }
    };
    TimePickerDialog.OnTimeSetListener t2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            dateTime.set(Calendar.SECOND, 00);
            //updateTextLabel();
            //new formatter to get hours:minute and AM_PM and SET TimeTV to the selected time.
            formatter = new SimpleDateFormat("hh:mm a");
            TimeText2.setText(formatter.format(dateTime.getTime()));
        }
    };
    TimePickerDialog.OnTimeSetListener t3 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            dateTime.set(Calendar.SECOND, 00);
            //updateTextLabel();
            //new formatter to get hours:minute and AM_PM and SET TimeTV to the selected time.
            formatter = new SimpleDateFormat("hh:mm a");
            TimeText3.setText(formatter.format(dateTime.getTime()));
        }
    };
    TimePickerDialog.OnTimeSetListener t4 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            dateTime.set(Calendar.SECOND, 00);
            //updateTextLabel();
            //new formatter to get hours:minute and AM_PM and SET TimeTV to the selected time.
            formatter = new SimpleDateFormat("hh:mm a");
            TimeText4.setText(formatter.format(dateTime.getTime()));
        }
    };
    //insertData(String name,String dosage,String amtbtl,String numrefills,String timesaday,String alarm,String day)
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString() == null || dosageNum.getText().toString() == null || bottleNum.getText().toString() == null || editText5.getText().toString() == null || editText.getText().toString().isEmpty() || dosageNum.getText().toString().isEmpty() || bottleNum.getText().toString().isEmpty() || editText5.getText().toString().isEmpty()) {
                            Toast.makeText(AddActivity.this, "Medicine Name, Dosage, Amount, and Refills are REQUIRED", Toast.LENGTH_LONG).show();
                        } else {
                            if(Double.parseDouble(dosageNum.getText().toString()) <= 0 || Double.parseDouble(bottleNum.getText().toString()) <= 0) {
                                Toast.makeText(AddActivity.this, "Dosage and Amount MUST BE > 0", Toast.LENGTH_LONG).show();
                            }
                                else{
                                if (customCB.isChecked()) {
                                    if (timesSpinner.getSelectedItem().toString().equals("1")) {
                                        time = "," + TimeText1.getText().toString();
                                    } else if (timesSpinner.getSelectedItem().toString().equals("2")) {
                                        time = "," + TimeText1.getText().toString();
                                        time = time + "," + TimeText2.getText().toString();
                                    } else if (timesSpinner.getSelectedItem().toString().equals("3")) {
                                        time = "," + TimeText1.getText().toString();
                                        time = time + "," + TimeText2.getText().toString();
                                        time = time + "," + TimeText3.getText().toString();
                                    } else if (timesSpinner.getSelectedItem().toString().equals("4")) {
                                        time = "," + TimeText1.getText().toString();
                                        time = time + "," + TimeText2.getText().toString();
                                        time = time + "," + TimeText3.getText().toString();
                                        time = time + "," + TimeText4.getText().toString();
                                    }
                                    time = time.replaceFirst("^,", "");
                                } else {
                                    if (morningCB.isChecked()) {
                                        time = time + ",6:00 AM";
                                        //time.replaceFirst("^,", "")
                                    }
                                    if (noonCB.isChecked()) {
                                        time = time + ",12:00 PM";
                                    }
                                    if (eveningCB.isChecked()) {
                                        time = time + ",6:00 PM";
                                    }
                                    if (nightCB.isChecked()) {
                                        time = time + ",12:00 AM";
                                    }
                                    time = time.replaceFirst("^,", "");
                                }
                                if (everydayCB.isChecked()) {
                                    days = "Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday";
                                } else {
                                    if (mondayCB.isChecked()) {
                                        days = days + ",Monday";
                                    }
                                    if (tuesdayCB.isChecked()) {
                                        days = days + ",Tuesday";
                                    }
                                    if (wednesdayCB.isChecked()) {
                                        days = days + ",Wednesday";
                                    }
                                    if (thursdayCB.isChecked()) {
                                        days = days + ",Thursday";
                                    }
                                    if (fridayCB.isChecked()) {
                                        days = days + ",Friday";
                                    }
                                    if (saturdayCB.isChecked()) {
                                        days = days + ",Saturday";
                                    }
                                    if (sundayCB.isChecked()) {
                                        days = days + ",Sunday";
                                    }
                                    days = days.replaceFirst("^,", "");
                                }
                                boolean isInserted = myDb.insertData(editText.getText().toString(),
                                        dosageNum.getText().toString() + " " + measure1.getSelectedItem().toString(),
                                        bottleNum.getText().toString() + " " + measure2.getSelectedItem().toString(),
                                        editText5.getText().toString(),
                                        time,
                                        days);
                                if (isInserted == true) {
                                    Toast.makeText(AddActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(AddActivity.this, ViewMed.class);
                                    startActivity(intent);
//                                    notification.timeToNotify();
                                } else
                                    Toast.makeText(AddActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

}
