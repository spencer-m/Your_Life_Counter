package com.group19.seng301_w2016.yourlifecounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Spencer on 3/8/2016.
 *
 * Data Structure Visualization:
 * {03-22-2016 : {Breakfast : [Food1, Food2], Lunch : [Food1, Food2], Water : [Food1]}}
 */
public class UserDatabase {

    // the string is the date corresponding to the data that is in the arraylist
    private HashMap<String, HashMap<String, ArrayList<Food>>> userData = new HashMap<String, HashMap<String, ArrayList<Food>>>();

    public UserDatabase() {
        readDB();
    }

    public boolean writeDB() {
        try {
            //FileOutputStream fos = getApplicationContext().openFileOutput(filename, getActivity().MODE_PRIVATE);
            FileOutputStream fOut = new FileOutputStream("/data/data/com.group19.seng301_w2016.yourlifecounter/files/userdata.txt");
            OutputStreamWriter out = new OutputStreamWriter(fOut);

            out.write(this.toString());

            out.close();
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

    public boolean readDB() {

        userData = new HashMap<String, HashMap<String, ArrayList<Food>>>();

        try {

            InputStream in = new FileInputStream("/data/data/com.group19.seng301_w2016.yourlifecounter/files/userdata.txt");

            if (in != null) {
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);

                String str;
                String[] buf;

                while ((str = reader.readLine()) != null) {

                    buf = str.split("\t");
                    Food tempFood = new Food(buf[1], buf[2], buf[3], buf[4], buf[5]);

                    if (userData.containsKey(buf[0])) {

                        if (userData.get(buf[0]).containsKey(buf[2]))
                            userData.get(buf[0]).get(buf[2]).add(tempFood);

                        else {
                            ArrayList<Food> tempf = new ArrayList<Food>();
                            tempf.add(tempFood);
                            userData.get(buf[0]).put(buf[2], tempf);
                        }
                    } else {
                        HashMap<String, ArrayList<Food>> temp = new HashMap<String, ArrayList<Food>>();
                        ArrayList<Food> tempf = new ArrayList<Food>();
                        tempf.add(tempFood);
                        temp.put(buf[2], tempf);
                        userData.put(buf[0], temp);
                    }
                }
            }
            in.close();
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

    public void addFoodUser(String xname, String xfoodCategory, String xservingSize, String xservingUnit, String xcaloriesPerServing) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Food tempFood = new Food(xname, xfoodCategory, xservingSize, xservingUnit, xcaloriesPerServing);

        if (userData.containsKey(date)) {

            if (userData.get(date).containsKey(xfoodCategory)) {

                if(xfoodCategory.equals("Water")) {
                    Food watah = userData.get(date).get("Water").remove(0);
                    String total = Integer.toString(Integer.parseInt(xservingSize) + Integer.parseInt(watah.getServingSize()));
                    userData.get(date).get("Water").add(0, new Food("Water", "Water", total, "mL", "0"));
                }
                else
                    userData.get(date).get(xfoodCategory).add(tempFood);
            }

            else {
                ArrayList<Food> tempf = new ArrayList<Food>();
                tempf.add(tempFood);
                userData.get(date).put(xfoodCategory, tempf);
            }
        } else {
            HashMap<String, ArrayList<Food>> temp = new HashMap<String, ArrayList<Food>>();
            ArrayList<Food> tempf = new ArrayList<Food>();
            tempf.add(tempFood);
            temp.put(xfoodCategory, tempf);
            userData.put(date, temp);
        }
        this.writeDB();
    }

    public void removeFoodUser(String xname, String xfoodCategory, String xservingSize, String xservingUnit, String xcaloriesPerServing) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Food tempFood = new Food(xname, xfoodCategory, xservingSize, xservingUnit, xcaloriesPerServing);

        if (userData.containsKey(date)){
            if (userData.get(date).containsKey(xfoodCategory))
                userData.get(date).get(xfoodCategory).remove(tempFood);

        }
        this.writeDB();
    }

    public void updateFoodUser(String date, String category, int pos, String name, String servingSize, String servingUnit, String caloriesPerServing) {

        if(name.length() != 0)
            userData.get(date).get(category).get(pos).setName(name);
        if(servingSize.length() != 0)
            userData.get(date).get(category).get(pos).setName(servingSize);
        if(servingUnit.length() != 0)
            userData.get(date).get(category).get(pos).setName(servingUnit);
        if(caloriesPerServing.length() != 0)
            userData.get(date).get(category).get(pos).setName(caloriesPerServing);

        writeDB();
        readDB();

    }

    public String toString() {

        StringBuilder theString = new StringBuilder(0);

        for (String s1 : userData.keySet()) {
            for (String s2 : userData.get(s1).keySet()) {
                for (Food f : userData.get(s1).get(s2)) {
                    theString.append(s1 + "\t" + f.toString());
                }
            }
        }
        return theString.toString();
    }

    public ArrayList<Food> getFood(String date, String category) {

        ArrayList<Food> result = new ArrayList<>();

        if (userData.containsKey(date)){
            if (userData.get(date).containsKey(category))
                result = userData.get(date).get(category);

        }

        return result;
    }

    public int getWater() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        int result = 0;

        if (userData.containsKey(date)) {
            if (userData.get(date).containsKey("Water"))
                result = Integer.parseInt(userData.get(date).get("Water").get(0).getServingSize());
        }

        return result;
    }

    public int getTotalCalories() {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int result = 0;

        if (userData.containsKey(date)) {
            for (String s : userData.get(date).keySet()) {

                for (Food f : userData.get(date).get(s))

                    result = result + Integer.parseInt(f.getCaloriesPerServing());

            }
        }

        return result;
    }
}