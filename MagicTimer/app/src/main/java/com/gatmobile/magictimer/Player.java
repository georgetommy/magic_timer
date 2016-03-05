package com.gatmobile.magictimer;

import android.widget.Chronometer;

/**
 * Created by Gabrielle on 05-Mar-16.
 */
public class Player {

    String name;

    Chronometer chronometer;

    Chronometer chronometer_total;

    long timeChronometer;

    long timeChronometerTotal;

    public Player(){

    }

    public Player(String name,Chronometer chronometer, Chronometer chronometer_total){
        this.name=name;
        this.chronometer=chronometer;
        this.chronometer_total= chronometer_total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chronometer getChronometer() {
        return chronometer;
    }

    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    public Chronometer getChronometer_total() {
        return chronometer_total;
    }

    public void setChronometer_total(Chronometer chronometer_total) {
        this.chronometer_total = chronometer_total;
    }

    public long getTimeChronometer() {
        return timeChronometer;
    }

    public void setTimeChronometer(long timeChronometer) {
        this.timeChronometer = timeChronometer;
    }

    public long getTimeChronometerTotal() {
        return timeChronometerTotal;
    }

    public void setTimeChronometerTotal(long timeChronometerTotal) {
        this.timeChronometerTotal = timeChronometerTotal;
    }
}
