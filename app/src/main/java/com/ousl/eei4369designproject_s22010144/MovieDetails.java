package com.ousl.eei4369designproject_s22010144;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetails extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private int shakeCount = 0;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Button buttonTrailer = findViewById(R.id.buttonTrailer);
        Button buttonAvailableTheaters = findViewById(R.id.buttonAvailableTheaters);
        Button buttonReviews = findViewById(R.id.buttonReviews);

        buttonTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display movie trailer as a popup window
                dialog = new Dialog(MovieDetails.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.video_popup_layout);

                VideoView videoView = dialog.findViewById(R.id.videoView);

                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.satrailer;
                Uri uri = Uri.parse(videoPath);
                videoView.setVideoURI(uri);

                // Media controller to control playback
                MediaController mediaController = new MediaController(MovieDetails.this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);

                // Start playing the trailer
                videoView.start();
                dialog.show();
            }
        });

        buttonAvailableTheaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Available Theaters Screen
                startActivity(new Intent(MovieDetails.this, AvailableTheatersActivity.class));
            }
        });

        buttonReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Reviews and Interactions Screen
                startActivity(new Intent(MovieDetails.this, ReviewsandIntActivity.class));
            }
        });

        // Initialize sensor manager and Accelerometer
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accelerometer != null) {
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister sensor listener to avoid memory leaks
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Detect shake gesture
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            double acceleration = Math.sqrt(x * x + y * y + z * z);
            if (acceleration > 12) {
                shakeCount++;
                if (shakeCount >= 3 && dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    shakeCount = 0;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
