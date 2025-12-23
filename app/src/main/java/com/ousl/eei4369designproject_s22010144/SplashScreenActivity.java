package com.ousl.eei4369designproject_s22010144;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        TextView getStartedText = findViewById(R.id.textViewGetStarted);
        getStartedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecting to the Sign Up
                startActivity(new Intent(SplashScreenActivity.this, SignUpActivity.class));
                finish();
            }
        });
    } 
}
