package com.android.example.androidexam.parsing.json;

/**
 * Created by ws on 2015-09-15.
 */
public class Weather {
    private String time;
    private String temp;
    private String description;

    public Weather(String time, String temp, String description) {
        this.description = description;
        this.temp = temp;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "time: " + time + ", temp: " + temp + ", desc: " + description;
    }
}