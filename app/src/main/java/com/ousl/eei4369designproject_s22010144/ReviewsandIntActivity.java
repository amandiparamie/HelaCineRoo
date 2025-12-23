package com.ousl.eei4369designproject_s22010144;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewsandIntActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewsandint);

        ImageView imageViewSA = findViewById(R.id.imageViewSA);

        imageViewSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Movie Details Screen
                Intent intent = new Intent(ReviewsandIntActivity.this, MovieDetails.class);
                startActivity(intent);
            }
        });
    }
}
