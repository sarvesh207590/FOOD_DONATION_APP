package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NGO2 extends AppCompatActivity {
    public static  final String EXTRA_NAME2 ="com.example.multiscreen1.extra.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void donate(View view) {

        Intent intent = new Intent(this, DONATE_NOW.class);
        String name=("Akshaya Patra Foundation").toString();
        intent.putExtra(EXTRA_NAME2,name);
        startActivity(intent);
    }
}
