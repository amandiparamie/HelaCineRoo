package com.ousl.eei4369designproject_s22010144;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewFilmCategories;
    private ImageView imageViewProfile;
    private TextView textViewReviewsnInteractions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFilmCategories = findViewById(R.id.textViewFilmCategories);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        textViewReviewsnInteractions = findViewById(R.id.textViewReviewsnInteractions);

        // Set OnClickListener for navigate to Film Categories Screen
        textViewFilmCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FilmCategoriesActivity.class));
            }
        });

        // Set OnClickListener for navigate to Profile Screen
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        // Set OnClickListener for navigate to Reviews and Interactions Screen
        textViewReviewsnInteractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReviewsandIntActivity.class));
            }
        });
    }
}
