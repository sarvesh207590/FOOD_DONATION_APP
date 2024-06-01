package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NGO3 extends AppCompatActivity {

    public static  final String EXTRA_NAME3 ="com.example.multiscreen1.extra.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo3);
    }
    public void donate(View view) {

        Intent intent = new Intent(this, DONATE_NOW.class);
        String name=("Saath Foundation").toString();
        intent.putExtra(EXTRA_NAME3,name);
        startActivity(intent);
    }
}