package com.example.burke.medicalapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Juwan on 11/29/2017.
 */

public class AlarmService extends BroadcastReceiver {
    NotificationCompat.Builder mBuilder,mBuilderREFILL;
    SQLiteHelper myDb;

    //set ID for notification
    int mNotificationID = 000;
    int dosage;
    int medicine;
    SimpleDateFormat formatter;
    @Override
    public void onReceive(Context context, Intent intent) {
        mBuilder = new NotificationCompat.Builder(context);
        mBuilderREFILL = new NotificationCompat.Builder(context);
        //Log.e(TAG, "getting in onReceive");

        String dosage = intent.getStringExtra("DOSAGE");
        int weekday = intent.getIntExtra("WEEKDAY", 0);
        String name = intent.getStringExtra("NAME");
        String time = intent.getStringExtra("TIME");
        String amount = intent.getStringExtra("AMOUNT");
        String idDB = intent.getStringExtra("ID");
        //String time = intent.getStringExtra("TIME");
        formatter = new SimpleDateFormat("hh:mm a");

        Calendar cal = Calendar. getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        String currentTime = formatter.format(cal.getTime());
        Log.e(TAG, "checking time" + currentTime + time);
        NotificationManager mgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        if(weekday == day){ //&& currentTime.equals(time)){//Name of day doesn't matter because we are not display day -- dont add time because might be delayed call
           // Log.e(TAG, "getting in DayIF");
            Intent intentForPend = new Intent(context, ViewMed.class);
            int id = NotificationID.getID();
            intentForPend.putExtra("id", id);
            PendingIntent pi = PendingIntent.getActivity(context, 9000, intentForPend, 0);

            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("Take Medicine: " + name + " w/Water");
            mBuilder.setContentText("Dosage: " + dosage + " At Time: " + time);
            mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            mBuilder.setAutoCancel(true);
            mBuilder.setContentIntent(pi);
            //Log.e(TAG, "getting in onReceive" + weekday + day);


            //pending intent

           // mBuilder.setContentIntent(pi);
            myDb = new SQLiteHelper(context);
            int refillRES = 0;
            String toPASSamtBTL ="";
            Cursor res = myDb.getDataById(idDB);
            Double dos, amt = 1.0;
            Double newAMT = 1.0;
            while (res.moveToNext()){
                String dosageRES = res.getString(res.getColumnIndexOrThrow("DOSAGE"));
                String amountRES = res.getString(res.getColumnIndexOrThrow("AMOUNT"));
                String refills = res.getString(res.getColumnIndexOrThrow("REFILLS"));
                Log.e(TAG, "NEWAMT: " + newAMT + "NEWREFILL: " + refills);
                String[] dosS = dosageRES.split("\\s+");
                String[] amtS = amountRES.split("\\s+");
                toPASSamtBTL = amtS[1];
                dos = Double.parseDouble(dosS[0]);
                amt = Double.parseDouble(amtS[0]);
                refillRES = Integer.parseInt(refills);
                newAMT = amt - dos;
                myDb.updateAMT(idDB,newAMT.toString() + " " + toPASSamtBTL,refillRES);
            }
            if(newAMT <= 0){
                if(amt > 0){
                    refillRES = refillRES - 1;
                }
                Log.e(TAG, "NEWAMT: " + newAMT + "NEWREFILL: " + refillRES);

                myDb.updateAMT(idDB,newAMT.toString() + " " + toPASSamtBTL,refillRES);
                mBuilderREFILL.setSmallIcon(R.mipmap.ic_launcher);
                mBuilderREFILL.setContentTitle("REFILL MEDICINE " + name);
                mBuilderREFILL.setContentText("AMOUNT " + newAMT + "\n Your Prescription May Have Expired.");
                mBuilderREFILL.setAutoCancel(true);
                mBuilderREFILL.setContentIntent(pi);

                mgr.notify(999999 , mBuilderREFILL.build());
            }

            myDb.close();
            //set ID for notification
            //mNotificationID = 001;

            //get an instance of the notificationManager service


            mgr.notify(id , mBuilder.build());
            mNotificationID++;
        }


    }
}
