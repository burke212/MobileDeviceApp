package com.example.burke.medicalapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class editActivity extends AppCompatActivity {

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
    private TextView TimeText1,TimeText2,TimeText3,TimeText4, Title;
    private Button btnAddData;
    private RadioGroup timeRG, daysRG;
    private String days,time, id;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    Format formatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        id = getIntent().getExtras().getString("id");
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
        Title = (TextView) findViewById(R.id.Title);
        Title.setText("Editing Data for Medicine Entry #: " + id);
        btnAddData = (Button) findViewById(R.id.save);
        btnAddData.setText("Update");


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



        timeRG = (RadioGroup) findViewById(R.id.times);
        daysRG = (RadioGroup) findViewById(R.id.days);
        //set Calendar Seconds to not display.
        dateTime.set(Calendar.SECOND, 00);

        //set onClickListener on Time TV
        TimeText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(editActivity.this, t1, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
            }
        });

        TimeText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(editActivity.this, t2, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
            }
        });
        TimeText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(editActivity.this, t3, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
            }
        });
        TimeText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TimePickerDialog Box
                new TimePickerDialog(editActivity.this, t4, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
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
                    Toast.makeText(editActivity.this, "Reached Limit of Times: " + timeCount, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(editActivity.this, "Reached Limit of Times: " + timeCount, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(editActivity.this, "Reached Limit of Times: " + timeCount, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(editActivity.this, "Reached Limit of Times: " + timeCount, Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(editActivity.this, ViewMed.class);
                startActivity(intent);
            }
        });

        AddData();
        setupDefaultValues();
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
                            Toast.makeText(editActivity.this, "Medicine Name, Dosage, Amount, and Refills are REQUIRED", Toast.LENGTH_LONG).show();
                        } else {
                            if(Double.parseDouble(dosageNum.getText().toString()) <= 0 || Double.parseDouble(bottleNum.getText().toString()) <= 0) {
                                Toast.makeText(editActivity.this, "Dosage and Amount MUST BE > 0", Toast.LENGTH_LONG).show();
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
                                boolean isInserted = myDb.updateData(id,editText.getText().toString(),
                                        dosageNum.getText().toString() + " " + measure1.getSelectedItem().toString(),
                                        bottleNum.getText().toString() + " " + measure2.getSelectedItem().toString(),
                                        editText5.getText().toString(),
                                        time,
                                        days);
                                if (isInserted == true) {
                                    Toast.makeText(editActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(editActivity.this, ViewMed.class);
                                    startActivity(intent);
                                } else
                                    Toast.makeText(editActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    ///////////////////////////////////////////////////////////////////////////////GETTING INFO FROM DATABASE TO SHOW TO USER/////////////////////////////////////////////////////////////////////////////
    private void setupDefaultValues(){
        ////if(myDb.getDataById(id)){

        //}
        Cursor res = myDb.getDataById(id);
        while(res.moveToNext()){
            String gotDosage = res.getString(res.getColumnIndexOrThrow("DOSAGE"));
            String gotAmount = res.getString(res.getColumnIndexOrThrow("AMOUNT"));
            String gotTimes = res.getString(res.getColumnIndexOrThrow("ALARM"));
            String gotDays = res.getString(res.getColumnIndexOrThrow("DAYS"));
            String[] dosage = gotDosage.split("\\s+");
            String[] amount = gotAmount.split("\\s+");
            String[] times = gotTimes.split(",");
            String[] days = gotDays.split(",");
            editText.setText(res.getString(res.getColumnIndexOrThrow("NAME")));
            dosageNum.setText(dosage[0]);
            for(int i = 0; i < measure1.getCount(); i++){
                if(measure1.getItemAtPosition(i).toString().equals(dosage[1])){
                    measure1.setSelection(i);
                    break;
                }
            }
            //editText.setText(dosage[1]);
            bottleNum.setText(amount[0]);
            for(int i = 0; i < measure2.getCount(); i++){
                if(measure2.getItemAtPosition(i).toString().equals(amount[1])){
                    measure2.setSelection(i);
                    break;
                }
            }
            editText5.setText(res.getString(res.getColumnIndexOrThrow("REFILLS")));
            //bottleNum.setText(amount[1]);
            Integer timesaday = times.length;
            Integer numberofdays = days.length;


            if(timesaday == 2){
                timesSpinner.setSelection(1);
            }
            else if(timesaday == 3){
                timesSpinner.setSelection(2);
            }
            else if(timesaday == 4){
                timesSpinner.setSelection(3);
            }else{
                timesSpinner.setSelection(0);
            }
            customCB.performClick();

            if (timesSpinner.getSelectedItem().toString().equals("1")){
                //Log.d(TAG, "on.Custom: gettingIN");
                TimeText1.setText(times[0]);
            }else if(timesSpinner.getSelectedItem().toString().equals("2")){
                TimeText1.setText(times[0]);
                TimeText2.setText(times[1]);
            }else if(timesSpinner.getSelectedItem().toString().equals("3")){
                TimeText1.setText(times[0]);
                TimeText2.setText(times[1]);
                TimeText3.setText(times[2]);
            }else if(timesSpinner.getSelectedItem().toString().equals("4")){
                TimeText1.setText(times[0]);
                TimeText2.setText(times[1]);
                TimeText3.setText(times[2]);
                TimeText4.setText(times[3]);
            }

            if(numberofdays == 7){
                everydayCB.setChecked(true);
            }
            else{
                everydayCB.setChecked(false);
                int count = daysRG.getChildCount();
                for(int i = 0; i < count; i++){
                    final Object child = daysRG.getChildAt(i);
                    if(child instanceof CheckBox){
                        for(String day : days){
                            if (((CheckBox) child).getText().toString().equals(day)){
                                ((CheckBox) child).setChecked(true);
                                ((CheckBox) child).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((CheckBox) child).setChecked(true);
                                    }
                                });
                            }
                        }
                    }
                }
            }

        }
    }

}
