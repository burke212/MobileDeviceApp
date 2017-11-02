package com.example.burke.medicalapp;

/**
 * Created by Juwan on 11/1/2017.
 */

public class SharedPref {
    //
    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String MEDICINE = "CUS_EMAIL";
    public static final String DOSAGE = "HOW MANY OR HOW MUCH";

    //If server response is equal to this that means login is successful
    public static final String AMOUNT = "AMOUNTPRESCRIBED";
    public static final String AMOUNTLEFT = "AMOUNT-DOSAGE*TIMESTAKEN";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String REFILLS = "5";


    //This would be used to store the email of current logged in user
    public static final String EXPIRATION  = "Monday Dec, 30th 2017";
    public static final String TIME = "9:00";
    public static final String TIMESPERDAY = "Morning, Afternoon, Evening, Night";

}
