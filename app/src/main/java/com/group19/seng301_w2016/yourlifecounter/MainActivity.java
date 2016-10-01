package com.group19.seng301_w2016.yourlifecounter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;;

public class MainActivity extends AppCompatActivity {

    ArrayList<DiaryEntries> profiles;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionButton fab4;
    private FloatingActionButton fab5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProfileAdapter adapterpView = new ProfileAdapter();
        ViewPager profileView = (ViewPager) findViewById(R.id.profileViewer);
        profileView.setAdapter(adapterpView);
        profileView.setCurrentItem(0);

        SettingsData settingsData = new SettingsData();
        UserDatabase userDatabase = new UserDatabase();
        int totalCal = settingsData.getCalorieGoal();

        /*
        LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.rectangle_calories,null);
        TextView caloriesRem = (TextView) view.findViewById(R.id.calRem);

        if (totalCal < 1) {
            //set text to be no calorie goal set
            caloriesRem.setText("Set your calorie goal!");
        }
        else {
            int currentCalories = userDatabase.getTotalCalories();
            int remaining = totalCal - currentCalories;
            if (remaining < 0) {
                caloriesRem.setText("Went over your goal by" + String.valueOf(Math.abs(remaining)));
            }
            else {
                caloriesRem.setText(String.valueOf(remaining));
            }
        }*/

        // DATABASE INIT
        try {
            File file = getBaseContext().getFileStreamPath("foodDB.txt");
            if (!file.exists()) {
                OutputStreamWriter out = new OutputStreamWriter(openFileOutput("foodDB.txt", 0));
                AssetManager am = getApplicationContext().getAssets();
                InputStream in = am.open("sampleFoodDB.txt");

                if (in != null) {
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    while ((str = reader.readLine()) != null) {

                        out.write(str + "\n");
                    }
                }
                in.close();
                out.close();
                //Toast.makeText(this, "Food Database Created!", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "Food DB Present!", Toast.LENGTH_LONG).show();
            }

            file = getBaseContext().getFileStreamPath("userdata.txt");
            if (!file.exists()) {
                // FIX ME
                OutputStreamWriter out = new OutputStreamWriter(openFileOutput("userdata.txt", 0));
                AssetManager am = getApplicationContext().getAssets();
                InputStream in = am.open("sampleUserDB.txt");


                if (in != null) {
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    while ((str = reader.readLine()) != null) {

                        out.write(str);
                    }
                }
                in.close();
                out.close();
                //Toast.makeText(this, "User Database Created!", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "User DB Present!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            //Toast.makeText(this, "Somethings is wrong" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        //END DATABASE INIT

        //test /code>
        /*
        SettingsData settings = new SettingsData();
        HashMap<String, ArrayList<String>> tempHash = new HashMap<>();

        //tempHash.put("Profile", new ArrayList<String>());
        //tempHash.put("Reminders", new ArrayList<String>());
        //tempHash.put("Nutrition", new ArrayList<String>());
        //tempHash.put("Goals", new ArrayList<String>());

        settings.setSettings(tempHash);
        settings.writeSettings();

        Toast.makeText(this, settings.toString(), Toast.LENGTH_LONG).show();
        */
        //test
        //SettingsData settings = new SettingsData();
        //Toast.makeText(this, Integer.toString(settings.getCalorieGoal()), Toast.LENGTH_LONG).show();
        //Toast.makeText(this, Integer.toString(settings.getWaterGoal()), Toast.LENGTH_LONG).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);

        //RECYCLER VIEW STUFF
        RecyclerView rvDiaryEntries = (RecyclerView) findViewById(R.id.rvProfiles);

        profiles = DiaryEntries.createDiaryEntries(1);
        DiaryAdapter adapter = new DiaryAdapter(profiles);

        rvDiaryEntries.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        rvDiaryEntries.setLayoutManager(layoutManager);

        adapter.notifyItemInserted(0);
        //END OF RECYCLER VIEW STUFF

        /*bHome = (Button) findViewById(R.id.homeButton);
        bHome.setOnClickListener(new View.OnClickListener() { // redirects to Search page
            public void onClick(View v) {
                // do nothing
            }
        });

        bProgress = (Button) findViewById(R.id.progressButton);
        bProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Progress.class));
            }
        });

        bWaterIntake = (Button) findViewById(R.id.waterButton);
        bWaterIntake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, WaterIntake.class));
            }
        });

        bDiary = (Button) findViewById(R.id.diaryButton);
        bDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Diary.class));
            }
        });

        bSettings = (Button) findViewById(R.id.settingsButton);
        bSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });*/

        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab4 = (FloatingActionButton)findViewById(R.id.fab4);
        fab5 = (FloatingActionButton)findViewById(R.id.fab5);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Progress.class));
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WaterIntake.class));
            }
        });

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Diary.class));
            }
        });

        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });


    }

    private class ProfileAdapter extends PagerAdapter {

        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object==view;
        }

        public Object instantiateItem(View collection, int position) {

            LayoutInflater inflater = (LayoutInflater) collection.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            int resId = 0;
            switch (position) {
                case 0:
                    resId = showCaloriesRem();
                    break;
                case 1:
                    resId = showWaterRem();
                    break;
            }

            View view = inflater.inflate(resId, null);
            ((ViewPager) collection).addView(view, 0);
            view.setTag(resId);
            return view;
        }

    }
    public int showCaloriesRem()
    {
        int resId;
        resId = R.layout.rectangle_calories;
        return resId;
    }
    public int showWaterRem()
    {
        int resId;
        resId = R.layout.rectangle_water;
        return resId;
    }



}


