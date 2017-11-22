package com.example.burke.medicalapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ViewMed extends AppCompatActivity {
    SQLiteHelper myDb;
    CustomAdapter adapter;
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
        //This will then trigger the CursorAdapter iterating through the result set and populating the list. We can change the cursor to update the adapter at any time with:

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
