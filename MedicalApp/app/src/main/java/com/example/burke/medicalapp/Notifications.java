package com.example.burke.medicalapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by burke on 11/26/2017.
 */

public class Notifications extends Activity{

    NotificationCompat.Builder mBuilder;

    //set ID for notification
    int mNotificationID = 000;

    //Constructor
    private void Notifications(){
        mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("MY NOTIFICATION TITLE");
        mBuilder.setContentText("HELLO WORLD");

        //pending intent
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        mBuilder.setContentIntent(pi);

        //set ID for notification
        //int mNotificationID = 001;

        //get an instance of the notificationManager service
        NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //build notification & issue it
        //  mgr.notify(mNotificationID, mBuilder.build());
    }

    // Change the notification text to the medication name
    private void setNotificationContentText(String medName){
        mBuilder.setContentText("Time to take " + medName);
    }

    // Check day & time. Notify user if time to take med.
    private void timeToNotify(){
        if(currentDay == medDay){
            if(currentTime == medTime){
                //set ID for notification
                mNotificationID = mNotificationID + 1;

                //get an instance of the notificationManager service
                NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                //build notification & issue it
                mgr.notify(mNotificationID, mBuilder.build());
            }
        }
    }

}
