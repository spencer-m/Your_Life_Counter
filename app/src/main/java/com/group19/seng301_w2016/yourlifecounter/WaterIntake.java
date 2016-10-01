package com.group19.seng301_w2016.yourlifecounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jennykuma on 2016-03-06.
 * Edited by KaySee on 2016-03-23.
 */
public class WaterIntake extends AppCompatActivity {

    public static UserDatabase userDatabase = new UserDatabase();
    private FloatingActionButton fab1;
    private EditText editText;
    private TextView addWater;
    private TextView totalWaterOutput;
    private Button waterButton;
    private String water = "Water";
    private int updatedTotal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waterintake);

        editText = (EditText) findViewById(R.id.waterAmount);

        addWater = (TextView) findViewById(R.id.waterAmount);
        totalWaterOutput = (TextView) findViewById(R.id.totalWaterOutput);

        updatedTotal = userDatabase.getWater();
        totalWaterOutput.setText(Integer.toString(updatedTotal) + " mL");


        waterButton = (Button) findViewById(R.id.waterButton);
        waterButton.setEnabled(false);
        waterButton.setAlpha(.5f);

        //Checks to see if there is a value entered into editText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Disables addwater if there is no value entered, else enables it
                //Changes transparency to match state
                if(s.toString().equals("")) {
                    waterButton.setEnabled(false);
                    waterButton.setAlpha(.5f);
                }
                else {
                    waterButton.setEnabled(true);
                    waterButton.setAlpha(1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        waterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                userDatabase.addFoodUser(water, water, addWater.getText().toString(), "0", "0");

                updatedTotal = userDatabase.getWater();
                totalWaterOutput.setText(Integer.toString(updatedTotal) + "\nmL");

                //dialog.dismiss();

                Toast.makeText(getApplicationContext(), "The water amount has been updated!", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(WaterIntake.this, Diary.class));

                // clear edittext
                editText.setText("");

                //disappear keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WaterIntake.this, MainActivity.class));
            }
        });
    } // end of oncreate


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