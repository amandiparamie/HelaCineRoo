package com.ousl.eei4369designproject_s22010144;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ousl.eei4369designproject_s22010144.MovieCategories.ActionMovieActivity;
import com.ousl.eei4369designproject_s22010144.MovieCategories.ComedyMovieListActivity;
import com.ousl.eei4369designproject_s22010144.MovieCategories.HorrorMovieListActivity;
import com.ousl.eei4369designproject_s22010144.MovieCategories.SuspenseMovieListActivity;
import com.ousl.eei4369designproject_s22010144.MovieCategories.ThrillerMovieListActivity;

public class FilmCategoriesActivity extends AppCompatActivity {

    private Button buttonAction, buttonSuspense, buttonRomance, buttonThriller, buttonHorror, buttonComedy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_categories);

        buttonAction = findViewById(R.id.buttonAction);
        buttonSuspense = findViewById(R.id.buttonSuspense);
        buttonRomance = findViewById(R.id.buttonRomance);
        buttonThriller = findViewById(R.id.buttonThriller);
        buttonHorror = findViewById(R.id.buttonHorror);
        buttonComedy = findViewById(R.id.buttonComedy);

        // Set OnClickListener for each button to navigate to corresponding movie category activity
        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmCategoriesActivity.this, ActionMovieActivity.class));
            }
        });

        buttonSuspense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmCategoriesActivity.this, SuspenseMovieListActivity.class));
            }
        });

        buttonRomance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmCategoriesActivity.this, MovieDetails.class));
            }
        });

        buttonThriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmCategoriesActivity.this, ThrillerMovieListActivity.class));
            }
        });

        buttonHorror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmCategoriesActivity.this, HorrorMovieListActivity.class));
            }
        });

        buttonComedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmCategoriesActivity.this, ComedyMovieListActivity.class));
            }
        });

        // Navigate to Home
        ImageView imageViewHome = findViewById(R.id.imageViewhome);
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilmCategoriesActivity.this, MainActivity.class));
            }
        });
    }
}
