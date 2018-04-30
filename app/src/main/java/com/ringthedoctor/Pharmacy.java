package com.ringthedoctor;

/**
 * Created by BrainPlow on 4/27/2018.
 */

public class Pharmacy {

    public String pharname;
    public String pharadd;
    public String pharcont;

    public Pharmacy(){}

    public Pharmacy(String pharname, String pharadd, String pharcont) {
        this.pharname = pharname;
        this.pharadd = pharadd;
        this.pharcont = pharcont;
    }

    public String getPharname() {
        return pharname;
    }

    public String getPharadd() {
        return pharadd;
    }

    public String getPharcont() {
        return pharcont;
    }

}
