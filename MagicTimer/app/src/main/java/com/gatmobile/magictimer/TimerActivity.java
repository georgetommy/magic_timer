package com.gatmobile.magictimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class TimerActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button start, pause, reset, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

       chronometer = (Chronometer) findViewById(R.id.chronometer_main);
        start = (Button) findViewById(R.id.start_chrono_main);
        pause = (Button) findViewById(R.id.pause_chrono_main);
        reset = (Button) findViewById(R.id.reset_chrono_main);
        next = (Button) findViewById(R.id.next_chrono_main);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setFormat(null);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("saving time for player: " + chronometer.getFormat());
            }
        });



    }



}
