package ru.mirea.velikanov.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sensors_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sensors_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SensorManager sensorManager;
    private SensorEventListener accelerometerEventListener;
    private SensorEventListener gyroscopeEventListener;
    private SensorEventListener magneticEventListener;
    private Sensor accelerometerSensor;
    private Sensor gyroscopeSensor;
    private Sensor magneticFieldSensor;

    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private TextView axisX;
    private TextView axisY;
    private TextView axisZ;
    private TextView magneticX;
    private TextView magneticY;
    private TextView magneticZ;

    public Sensors_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sensors_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Sensors_fragment newInstance(String param1, String param2) {
        Sensors_fragment fragment = new Sensors_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelerometerEventListener);
        sensorManager.unregisterListener(gyroscopeEventListener);
        sensorManager.unregisterListener(magneticEventListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(accelerometerEventListener, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(magneticEventListener, magneticFieldSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensors_fragment, container, false);
        sensorManager =
                (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magneticFieldSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if(gyroscopeSensor == null) {
            Toast.makeText(getActivity(), "The Device has no Gyroscope!", Toast.LENGTH_LONG).show();
//            getActivity().finish();
        }

        azimuthTextView = view.findViewById(R.id.textViewAzimuth);
        pitchTextView = view.findViewById(R.id.textViewPitch);
        rollTextView = view.findViewById(R.id.textViewRoll);
        axisX = view.findViewById(R.id.textViewAxisX);
        axisY = view.findViewById(R.id.textViewAxisY);
        axisZ = view.findViewById(R.id.textViewAxisZ);
        magneticX = view.findViewById(R.id.textViewMagneticX);
        magneticY = view.findViewById(R.id.textViewMagneticY);
        magneticZ = view.findViewById(R.id.textViewMagneticZ);

        accelerometerEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    float valueAzimuth = event.values[0];
                    float valuePitch = event.values[1];
                    float valueRoll = event.values[2];
                    azimuthTextView.setText("Azimuth: " + valueAzimuth);
                    pitchTextView.setText("Pitch: " + valuePitch);
                    rollTextView.setText("Roll: " + valueRoll);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    float X = event.values[0];
                    float Y = event.values[1];
                    float Z = event.values[2];
                    axisX.setText("Axis X: " + X);
                    axisY.setText("Axis Y: " + Y);
                    axisZ.setText("Axis Z: " + Z);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        magneticEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    float magneticAxisX = event.values[0];
                    float magneticAxisY = event.values[1];
                    float magneticAxisZ = event.values[2];
                    magneticX.setText("Magnetic Fiels X axis: " + magneticAxisX);
                    magneticY.setText("Magnetic Fiels Y axis: " + magneticAxisY);
                    magneticZ.setText("Magnetic Fiels Z axis: " + magneticAxisZ);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        return view;
    }
}