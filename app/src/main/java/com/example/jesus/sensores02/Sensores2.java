package com.example.jesus.sensores02;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sensores2 extends AppCompatActivity implements SensorEventListener{
int contador;
double x=0;
double y=0;
double z=0;
double a=0;
double amax=0;
double gravedad = SensorManager.STANDARD_GRAVITY;
TextView tvax;
TextView tvay;
TextView tvaz;
TextView tva;
TextView tvaMax;
TextView tvG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores2);
        tvax = (TextView) findViewById(R.id.aceleracionX);
        tvay = (TextView) findViewById(R.id.aceleracionY);
        tvaz = (TextView) findViewById(R.id.aceleracionZ);
        tva = (TextView) findViewById(R.id.aceleracion);
        tvaMax = (TextView) findViewById(R.id.aceleracionMaxima);
        tvG = (TextView) findViewById(R.id.gravedad);

        SensorManager SensorManager = (android.hardware.SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor acelerometro = SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_FASTEST);
        new MiAsyncTask().execute();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        a = Math.sqrt(x*x+y*y+z*z);
        if (a > amax) amax = a;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    class MiAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            while(true){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }contador++;
                publishProgress();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            tvax.setText("" + x);
            tvay.setText("" + y);
            tvaz.setText("" + z);
            tva.setText("" + a);
            tvaMax.setText("" + amax);
            tvG.setText("" + gravedad);
            tvG.append("\n" + "CONTADOR: " + contador);
        }
    }
}
