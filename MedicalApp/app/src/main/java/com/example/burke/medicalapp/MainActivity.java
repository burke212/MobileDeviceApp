package com.example.burke.medicalapp;

import android.app.TimePickerDialog;
import android.content.Intent;
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
    private Button view,add;

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
        setContentView(R.layout.activity_main);

        view = (Button)findViewById( R.id.view );
        add = (Button)findViewById( R.id.add );

view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, ViewMed.class);
        startActivity(intent);
    }
});

add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
});
        //set onClickListener on Time TV

}}
