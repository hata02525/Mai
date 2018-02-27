package com.example.fluper.mai.utils;

import com.example.fluper.mai.R;
import com.example.fluper.mai.modal.ListModel;

import java.util.ArrayList;

/**
 * Created by fluper on 27/11/17.
 */

public class DataStore {
    public static ArrayList<ListModel> StringData() {
        ArrayList<ListModel> items = new ArrayList<>();
        String[] firstTitles = {"Food & Beverage","Beauty","Wellness","Hotel"," Tour & Travel","Attraction & Leisure"," Retail Store","Service"," Education","Medical"};
        String[] secondTitles={"(05)","(02)","(03)","(05)","(02)","(03)","(02)","(03)","(02)","(02)"};
        Integer[] clubImages = new Integer[]{R.drawable.login_bg,R.drawable.login_bg,R.drawable.splash,
                R.drawable.login_bg,R.drawable.splash,R.drawable.login_bg,R.drawable.splash,R.drawable.login_bg,R.drawable.login_bg,R.drawable.login_bg};

        for (int j = 0; j < clubImages.length; j++) {
            ListModel dataModel= new ListModel();
            dataModel.setName(firstTitles[j]);
            dataModel.setImageid(clubImages[j]);
            dataModel.setNamewithnumbr(secondTitles[j]);
            items.add(dataModel);
        }
        return items;
    }
}
