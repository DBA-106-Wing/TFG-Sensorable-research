package com.sensorable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.commons.SensorsProvider;
import com.sensorable.R;
import com.sensorable.activities.AdvancedMenuActivity;

public class DetailedSensorsListActivity extends AppCompatActivity {

    private TextView acceleromterTextView;
    private TextView temperatureTextView;
    private TextView humidityTextView;
    private TextView proximityTextView;
    private TextView lightTextView;
    private Button advancedMenuButton;

    private SensorsProvider sensorsProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_sensors_list);

        // We can pass messages from an intent to other, for now we wont

       initializeAttributtesFromUI();

        sensorsProvider = new SensorsProvider(this);
    }

    private void initializeAttributtesFromUI() {
        acceleromterTextView = (TextView) findViewById(R.id.acceleromterText);
        temperatureTextView = (TextView) findViewById(R.id.temperatureText);
        humidityTextView = (TextView) findViewById(R.id.humidityText);
        proximityTextView = (TextView) findViewById(R.id.proximityText);
        lightTextView = (TextView) findViewById(R.id.lightText);
        advancedMenuButton = (Button) findViewById(R.id.advancedMenuButton);
        advancedMenuButton.setOnClickListener(v -> {
            startActivity(new Intent(
                    this,
                    AdvancedMenuActivity.class)
            );
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeSensors();
    }

    private void initializeSensors() {
        sensorsProvider.subscribeToSensor(Sensor.TYPE_ACCELEROMETER, new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                String values = "(" +
                        sensorEvent.values[0] + ", " +
                        sensorEvent.values[1] + ", " +
                        sensorEvent.values[2] +
                        ")";

                acceleromterTextView.setText(values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        }, SensorManager.SENSOR_DELAY_NORMAL);

        sensorsProvider.subscribeToSensor(Sensor.TYPE_AMBIENT_TEMPERATURE, new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                String values = sensorEvent.values[0] + "ºC";
                temperatureTextView.setText(values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, SensorManager.SENSOR_DELAY_NORMAL);

        sensorsProvider.subscribeToSensor(Sensor.TYPE_RELATIVE_HUMIDITY, new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                String values = sensorEvent.values[0] + " %";
                humidityTextView.setText(values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, SensorManager.SENSOR_DELAY_NORMAL);

        sensorsProvider.subscribeToSensor(Sensor.TYPE_PROXIMITY, new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                String values = sensorEvent.values[0] + " cm";
                proximityTextView.setText(values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, SensorManager.SENSOR_DELAY_NORMAL);

        sensorsProvider.subscribeToSensor(Sensor.TYPE_LIGHT, new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                String values = sensorEvent.values[0] + " lm";
                lightTextView.setText(values);
                Log.i("SENSORS", "LIGHT CHANGED " + sensorEvent.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, SensorManager.SENSOR_DELAY_NORMAL);

        Toast.makeText(this, "Sensor inicializados correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}