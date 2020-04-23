package com.melihozden.mobileprogrammingodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {


    private ListView listView ;
    private List<Sensor> listSensor ;
    private TextView textView ;
    private List<String> listString ;
    private ArrayAdapter arrayAdapter ;
    private SensorManager sensorManager ;
    private Sensor lightSensor ;

    //Gyro
    private LinearLayout linearLayout ;
    private TextView timerView ;
    private Sensor accSensor ;
    private float[] mGravity ;
    private float mAcc;
    private float mAccCurrent;
    private float mAccLast;

    //Timer
    private CountDownTimer timer ;
    private boolean control ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        listView = findViewById(R.id.sensorListView);
        textView = findViewById(android.R.id.text1);

        timerView = findViewById(R.id.counterView);

        listString = new ArrayList<String>();

        sensorManager = (SensorManager)  getSystemService(Context.SENSOR_SERVICE);
        listSensor = sensorManager.getSensorList(Sensor.TYPE_ALL) ;


        for(int i = 0 ;i<listSensor.size();i++){
            listString.add(listSensor.get(i).getName());
        }
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) ;
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) ;

        mAcc = 0.00f ;
        mAccCurrent = SensorManager.GRAVITY_EARTH ;
        mAccLast = SensorManager.GRAVITY_EARTH ;
        control = false ;


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL) ;
        sensorManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_UI) ;
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
        public void onSensorChanged(SensorEvent event){
        if (event.sensor.getType() == Sensor.TYPE_LIGHT){
            if(event.values[0] > 15000.0){
                listView.setBackgroundColor(Color.BLACK);
                timerView.setBackgroundColor(Color.BLACK);
                timerView.setTextColor(Color.WHITE);
                arrayAdapter = new ArrayAdapter<String>(SensorActivity.this,
                        R.layout.sensor_list_item,
                        R.id.txtItem,listString
                ){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        View view = super.getView(position,convertView,parent);
                        ((TextView)view.findViewById(R.id.txtItem)).setTextColor(Color.WHITE);
                        return view ;
                    }
                };

                listView.setAdapter(arrayAdapter);
            }
            else{
                listView.setBackgroundColor(Color.WHITE);
                timerView.setBackgroundColor(Color.WHITE);
                timerView.setTextColor(Color.BLACK);
                arrayAdapter = new ArrayAdapter<String>(SensorActivity.this,
                        R.layout.sensor_list_item,
                        R.id.txtItem,listString
                ){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        View view = super.getView(position,convertView,parent);
                        ((TextView)view.findViewById(R.id.txtItem)).setTextColor(Color.BLACK);
                        return view ;
                    }
                };

                listView.setAdapter(arrayAdapter);
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();

            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];

            mAccLast = mAccCurrent ;
            mAccCurrent = (float) Math.sqrt(x*x + y*y + z*z);
            float delta = mAccCurrent - mAccLast ;
            mAcc = mAcc * 0.9f + delta ;
            if(mAcc > 1){
                control = false;
            }
            else{
                int min = 15000;
                if(!control){
                    counter(min);
                }
            }
        }
    }

    private void counter(long min){
        control = true ;
        if(timer != null){
            timer.cancel();
            timer = null;
        }

        timer = new CountDownTimer(min,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60 ;
                timerView.setText(String.format("%d",seconds));
            }

            @Override
            public void onFinish() {

                Toast.makeText(getApplicationContext(),"Time is Up",
                        Toast.LENGTH_LONG).show();
                control = false ;
                finishAffinity();
                System.exit(0);
            }
        };
        timer.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    /// GYRO Hareketleri buraya gelecek



}
