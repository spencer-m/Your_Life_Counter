package com.group19.seng301_w2016.yourlifecounter;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Jennykuma on 2016-03-06.
 */
public class Settings extends AppCompatActivity{

    public static UserDatabase userDatabase = new UserDatabase();
    private Button myProfile;
    private Button reminders;
    private Button nutrition;
    private TextView goals;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


        goals = (TextView)findViewById(R.id.goals);
        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, Goals.class));
            }
        });
    }
}
