package com.group19.seng301_w2016.yourlifecounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiaryEntries{
    // profile photo
    // my progress button
    private String userName;
    private String date;
    private String diaryInfo;

    public DiaryEntries(String userName, String date, String diaryInfo){
        this.userName = userName;
        this.date = date;
        this.diaryInfo = diaryInfo;
    }

    /*
    private List<DiaryEntries> diaryEntries;

    private void initData() {
        diaryEntries = new ArrayList<>();
        diaryEntries.add(new DiaryEntries("Shannon", "SANS DATE", "NOW YOU SEE ME xD"));
        diaryEntries.add(new DiaryEntries("Taylor", "March something", "Luvin' mah dawg"));
        diaryEntries.add(new DiaryEntries("Taylor A", "April something", "ICE CREAM CRAZEEE"));
    }*/

    public String getUserName(){
        return this.userName;
    }

    public String getDate(){
        return this.date;
    }

    public String getDiaryInfo(){
        return this.diaryInfo;
    }

    public static ArrayList<DiaryEntries> createDiaryEntries (int numEntries) {

        ArrayList<DiaryEntries> dEntries = new ArrayList<DiaryEntries>();

        for(int i = 1; i <= numEntries; i++) {
            // database operation
            dEntries.add(new DiaryEntries("Jenny", "March 30, 2016", "I was under my calorie goal!"));
        }

        return dEntries;
    }


}
