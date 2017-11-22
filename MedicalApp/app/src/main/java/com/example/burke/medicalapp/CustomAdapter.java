package com.example.burke.medicalapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Juwan on 11/7/2017.
 */

public class CustomAdapter extends CursorAdapter{
private Context activity;
    public CustomAdapter(Context context, Cursor c) {
        super(context, c);
        activity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.list_medicine_single, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView Medicine = view.findViewById(R.id.Medicine);
        TextView Dosage = view.findViewById(R.id.Dosage);
        TextView PerBottle = view.findViewById(R.id.PerBottle);
        TextView Refills = view.findViewById(R.id.Refills);
        TextView Days = view.findViewById(R.id.Days);
        TextView Times = view.findViewById(R.id.Times);
        final Button edit = view.findViewById(R.id.Edit);
        final Button Remove = view.findViewById(R.id.Remove);


        edit.setTag(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
        Remove.setTag(cursor.getString(cursor.getColumnIndexOrThrow("_id")));


        Medicine.setText(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
        Dosage.setText(cursor.getString(cursor.getColumnIndexOrThrow("DOSAGE")));
        PerBottle.setText(cursor.getString(cursor.getColumnIndexOrThrow("AMOUNT")));
        Refills.setText(cursor.getString(cursor.getColumnIndexOrThrow("REFILLS")));
        Days.setText(cursor.getString(cursor.getColumnIndexOrThrow("DAYS")));
        Times.setText(cursor.getString(cursor.getColumnIndexOrThrow("ALARM")));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPass = edit.getTag().toString();
                //SQLiteHelper cartHelper = new SQLiteHelper(context);
                //cartHelper.updateData(idPass, quantity.getSelectedItem().toString());
                ((ViewMed) activity).update(idPass);
            }
        });

        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPass = Remove.getTag().toString();
                ((ViewMed) activity).remove(idPass);
            }
        });
    }
}
