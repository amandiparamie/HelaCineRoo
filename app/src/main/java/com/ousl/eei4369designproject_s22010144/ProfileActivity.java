package com.ousl.eei4369designproject_s22010144;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private Button buttonLanguages;
    private TextView textProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        buttonLanguages = findViewById(R.id.buttonLanguages);
        buttonLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Languages Screen
                Intent intent = new Intent(ProfileActivity.this, LanguagesActivity.class);
                startActivity(intent);
            }
        });

        textProfile = findViewById(R.id.textProfile);
        textProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Profile Details Screen
                Intent intent = new Intent(ProfileActivity.this, ProfileDetailsActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to Terms & Conditions Screen
        Button buttonTermsnConditions = findViewById(R.id.buttonTermsnConditions);
        buttonTermsnConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, TermsnConditionsActivity.class);
                startActivity(intent);
            }
        });
    }
}
