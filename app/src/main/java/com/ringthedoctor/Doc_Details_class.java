package com.ringthedoctor;

/**
 * Created by BrainPlow on 8/25/2017.
 */

public class Doc_Details_class {


    public String rating;
    public String hos_addr;
    public String doc_cont;
    public String dco_tim;

    public Doc_Details_class(){}
    public Doc_Details_class(String rating, String hos_addr, String doc_cont, String dco_tim) {
        this.rating = rating;
        this.hos_addr = hos_addr;
        this.doc_cont = doc_cont;
        this.dco_tim = dco_tim;
    }

    public String getRating() {
        return rating;
    }

    public String getHos_addr() {
        return hos_addr;
    }

    public String getDoc_cont() {
        return doc_cont;
    }

    public String getDco_tim() {
        return dco_tim;
    }
}
