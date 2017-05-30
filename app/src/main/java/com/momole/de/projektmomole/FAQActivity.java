package com.momole.de.projektmomole;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
public class FAQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq2);}

        public void goTofrage1 (View view){
            Intent intent = new Intent (this, Fragebutton.class);
            startActivity(intent);
        }
    public void goTofrage2 (View view){
        Intent intent = new Intent (this, Fragebutton2.class);
        startActivity(intent);
    }
    public void goTofrage7 (View view){
        Intent intent = new Intent (this, Fragebutton3.class);
        startActivity(intent);
    }
    public void goTofrage3 (View view){
        Intent intent = new Intent (this, Fragebutton4.class);
        startActivity(intent);
    }
    public void goTofrage4 (View view){
        Intent intent = new Intent (this, Fragebutton5.class);
        startActivity(intent);
    }
    public void goTofrage5 (View view){
        Intent intent = new Intent (this, Fragebutton6.class);
        startActivity(intent);
    }
    public void goTofrage6 (View view){
        Intent intent = new Intent (this, Fragebutton7.class);
        startActivity(intent);
    }

    }




