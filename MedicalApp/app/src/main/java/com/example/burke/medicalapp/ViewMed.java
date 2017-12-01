package com.example.burke.medicalapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class ViewMed extends AppCompatActivity {
    SQLiteHelper myDb;
    CustomAdapter adapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_med);
        myDb = new SQLiteHelper(this);

        Cursor res = myDb.getAllData();
        ListView list = (ListView) findViewById(R.id.list);
        Button add = (Button) findViewById(R.id.add);
        Button back = (Button) findViewById(R.id.back);
// Setup cursor adapter using cursor from last step
        adapter = new CustomAdapter(this, res);
// Attach cursor adapter to the ListView
        list.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ViewMed.this,"Data Inserted",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewMed.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ViewMed.this,"Data Inserted",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewMed.this, AddActivity.class);
                startActivity(intent);
            }
        });

//        for(int i =0; i<count){
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//    }
        preferences = getApplication().getSharedPreferences("shared", MODE_PRIVATE);
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        int count = preferences.getInt("pendingLength", 0);
        Log.e(TAG, String.valueOf(count));


        int i = 0;
        int d = 0;
        //This will then trigger the CursorAdapter iterating through the result set and populating the list. We can change the cursor to update the adapter at any time with:
        while(res.moveToNext()){
            String[] Times = res.getString(res.getColumnIndexOrThrow("ALARM")).split(",");
            String[] Days = res.getString(res.getColumnIndexOrThrow("DAYS")).split(",");
            String medName = res.getString(res.getColumnIndexOrThrow("NAME"));
            String dosage = res.getString(res.getColumnIndexOrThrow("DOSAGE"));
            String amount = res.getString(res.getColumnIndexOrThrow("AMOUNT"));
            String id = res.getString(res.getColumnIndexOrThrow("_id"));


            //ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
            AlarmManager mgrAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);


            for (String Day : Days){
//                boolean day = Day == "Wednesday";
//                String tf;
//                if (day){
//                    tf = "t";
//                }else{
//                    tf = "f";
//                }
//                Log.e(TAG, tf);


                if(Day.equals("Sunday")){
                    d = 1;
                }
                else if(Day.equals("Monday")){
                    d = 2;
                }else if(Day.equals("Tuesday")){
                    d = 3;
                }
                else if(Day.equals("Wednesday")){
                    d = 4;
                }
                else if(Day.equals("Thursday")){
                    d = 5;
                }else if(Day.equals("Friday")){
                    d = 6;
                }
                else if(Day.equals("Saturday")){
                    d = 7;
                }
                for (String Time: Times) {
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); // this is you format

                    Calendar cal = Calendar. getInstance();
                    Calendar rightNow = (Calendar) cal.clone();
                    Calendar todayZERO = (Calendar) cal.clone();
                    todayZERO.set(Calendar.HOUR_OF_DAY, 0);
                    todayZERO.set(Calendar.MINUTE, 0);
                    todayZERO.set(Calendar.SECOND, 0);
                    todayZERO.set(Calendar.MILLISECOND, 0);
                    Date date = cal.getTime();

                    try{
                        date = sdf.parse(Time); // this string getting you from your timepicker
                        cal.setTime(date);
                    }catch (ParseException e){

                    }
                    boolean test;
                    if(test = rightNow.getTimeInMillis() > (todayZERO.getTimeInMillis()+(cal.getTimeInMillis()+cal.getTimeZone().getOffset(cal.getTimeInMillis())))){
                        cal.add(Calendar.DATE, 1);
                    }
                    //Log.e(TAG, "day: " + i);

                    Intent myIntent = new Intent(this, AlarmService.class);
                    myIntent.putExtra("WEEKDAY", d);
                    myIntent.putExtra("NAME", medName);
                    myIntent.putExtra("DOSAGE", dosage);
                    myIntent.putExtra("AMOUNT", amount);
                    myIntent.putExtra("TIME", Time);
                    myIntent.putExtra("ID", id);//passing ID
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    mgrAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, (todayZERO.getTimeInMillis()+(cal.getTimeInMillis()+cal.getTimeZone().getOffset(cal.getTimeInMillis()))),AlarmManager.INTERVAL_DAY, pendingIntent);
                    Log.e(TAG, "day: " +  (todayZERO.getTimeInMillis()+(cal.getTimeInMillis()+cal.getTimeZone().getOffset(cal.getTimeInMillis()))) + "CURRENT" + cal.getTime() + "test " + test );
                    Log.e(TAG, Time + i);

                    //how we're setting the alarm
                    //Today 0 hours 0 mins 0 seconds 0 millis + The Set Time AM/PM just the time in milis no date
                    intentArray.add(pendingIntent);
                    i++;
                    //i sets new pending activity as not to overwrite
                    //i increments
                    //add pendingintent to intentArray
                    //putExtra for weekday
                    //in OnReceive do int value=intent.getIntExtra("WEEKDAY", 0);
                }
            }


        }

        editor = preferences.edit();
        editor.putInt("pendingLength",intentArray.size());
        editor.commit();


// Switch to new cursor and update contents of ListView
        //adapter.changeCursor(todoCursor);
    }

    public void update(String id){
        Intent intent = new Intent(ViewMed.this, editActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public void remove(String id){
        myDb.deleteData(id);
        Cursor res = myDb.getAllData();
        adapter.changeCursor(res);
    }
}
