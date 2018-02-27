package com.example.fluper.mai.modal;

/**
 * Created by fluper on 27/11/17.
 */

public class ListStore {


    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }
    public String getMerchantname() {
        return merchantname;
    }
    public void setMerchantimgid(int merchantimgid) {
        this.merchantimgid = merchantimgid;
    }

    public int getMerchantimgid() {
        return merchantimgid;
    }



    String merchantname;
    int merchantimgid;
}
