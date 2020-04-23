package com.melihozden.mobileprogrammingodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class KullaniciActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    EditText kullaniciAdi;
    EditText kullaniciBoy;
    EditText kullaniciKilo;
    EditText kullaniciYas;

    Switch switchGender ;
    Switch switcMode;

    Button saveSharedButton;

    public static final String userPrefs = "User Prefs";
    public static final String userName = "Default Name";
    public static final String userBoy = "Default Boy";
    public static final String userKilo = "Default Kilo";
    public static final String userYas = "Default Yas";

    public static final String genderBool = "0" ;
    public static final String appModeBool = "0" ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici);

        // EditText
        kullaniciAdi = findViewById(R.id.kullaniciAdi);
        kullaniciBoy = findViewById(R.id.boy);
        kullaniciKilo = findViewById(R.id.kilo);
        kullaniciYas = findViewById(R.id.yas);

        //Switch
        switchGender = findViewById(R.id.genderSwitch);
        switcMode = findViewById(R.id.appModeSwitch);


        sharedPreferences = getSharedPreferences(userPrefs, Context.MODE_PRIVATE);

        saveSharedButton = findViewById(R.id.saveShared);


        saveSharedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kAd = kullaniciAdi.getText().toString();
                String kBoy = kullaniciBoy.getText().toString();
                String kKilo = kullaniciKilo.getText().toString();
                String kYas = kullaniciYas.getText().toString();

                String kGender,kMode ;

                if(switchGender.isChecked()){
                    kGender = "1";
                }
                else{
                    kGender = "0";
                }

                if(switcMode.isChecked()){
                    kMode = "1";
                }
                else{
                    kMode = "0";
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();

                //editor.putString(id,);
                editor.putString(userName,kAd);
                editor.putString(userBoy,kBoy);
                editor.putString(userKilo,kKilo);
                editor.putString(userYas,kYas);
                editor.putString(genderBool,kGender);
                editor.putString(appModeBool,kMode);
                editor.commit();
                Toast.makeText(KullaniciActivity.this, "Saved", Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sh = getSharedPreferences(userPrefs,MODE_APPEND);


        String s1 = sh.getString(userName,"0");
        String s2 = sh.getString(userBoy,"00");
        String s3 = sh.getString(userKilo,"000");
        String s4 = sh.getString(userYas,"0000");

        String gender = sh.getString(genderBool,"10");
        String appMode = sh.getString(appModeBool,"10");

        kullaniciAdi.setText(s1);
        kullaniciBoy.setText(s2);
        kullaniciKilo.setText(s3);
        kullaniciYas.setText(s4);

        //*********
        if(gender == "0"){
            switchGender.setChecked(false);
        }
        else{
            switchGender.setChecked(true);

        }

        if(appMode == "0"){
            switcMode.setChecked(false);
        }
        else{
            switcMode.setChecked(true);
        }




    }


}
