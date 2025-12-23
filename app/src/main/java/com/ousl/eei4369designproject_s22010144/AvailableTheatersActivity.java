package com.ousl.eei4369designproject_s22010144;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class AvailableTheatersActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private static final String TAG = "AvailableTheatersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_theaters);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        TextView textViewLiveLocation = findViewById(R.id.textViewLiveLocation);
        textViewLiveLocation.setOnClickListener(view -> {
            // Check if location permission is granted
            if (ContextCompat.checkSelfPermission(AvailableTheatersActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Request location permission if not granted
                ActivityCompat.requestPermissions(AvailableTheatersActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
            } else {
                Intent intent = new Intent(AvailableTheatersActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to get last known location
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        // Retrieve latitude and longitude
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        String locationText = "Latitude: " + latitude + ", Longitude: " + longitude;
                        // Handle case when location is not available
                        Toast.makeText(AvailableTheatersActivity.this, locationText, Toast.LENGTH_LONG).show();
                        Log.d(TAG, locationText);
                    } else {
                        Toast.makeText(AvailableTheatersActivity.this, "Location not available",
                                Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Location not available");
                    }
                });
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            // If permission granted, get last known location
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                // If permission denied, display message
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
