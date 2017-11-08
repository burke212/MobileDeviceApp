package com.example.burke.medicalapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Juwan on 11/7/2017.
 */

public class CustomAdapter extends CursorAdapter{

    public CustomAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.list_medicine_single, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView Medicine = view.findViewById(R.id.Medicine);
        TextView Dosage = view.findViewById(R.id.Dosage);
        TextView PerBottle = view.findViewById(R.id.PerBottle);
        TextView Refills = view.findViewById(R.id.Refills);
        TextView Days = view.findViewById(R.id.Days);
        TextView Times = view.findViewById(R.id.Times);

        Medicine.setText(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
        Dosage.setText(cursor.getString(cursor.getColumnIndexOrThrow("DOSAGE")));
        PerBottle.setText(cursor.getString(cursor.getColumnIndexOrThrow("AMOUNT")));
        Refills.setText(cursor.getString(cursor.getColumnIndexOrThrow("REFILLS")));
        Days.setText(cursor.getString(cursor.getColumnIndexOrThrow("DAYS")));
        Times.setText(cursor.getString(cursor.getColumnIndexOrThrow("ALARM")));
    }
}
