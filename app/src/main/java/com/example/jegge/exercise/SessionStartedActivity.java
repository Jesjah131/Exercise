package com.example.jegge.exercise;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class SessionStartedActivity extends Activity {

    public boolean running;
    Chronometer MyChronometer;
    long startTime, stopTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_started);

        stopTime = 0;
        MyChronometer = (Chronometer) findViewById(R.id.timer);
        ((Chronometer) findViewById(R.id.timer)).start();
        startTime=System.currentTimeMillis();
        running = true;
    }
}
