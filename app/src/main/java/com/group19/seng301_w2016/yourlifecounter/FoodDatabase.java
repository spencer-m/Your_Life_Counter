package com.group19.seng301_w2016.yourlifecounter;
import android.content.Context;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

public class FoodDatabase {

	private HashSet<Food> foodBase;


	public FoodDatabase() {

		this.readDB();
	}

    public void addFood(String xname, String xfoodCategory, String xservingSize, String xservingUnit, String xcaloriesPerServing) {

        Food tempFood = new Food(xname, xfoodCategory, xservingSize, xservingUnit, xcaloriesPerServing);

        if (!foodBase.contains(tempFood))
            foodBase.add(tempFood);
        writeDB();
    }

    public void removeFood(String xname, String xfoodCategory, String xservingSize, String xservingUnit, String xcaloriesPerServing) {

        Food tempFood = new Food(xname, xfoodCategory, xservingSize, xservingUnit, xcaloriesPerServing);

        if (foodBase.contains(tempFood))
            foodBase.remove(tempFood);
        writeDB();
    }

	public String toString() {

        StringBuilder theString = new StringBuilder(0);

		for (Food f : foodBase)
			theString.append(f.toString());

		return theString.toString();

	}

	public boolean readDB()  {

		foodBase = new HashSet<Food>();

		try {

			InputStream in = new FileInputStream("/data/data/com.group19.seng301_w2016.yourlifecounter/files/foodDB.txt");

			if (in != null) {
				InputStreamReader tmp = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(tmp);

				String str;
				String[] buf;

				while ((str = reader.readLine()) != null) {

					buf = str.split("\t");
					Food tempFood = new Food(buf[0], buf[1], buf[2], buf[3], buf[4]);

					foodBase.add(tempFood);
				}

				in.close();
			}
		}
		catch(Exception e) {
			return false;
		}

		return true;
	}


	public boolean writeDB () {

		try {

			FileOutputStream fOut = new FileOutputStream("/data/data/com.group19.seng301_w2016.yourlifecounter/files/foodDB.txt");
			OutputStreamWriter out = new OutputStreamWriter(fOut);

			out.write(this.toString());

			out.close();

		}
		catch(Exception e) {
			return false;
		}

		return true;
	}

	// do in a background thread
    public ArrayList<Food> search(String food) {

        ArrayList<Food> result = new ArrayList<Food>();

        for(Food f : foodBase) {

            if (f.getName().toLowerCase().contains(food.toLowerCase()))
                result.add(new Food(f));
        }

		Collections.sort(result);
        return result;
    }


}