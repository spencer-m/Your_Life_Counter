package com.group19.seng301_w2016.yourlifecounter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Jennykuma on 2016-03-08.
 */
public class createNew extends AppCompatActivity {

    public static FoodDatabase foodDatabase = new FoodDatabase();
    public static UserDatabase userDatabase = new UserDatabase();
    private String[] categories;
    private String[] measurements;
    private Button   addFood;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new);

        this.categories= new String[]{
                "Early Meal", "Lunch", "Dinner", "Snack",
        };
        Spinner spinner = (Spinner) findViewById(R.id.foodCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, categories);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.measurements= new String[]{
                "Gram(s)", "Cup(s)", "Piece(s)",
        };
        Spinner spinner2 = (Spinner) findViewById(R.id.servingType);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, measurements);
        spinner2.setAdapter(adapter2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        addFood = (Button)findViewById(R.id.addFoodb);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // toast
                // add checks here, floating points?
                //ProgressDialog dialog = ProgressDialog.show(createNew.this, "Loading", "Please wait...");

                Boolean emptyText = false;

                TextView name = (TextView)findViewById(R.id.editText);
                TextView servingSize = (TextView)findViewById(R.id.servingSize);
                TextView calperServe = (TextView)findViewById(R.id.calPerServing);

                //Checks to see if there is an empty text field
                if(name.getText().toString().length() <= 0 || servingSize.getText().toString().length() <= 0 || calperServe.getText().toString().length() <= 0) {
                    emptyText = true;
                }

                Spinner category = (Spinner)findViewById(R.id.foodCategory);
                Spinner servingType = (Spinner)findViewById(R.id.servingType);


                //try{ Thread.sleep(2000); }catch(InterruptedException e){ }


                if(emptyText) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    userDatabase.addFoodUser(name.getText().toString(), category.getSelectedItem().toString(), servingSize.getText().toString(),
                            servingType.getSelectedItem().toString(), calperServe.getText().toString());

                    //dialog.dismiss();

                    Toast.makeText(getApplicationContext(), "Your food has been created!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(createNew.this, Diary.class));
                }
            }
        });
    }
}
