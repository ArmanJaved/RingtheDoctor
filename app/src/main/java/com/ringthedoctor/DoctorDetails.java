package com.ringthedoctor;

/**
 * Created by AndroidJSon.com on 6/10/2017.
 */


public class DoctorDetails {

    public String doctorname;
    public String docpic;
    public String dochosp;
    public String docadd;
    public String doccont;
    public String latlong;

    public DoctorDetails() {

    }

    public DoctorDetails(String doctorname, String docpic, String dochosp, String docadd, String doccont, String latlong) {
        this.doctorname = doctorname;
        this.docpic = docpic;
        this.dochosp = dochosp;
        this.docadd = docadd;
        this.doccont = doccont;
        this.latlong = latlong;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getDocpic() {
        return docpic;
    }

    public String getDochosp() {
        return dochosp;
    }

    public String getDocadd() {
        return docadd;
    }

    public String getDoccont() {
        return doccont;
    }

    public String getLatlong() {
        return latlong;
    }
}
