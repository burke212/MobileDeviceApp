package com.example.burke.medicalapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by burke on 11/26/2017.
 */

public class Notification extends Activity{

    NotificationCompat.Builder mBuilder;

    //set ID for notification
    int mNotificationID = 000;
    String currentDay, currentTime, days, medication;

    //Constructor
    private void Notifications(){
        mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("MY NOTIFICATION TITLE");
        mBuilder.setContentText("HELLO WORLD");

//        //pending intent
//        PendingIntent pi = PendingIntent.getActivity(this, 0,
//                new Intent(this, ViewMed.class), PendingIntent.FLAG_UPDATE_CURRENT);
//
//        mBuilder.setContentIntent(pi);

        //set ID for notification
        //int mNotificationID = 001;

        //get an instance of the notificationManager service
        NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //build notification & issue it
        //  mgr.notify(mNotificationID, mBuilder.build());

//        timeToNotify();
    }

    // Change the notification text to the medication name
    private void setNotificationContentText(String medName){
        mBuilder.setContentText("Time to take " + medName);
    }

    private void getCurrentDay(){
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        currentDay = dayFormat.format(calendar.getTime());
    }

    private void getCurrentTime(){
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        currentTime = dayFormat.format(calendar.getTime());

    }

    // Check day & time. Notify user if time to take med.

    public void timeToNotify(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        getCurrentTime();
        Cursor info = sqLiteHelper.getDataByTime(currentTime);
        getCurrentDay();
        while (info.moveToNext()) {
            days = info.getString(info.getColumnIndexOrThrow("DAYS"));
            medication = info.getString(info.getColumnIndexOrThrow("NAME"));
            List<String> medDays = Arrays.asList(days.split(","));
            for (String medDay : medDays){
                if(currentDay == medDay){
                    //pending intent
                    PendingIntent pi = PendingIntent.getActivity(this, 0,
                            new Intent(this, ViewMed.class), PendingIntent.FLAG_UPDATE_CURRENT);

                    mBuilder.setContentIntent(pi);

                    mNotificationID = mNotificationID + 1;

                    //get an instance of the notificationManager service
                    NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


                    mBuilder.setContentTitle("Reminder");
                    mBuilder.setContentText("It's time to take "+medication);

                    //build notification & issue it
                    mgr.notify(mNotificationID, mBuilder.build());
                }
            }

        }
    }

}
