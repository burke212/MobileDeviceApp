package com.example.burke.medicalapp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Juwan on 11/29/2017.
 */

public class NotificationID {
    private final static AtomicInteger c = new AtomicInteger(0);

    public static int getID()
    {
        return c.incrementAndGet();
    }
}
