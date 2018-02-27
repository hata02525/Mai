package com.example.fluper.mai.utils;

import com.example.fluper.mai.R;
import com.example.fluper.mai.modal.ListModel;
import com.example.fluper.mai.modal.ListStore;

import java.util.ArrayList;

/**
 * Created by fluper on 27/11/17.
 */

public class MerchantStore {

    public static ArrayList<ListStore> StringData() {
        ArrayList<ListStore> items = new ArrayList<>();
        String[] firstTitles = {"Optimum Nutritions","Merchant Name 02","Domino's Pizza","LOREA'L Paris","OLAY","Amway","Muscle Blaze"};
        Integer[] clubImages = new Integer[]{R.drawable.login_bg,R.drawable.login_bg,R.drawable.splash,
                R.drawable.login_bg,R.drawable.splash,R.drawable.login_bg,R.drawable.splash,};

        for (int j = 0; j < clubImages.length; j++) {
           ListStore datastore= new ListStore();
            datastore.setMerchantname(firstTitles[j]);
            datastore.setMerchantimgid(clubImages[j]);
            items.add(datastore);
        }
        return items;
    }
}
