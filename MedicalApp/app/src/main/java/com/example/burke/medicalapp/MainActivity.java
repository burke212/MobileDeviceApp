package com.example.burke.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button view,add;


// set ADD condition that no strings have ";" in them multiple values ",".
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //testing layout of add.xml. Uncomment line beneath to load activity main first
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

//        setCurrentTime();
//        setCurrentDay();
//        System.out.print("TEST");

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


}

//    private void setCurrentDay(){
//        String currentDay;
//        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
//        Calendar calendar = Calendar.getInstance();
//        currentDay = dayFormat.format(calendar.getTime());
//        System.out.println("current day: " + currentDay + "\n");
//    }
//
//    private void setCurrentTime(){
//        String currentTime;
//        Format formatter = new SimpleDateFormat("hh:mm a");
//        Calendar calendar = Calendar.getInstance();
//        currentTime = formatter.format(calendar.getTime());
//        System.out.println("current time: " + currentTime + "\n");
//    }

}
