package com.example.fluper.mai.utils;

import com.example.fluper.mai.R;
import com.example.fluper.mai.modal.Categorylist;
import com.example.fluper.mai.modal.ListStore;

import java.util.ArrayList;

/**
 * Created by fluper on 27/11/17.
 */

public class CategoryListData {

    public static ArrayList<Categorylist> StringData() {
        ArrayList<Categorylist> items = new ArrayList<>();
        String[] firstTitles = {"Near You","Geneva","Winterthur","Fribourg",};
        String[] SecondTitles={"04","03","14","05",};
        for (int j = 0; j < firstTitles.length; j++) {
            Categorylist category= new Categorylist();
            category.setListname(firstTitles[j]);
            category.setNumber(SecondTitles[j]);
            items.add(category);
        }
        return items;
    }
}
