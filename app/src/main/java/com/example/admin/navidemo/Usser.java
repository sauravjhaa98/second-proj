package com.example.admin.navidemo;

public class Usser {

    private String name;
    private String mobile;
    private String timeslot;
    private  String appointmentdate;
    private  String apptype;


    public Usser(String name, String mobile,String timeslot,String appointmentdate,String apptype) {
        this.name = name;
        this.mobile = mobile;
        this.timeslot=timeslot;
        this.appointmentdate=appointmentdate;
        this.apptype=apptype;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }
    public String getTimeslot() {
        return timeslot;
    }
    public String getAppointmentdate() {
        return appointmentdate;
    }
    public String getApptype() {
        return apptype;
    }
}