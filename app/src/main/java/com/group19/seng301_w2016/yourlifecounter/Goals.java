package com.group19.seng301_w2016.yourlifecounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nathan on Mar 22 2016.
 */
public class Goals extends AppCompatActivity {

    private Button calorieGoal;
    private Button waterGoal;
    private Button cancel;
    private Button enter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);

        SettingsData settings = new SettingsData();

        /* Calorie Goal */

        //Button causes a dialog box to appear for setting goal
        calorieGoal = (Button)findViewById(R.id.calorieGoal);
        calorieGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Goals.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);   //Removes title (empty space)
                dialog.setContentView(R.layout.dialog_calorie);
                dialog.show();

                EditText editGoal = (EditText) dialog.findViewById(R.id.calorieSet);
                cancel = (Button) dialog.findViewById(R.id.cancel);
                enter = (Button) dialog.findViewById(R.id.enter);

                //Disables enter button initially (no value in editGoal) and makes it semi-transparent
                enter.setEnabled(false);
                enter.setAlpha(.5f);

                //Checks to see if there is a value entered into editGoal
                editGoal.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        //Disables enter if there is no value entered, else enables it
                        //Changes transparency to match state
                        if(s.toString().equals("")) {
                            enter.setEnabled(false);
                            enter.setAlpha(.5f);
                        }
                        else {
                            enter.setEnabled(true);
                            enter.setAlpha(1);
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                //Cancels changing the goal, returns back to goals page
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                //Changes calorie goal as entered by user
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SettingsData tempSet = new SettingsData();
                        HashMap<String, ArrayList<String>> tempSettings = tempSet.getSettings();
                        TextView txt = (TextView) dialog.findViewById(R.id.calorieSet);
                        String value = txt.getText().toString();

                        if (tempSettings.containsKey("Goals")) {

                            if(tempSettings.get("Goals").size() > 0)
                                tempSettings.get("Goals").remove(0);
                            tempSettings.get("Goals").add(0, value); // should be from textbox
                        }
                        else {
                            tempSettings.put("Goals", new ArrayList<String>(2));
                            tempSettings.get("Goals").add(0, value); // should be from textbox
                            tempSettings.get("Goals").add(1, "0");
                        }
                        tempSet.setSettings(tempSettings);

                        Toast.makeText(getApplicationContext(), "Your calorie goal has been changed!", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

            }
        });

        /* Water Goal */

        waterGoal = (Button)findViewById(R.id.waterGoal);
        waterGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Goals.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);   //Removes title (empty space)
                dialog.setContentView(R.layout.dialog_water);
                dialog.show();

                EditText editGoal = (EditText)dialog.findViewById(R.id.waterSet);
                cancel = (Button)dialog.findViewById(R.id.cancel);
                enter = (Button)dialog.findViewById(R.id.enter);

                //Enter button is initially disabled (no value in editGoal) and makes it semi-transparent
                enter.setEnabled(false);
                enter.setAlpha(.5f);

                //Checks to see if there is a value entered into editGoal
                editGoal.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        //Disables enter if there is no value entered, else enables it
                        //Changes transparency to match state
                        if(s.toString().equals("")) {
                            enter.setEnabled(false);
                            enter.setAlpha(.5f);
                        }
                        else {
                            enter.setEnabled(true);
                            enter.setAlpha(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                //Cancels changing the goal, returns back to goals page
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                //Changes water goal as entered by user
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SettingsData tempSet = new SettingsData();
                        HashMap<String, ArrayList<String>> tempSettings = tempSet.getSettings();
                        TextView txt = (TextView) dialog.findViewById(R.id.waterSet);
                        String value = txt.getText().toString();


                        if (tempSettings.containsKey("Goals")) {
                            if(tempSettings.get("Goals").size() > 1)
                                tempSettings.get("Goals").remove(1);

                            tempSettings.get("Goals").add(1, value); // should be from textbox
                        }
                        else {
                            tempSettings.put("Goals", new ArrayList<String>(2));
                            tempSettings.get("Goals").add(0, "0");
                            tempSettings.get("Goals").add(1, value); // should be from textbox
                        }
                        tempSet.setSettings(tempSettings);

                        Toast.makeText(getApplicationContext(), "Your water goal has been changed!", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
            }
        });
    }

    ////////
}
