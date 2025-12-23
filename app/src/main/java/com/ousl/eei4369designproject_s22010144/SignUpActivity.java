package com.ousl.eei4369designproject_s22010144;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.ousl.eei4369designproject_s22010144.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToLogin();
            }
        });
    }

    // Method to handle the Sign Up process
    private void signUp() {
        // Retrieving user input from EditText fields
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString();
        String confirmPassword = binding.editTextConfirmPassword.getText().toString();

        // Validating if all fields are filled out
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields must be filled out!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validating if passwords match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Checking if the user already exists in the database
        boolean userExists = databaseHelper.checkEmail(email);
        if (userExists) {
            Toast.makeText(this, "User already exists, Login Please!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Inserting user data into the database
        boolean insertSuccess = databaseHelper.insertData(email, password);
        if (insertSuccess) {
            Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
            redirectToLogin();
        } else {
            Toast.makeText(this, "Sign Up Failed!", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to redirect to the login activity
    private void redirectToLogin() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
