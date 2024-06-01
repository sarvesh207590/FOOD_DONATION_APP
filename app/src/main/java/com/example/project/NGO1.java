package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NGO1 extends AppCompatActivity {
    public static  final String EXTRA_NAME1 ="com.example.multiscreen1.extra.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo1);
    }


    public void donate(View view) {

        Intent intent = new Intent(this, DONATE_NOW.class);
        String name=("Sukarma Charitable trust").toString();
        intent.putExtra(EXTRA_NAME1,name);
        startActivity(intent);
    }

}