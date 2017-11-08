package com.example.burke.medicalapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TestView extends AppCompatActivity {
    SQLiteHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        myDb = new SQLiteHelper(this);

        Cursor res = myDb.getAllData();
        ListView list = (ListView) findViewById(R.id.list);
        Button add = (Button) findViewById(R.id.add);
// Setup cursor adapter using cursor from last step
        CustomAdapter adapter = new CustomAdapter(this, res);
// Attach cursor adapter to the ListView
        list.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TestView.this,"Data Inserted",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TestView.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //This will then trigger the CursorAdapter iterating through the result set and populating the list. We can change the cursor to update the adapter at any time with:

// Switch to new cursor and update contents of ListView
        //adapter.changeCursor(todoCursor);
    }
}
