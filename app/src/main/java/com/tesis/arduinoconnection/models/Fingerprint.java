package com.tesis.arduinoconnection.models;

/**
 * Created by Lestat on 17-04-2018.
 */

public class Fingerprint {
    private String hash_fingerprint;
    private String rut;
    private int id_in_sensor;

    public Fingerprint(String hash_fingerprint, String rut, int id_in_sensor) {
        this.hash_fingerprint = hash_fingerprint;
        this.rut = rut;
        this.id_in_sensor = id_in_sensor;
    }

    public String getHash_fingerprint() {
        return hash_fingerprint;
    }

    public void setHash_fingerprint(String hash_fingerprint) {
        this.hash_fingerprint = hash_fingerprint;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getId_in_sensor() {
        return id_in_sensor;
    }

    public void setId_in_sensor(int id_in_sensor) {
        this.id_in_sensor = id_in_sensor;
    }
}
