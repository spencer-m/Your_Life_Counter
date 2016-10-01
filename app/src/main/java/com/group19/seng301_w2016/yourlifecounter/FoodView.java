package com.group19.seng301_w2016.yourlifecounter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Spencer on 3/22/2016.
 */
public class FoodView extends Food {

    private String name;
    private String foodCategory;
    private String caloriesPerServing;
    private String servingSize;
    private String servingUnit;


    public FoodView(String xname, String xfoodCategory, String xservingSize, String xservingUnit, String xcaloriesPerServing) {

        super(xname, xfoodCategory, xservingSize, xservingUnit, xcaloriesPerServing);
        name = xname;
        foodCategory = xfoodCategory;
        caloriesPerServing = xcaloriesPerServing;
        servingSize = xservingSize;
        servingUnit = xservingUnit;

        //nutrients = new HashMap<String, Integer>();

    }
    public FoodView(Food f) {

        super(f);
        name = f.getName();
        foodCategory = f.getFoodCategory();
        caloriesPerServing = f.getCaloriesPerServing();
        servingSize = f.getServingSize();
        servingUnit = f.getServingUnit();
    }

    // edit to change view in the list for Diary activity
    
    public String toString() {

        String result = "";

        //String tname = name.length() < 32 ? new String(name) : name.substring(0,31);

        result = "\n" + name + "\n" + caloriesPerServing + " Calories\n";

        return result;
    }
}
