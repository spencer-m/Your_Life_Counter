package com.group19.seng301_w2016.yourlifecounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jennykuma on 2016-03-06.
 */
public class Diary extends AppCompatActivity {

    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3; // water intake
    private FloatingActionButton fab4; // home

    private TextView goal;

    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary);


        SettingsData settingsData = new SettingsData();
        UserDatabase userDatabase = new UserDatabase();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if ((settingsData.getCalorieGoal() < 1) || (settingsData.getWaterGoal() < 1)) {
            Toast.makeText(getApplicationContext(), "One or more goals haven't been set!\nPlease set them to view your Diary", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Diary.this, Goals.class)); // redirect to goals page
        } else if (progressBar.getProgress() < 100){
            int total = settingsData.getCalorieGoal();
            int current = userDatabase.getTotalCalories();

            int value = (current * 100) / total;
            progressBar.setProgress(value);
        }

        goal = (TextView)findViewById(R.id.calorieTotal);
        goal.setText(String.valueOf(userDatabase.getTotalCalories()) + "/" + String.valueOf(settingsData.getCalorieGoal()) + " calories");

        ArrayList<Food> tempFood = new ArrayList<>();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Breakfast");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Early Meal");
        host.addTab(spec);

        ListView list1 = (ListView)findViewById(R.id.tab1list);
        userDatabase.readDB();

        // parse algo
        ArrayList<FoodView> earlyMeal= new ArrayList<FoodView>();
        tempFood = userDatabase.getFood(date, "Early Meal");

        // end of parse algo


        for (Food f : tempFood)
            earlyMeal.add(new FoodView(f.getName(), f.getFoodCategory(), f.getServingSize(), f.getServingUnit(), f.getCaloriesPerServing()));

        ArrayAdapter<FoodView> adapterlist1 = new ArrayAdapter<FoodView>(this, android.R.layout.simple_list_item_1, earlyMeal);
        list1.setAdapter(adapterlist1);


        TabWidget tw = (TabWidget) host.findViewById(android.R.id.tabs);
        View tabView = tw.getChildTabViewAt(0);
        TextView tv = (TextView)tabView.findViewById(android.R.id.title);
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        tv.setTypeface(face);

        TextView tab = (TextView) host.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tab.setTextSize(12);

        //Tab 2
        spec = host.newTabSpec("Lunch");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Lunch");
        host.addTab(spec);

        ListView list2 = (ListView)findViewById(R.id.tab2list);
        userDatabase.readDB();

        //parse algo
        ArrayList<FoodView> lunch = new ArrayList<FoodView>();
        tempFood = userDatabase.getFood(date, "Lunch");



        for (Food f : tempFood)
            lunch.add(new FoodView(f.getName(), f.getFoodCategory(), f.getServingSize(), f.getServingUnit(), f.getCaloriesPerServing()));

        ArrayAdapter<FoodView> adapterlist2 = new ArrayAdapter<FoodView>(this, android.R.layout.simple_list_item_1, lunch);
        list2.setAdapter(adapterlist2);


        TabWidget tw2 = (TabWidget)host.findViewById(android.R.id.tabs);
        View tabView2 = tw2.getChildTabViewAt(1);
        TextView tv2 = (TextView)tabView2.findViewById(android.R.id.title);
        tv2.setTypeface(face);

        TextView tab2 = (TextView) host.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tab2.setTextSize(12);

        //Tab 3
        spec = host.newTabSpec("Dinner");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Dinner");
        host.addTab(spec);

        ListView list3 = (ListView)findViewById(R.id.tab3list);
        userDatabase.readDB();
        ArrayList<FoodView> dinner = new ArrayList<>();
        tempFood = userDatabase.getFood(date, "Dinner"); //dynamic date


        for (Food f : tempFood)
            dinner.add(new FoodView(f.getName(), f.getFoodCategory(), f.getServingSize(), f.getServingUnit(), f.getCaloriesPerServing()));

        ArrayAdapter<FoodView> adapterlist3 = new ArrayAdapter<FoodView>(this, android.R.layout.simple_list_item_1, dinner);
        list3.setAdapter(adapterlist3);


        TextView tab3 = (TextView) host.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tab3.setTextSize(12);

        TabWidget tw3 = (TabWidget)host.findViewById(android.R.id.tabs);
        View tabView3 = tw3.getChildTabViewAt(2);
        TextView tv3 = (TextView)tabView3.findViewById(android.R.id.title);
        tv3.setTypeface(face);

        //Tab 4
        spec = host.newTabSpec("Snack");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Snack");
        host.addTab(spec);

        ListView list4 = (ListView)findViewById(R.id.tab4list);
        userDatabase.readDB();
        ArrayList<FoodView> snack = new ArrayList<>();
        tempFood = userDatabase.getFood(date, "Snack"); //dynamic date


        for (Food f : tempFood)
            snack.add(new FoodView(f.getName(), f.getFoodCategory(), f.getServingSize(), f.getServingUnit(), f.getCaloriesPerServing()));

        ArrayAdapter<FoodView> adapterlist4 = new ArrayAdapter<FoodView>(this, android.R.layout.simple_list_item_1, snack);
        list4.setAdapter(adapterlist4);


        TabWidget tw4 = (TabWidget)host.findViewById(android.R.id.tabs);
        View tabView4 = tw4.getChildTabViewAt(3);
        TextView tv4 = (TextView)tabView4.findViewById(android.R.id.title);
        tv4.setTypeface(face);

        TextView tab4 = (TextView) host.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
        tab4.setTextSize(12);

        // END OF TABS

        fab4 = (FloatingActionButton)findViewById(R.id.fab4);

        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diary.this, MainActivity.class));
            }
        });

        fab3 = (FloatingActionButton)findViewById(R.id.fab3);

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diary.this, WaterIntake.class));
            }
        });

        fab2 = (FloatingActionButton)findViewById(R.id.fab2);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diary.this, createNew.class));
            }
        });

        fab1 = (FloatingActionButton)findViewById(R.id.fab1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Diary.this, searchFood.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}