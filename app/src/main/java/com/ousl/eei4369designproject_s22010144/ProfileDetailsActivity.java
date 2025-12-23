package com.ousl.eei4369designproject_s22010144;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileDetailsActivity extends AppCompatActivity {

    EditText editTextNewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_details);

        editTextNewEmail = findViewById(R.id.editTextNewUsername);

        // Update email button click listener
        Button buttonUpdateEmail = findViewById(R.id.buttonUpdateEmail);
        buttonUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail();
            }
        });

        Button buttonLogOut = findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        Button buttonDeleteAccount = findViewById(R.id.buttonDeleteAccount);
        buttonDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAccountDialog();
            }
        });
    }

    private void updateEmail() {
        // Get the new email
        String newEmail = editTextNewEmail.getText().toString().trim();

        // Check if the new email is empty
        if (newEmail.isEmpty()) {
            editTextNewEmail.setError("Please enter a new email");
            editTextNewEmail.requestFocus();
            return;
        }

        // Retrieve the old email from the database
        String oldEmail = getOldEmailFromDatabase();

        // Update the email in SharedPreferences and the database
        boolean isUpdated = updateEmailInSharedPreferences(newEmail, oldEmail);

        if (isUpdated) {
            // Display the old and new emails
            TextView textViewOldEmail = findViewById(R.id.textViewOldEmail);
            TextView textViewNewEmail = findViewById(R.id.textViewNewEmail);

            // Set the text for both old and new emails
            textViewOldEmail.setText("Old Email: " + oldEmail);
            textViewNewEmail.setText("New Email: " + newEmail);
            textViewNewEmail.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Email updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update email. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private String getOldEmailFromDatabase() {
        // Retrieve the old email from the database
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        return sharedPreferences.getString("user_email", null);
    }

    private boolean updateEmailInSharedPreferences(String newEmail, String oldEmail) {
        // Update the email in the database
        DatabaseHelper db = new DatabaseHelper(this);
        boolean isUpdatedInDb = db.updateEmail(oldEmail, newEmail);

        if (isUpdatedInDb) {
            // Update the email in SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user_email", newEmail);
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    // Method to Log Out
    private void logOut() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(ProfileDetailsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    // Method to show delete account confirmation dialog
    private void showDeleteAccountDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccount();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    // Method to delete account
    private void deleteAccount() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", null);

        if (userEmail != null) {
            DatabaseHelper db = new DatabaseHelper(this);

            if (db.deleteUser(userEmail)) { // Delete user data from the database
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Clear shared preferences
                editor.apply();

                Intent intent = new Intent(ProfileDetailsActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            } else {
                new AlertDialog.Builder(ProfileDetailsActivity.this)
                        .setTitle("Error")
                        .setMessage("Failed to delete the account.")
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        } else {
            new AlertDialog.Builder(ProfileDetailsActivity.this)
                    .setTitle("Error")
                    .setMessage("No user is currently logged in.")
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
