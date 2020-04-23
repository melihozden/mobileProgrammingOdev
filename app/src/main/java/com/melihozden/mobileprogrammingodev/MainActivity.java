package com.melihozden.mobileprogrammingodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText userName =  findViewById(R.id.usernameText);
        final EditText password =  findViewById(R.id.passwordText);
        final Button loginButton = findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View arg0) {

                if(checkPassword(userName.getText().toString().trim(),password.getText().toString().trim())){
                    // both true
                    Intent helloAndroidIntent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(helloAndroidIntent);
                }
                else{
                    // false one at least
                    Toast.makeText(MainActivity.this,"Sifre/Kullanıcı Adı yanlis",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean checkPassword(String username, String passwd){

        if(username.equals("admin") && passwd.equals("12345")){
            return true ;
        }
        else{
            return false ;
        }

    }
}
