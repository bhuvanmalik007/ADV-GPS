package com.abdroid.wps2;


public class Apinfo {
    //private variables
    String mac;
    String lat;
    String longt;
    int id;

    // Empty constructor
    public Apinfo() {

    }

    // constructor
    public Apinfo(int id1, String m, String latt, String lng) {
        this.id=id1;
        this.mac = m;
        this.lat = latt;
        this.longt = lng;
    }

    public Apinfo(String m, String latt, String lng){
        this.mac = m;
        this.lat = latt;
        this.longt = lng;
    }

    public String getLat() {

        return lat;
    }

    public void setLat(String lat) {

        this.lat = lat;
    }

    public String getMac() {

        return mac;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setMac(String mac) {

        this.mac = mac;
    }

    public String getLongt() {

        return longt;
    }

    public void setLongt(String longt) {

        this.longt = longt;
    }
}
