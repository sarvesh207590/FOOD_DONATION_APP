package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.home_button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click_animation);//Load animation
                v.startAnimation(animation);//Start animation
            }
                                                                                                                                  });

    }




    public void NGO1(View view) {
        Intent intent = new Intent(this, NGO1.class);
        startActivity(intent);
    }

    public void NGO2(View view) {
        Intent intent = new Intent(this, NGO2.class);
        startActivity(intent);
    }

    public void NGO3(View view) {
        Intent intent = new Intent(this, NGO3.class);
        startActivity(intent);
    }

    public void NGO4(View view) {
        Intent intent = new Intent(this, NGO4.class);
        startActivity(intent);
    }

    public void PROFILE(View view) {
        Intent intent = new Intent(this, updateProfileActivity.class);
        startActivity(intent);
    }



}