package com.melihozden.mobileprogrammingodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        final Button emailGondermeButton = findViewById(R.id.emailGonderme);
        final Button kullaniciListButton = findViewById(R.id.kullaniciList);
        final Button sensorScreenButton = findViewById(R.id.sensorScreen);
        final Button settingScreenButton = findViewById(R.id.settingScreen);

        emailGondermeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(MenuActivity.this, emailActivity.class);
                startActivity(emailIntent);

            }
        });

        kullaniciListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kullaniciList = new Intent(MenuActivity.this, KullaniciListesiActivity.class );
                startActivity(kullaniciList);
            }
        });

        sensorScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensorScreen = new Intent(MenuActivity.this, SensorActivity.class );
               startActivity(sensorScreen);

            }
        });

        settingScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingScreen = new Intent(MenuActivity.this, KullaniciActivity.class );
                startActivity(settingScreen);
            }
        });


    }








}
