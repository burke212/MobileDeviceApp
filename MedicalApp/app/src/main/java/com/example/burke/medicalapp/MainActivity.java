package com.example.burke.medicalapp;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView TimeText;
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

        //get views
        TimeText = (TextView) findViewById(R.id.Time);

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
}
