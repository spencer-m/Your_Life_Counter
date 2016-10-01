package com.group19.seng301_w2016.yourlifecounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Spencer on 3/24/2016.
 */
public class SettingsData {

    HashMap<String, ArrayList<String>> settings;

    public SettingsData() {
        readSettings();
    }

    public boolean writeSettings() {
        try {
            FileOutputStream fOut = new FileOutputStream("/data/data/com.group19.seng301_w2016.yourlifecounter/files/settings.txt");
            OutputStreamWriter out = new OutputStreamWriter(fOut);

            out.write(this.toString());

            out.close();
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

    public boolean readSettings() {

        settings = new HashMap<String, ArrayList<String>>();

        try {

            InputStream in = new FileInputStream("/data/data/com.group19.seng301_w2016.yourlifecounter/files/settings.txt");

            if (in != null) {
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);

                String str;
                String[] buf;

                while ((str = reader.readLine()) != null) {

                    buf = str.split("\t");

                    if (buf.length == 0)
                        continue;
                    if (buf.length == 1) {
                        if (!settings.containsKey(buf[0]))
                            settings.put(buf[0], new ArrayList<String>());
                    }
                    else {

                        if (settings.containsKey(buf[0]))
                            settings.get(buf[0]).add(buf[1]);
                        else {
                            ArrayList<String> temp = new ArrayList<>();
                            temp.add(buf[1]);
                            settings.put(buf[0], temp);
                        }
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

    public HashMap<String, ArrayList<String>> getSettings() {

        readSettings();
        return settings;
    }

    public void setSettings(HashMap<String, ArrayList<String>> s) {

        settings = s;
        writeSettings();

    }

    public String toString() {

        String result = "";

        for(String s : settings.keySet()) {

            if (settings.get(s).size() > 0) {
                for(String t : settings.get(s)) {
                    result = result + s + "\t" + t + "\n";
                }
            }
            else
                result = result + s + "\t" + "" + "\n";
        }
        return result;
    }

    public int getCalorieGoal() {

        int result = -1;

        readSettings();

        if (settings.containsKey("Goals")) {
            if(settings.get("Goals").size() >= 2)
                result = Integer.parseInt(settings.get("Goals").get(0));
        }

        return result;
    }

    public int getWaterGoal() {

        int result = -1;

        readSettings();

        if (settings.containsKey("Goals")) {
            if(settings.get("Goals").size() >= 2)
                result = Integer.parseInt(settings.get("Goals").get(1));
        }

        return result;
    }
}
