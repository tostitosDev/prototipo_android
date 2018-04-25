package com.tesis.arduinoconnection.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lestat on 17-04-2018.
 */

public class Mark {
    private float latitude;
    private float longitude;
    private int id_in_sensor;
    private int type_mark_id;
    private Date date_time_mark;

    public Mark(float latitude, float longitude, int id_in_sensor, int type_mark_id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_in_sensor = id_in_sensor;
        this.date_time_mark = Calendar.getInstance().getTime();
        this.type_mark_id = type_mark_id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getId_in_sensor() {
        return id_in_sensor;
    }

    public void setId_in_sensor(int id_in_sensor) {
        this.id_in_sensor = id_in_sensor;
    }

    public Date getDate_time_mark() {
        return date_time_mark;
    }

    public void setDate_time_mark(Date date_time_mark) {
        this.date_time_mark = date_time_mark;
    }
}
