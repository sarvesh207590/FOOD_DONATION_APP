package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateProfileActivity extends AppCompatActivity {

    EditText edemail, edUsername, edaddress, edpassword;

    Button btninsert , btnupdate, btndelete , btnback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        edemail = findViewById(R.id.editTextemailpro);
        edUsername = findViewById(R.id.editTextUsernamepro);
//        edaddress = findViewById(R.id.editTextaddresspro);
        edpassword = findViewById(R.id.editTextpasspro);
        btninsert = findViewById(R.id.buttonInsert);
        btnupdate = findViewById(R.id.buttonUpdate);
        btndelete = findViewById(R.id.buttonDelete);
        btnback = findViewById(R.id.button5);

        Button button = findViewById(R.id.profile_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click_animation);
                v.startAnimation(animation);
            }
        });


//created BY RSAM
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        EditText emailEditText = findViewById(R.id.editTextemailpro);
        EditText passwordEditText = findViewById(R.id.editTextpasspro);

        Button deleteButton = findViewById(R.id.buttonDelete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check if email and password are not empty
                if (!email.isEmpty() && !password.isEmpty()) {
                    // Call deleteRecord function
                    boolean deleted = dbHelper.deleteRecord(email, password);
                    if (deleted) {
                        Toast.makeText(updateProfileActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();

                        // iTHE INTENT PASS KARA KONITARY DIRECT LOGIN MADHYE JANYASATHI
                        Intent intent = new Intent(updateProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                        // You can finish() the activity or do other operations here
                    } else {
                        Toast.makeText(updateProfileActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(updateProfileActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
//created BY SAM over
        // UDATE SATHI
        DatabaseHelper dbhelper = new DatabaseHelper(this);

// check karanyasathi
        Button updatePasswordButton = findViewById(R.id.update_password);
        EditText editTextNewPassword = findViewById(R.id.editTextNewPassword);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (dbhelper.checkUser(email, password)) {
                    editTextNewPassword.setVisibility(View.VISIBLE);
                    updatePasswordButton.setVisibility(View.VISIBLE);


                } else {
                    Toast.makeText(updateProfileActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // password change karanyasathi

        updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = editTextNewPassword.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                if (!newPassword.isEmpty() && !email.isEmpty()) {
                    dbHelper.updatePassword(email, newPassword);
                    Toast.makeText(updateProfileActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                    editTextNewPassword.setText("");

                    emailEditText.setText("");    //empty karel password and email chi field optional ahe kadhayacha asla tar kadh
                    passwordEditText.setText("");


                    editTextNewPassword.setVisibility(View.GONE);
                    updatePasswordButton.setVisibility(View.GONE);
                    // Clear the editText after updating the password

                } else {
                    Toast.makeText(updateProfileActivity.this, "Please enter email and new password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    // UPDATE SATHI OVER
    public void HOME(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void Login(View view) {

        startActivity(new Intent(this,LoginActivity.class));
    }
    public void Register(View view) {

        startActivity(new Intent(this,SignupActivity.class));
    }

}