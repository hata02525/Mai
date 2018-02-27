package com.example.fluper.mai.utils;

import com.example.fluper.mai.R;
import com.example.fluper.mai.modal.ListStore;
import com.example.fluper.mai.modal.SortedList;

import java.util.ArrayList;

/**
 * Created by fluper on 28/11/17.
 */

public class SortedData {
    public static ArrayList<SortedList> StringData() {
        ArrayList<SortedList> items = new ArrayList<>();
        String[] firstTitles = {"Starbucks Coffee", "Domino's Pizza"};
        String[] secondTitles = {"International", "Italian"};
        Integer[] clubImages = new Integer[]{R.drawable.login_bg, R.drawable.login_bg};
        String[] sortednumber={"05KM","02KM"};


        for (int j = 0; j < clubImages.length; j++) {
            SortedList sortedList= new SortedList();
            sortedList.setTitlename(firstTitles[j]);
            sortedList.setSubtite(secondTitles[j]);
            sortedList.setImageid(clubImages[j]);
            sortedList.setSnumber(sortednumber[j]);
            items.add(sortedList);
        }
        return items;
    }
}
