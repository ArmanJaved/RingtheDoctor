package com.ringthedoctor;

/**
 * Created by BrainPlow on 5/1/2018.
 */

public class DoctorSlot {

    public String slottiming;
    public String slotflag;

    public DoctorSlot(){}

    public DoctorSlot(String slottiming, String slotflag) {
        this.slottiming = slottiming;
        this.slotflag = slotflag;
    }

    public String getSlottiming() {
        return slottiming;
    }

    public String getSlotflag() {
        return slotflag;
    }

    public void setSlottiming(String slottiming) {
        this.slottiming = slottiming;
    }

    public void setSlotflag(String slotflag) {
        this.slotflag = slotflag;
    }
}
