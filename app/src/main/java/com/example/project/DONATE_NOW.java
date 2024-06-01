package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DONATE_NOW extends AppCompatActivity {

    public static  final String EXTRA_NAME="com.example.multiscreen1.extra.NAME";// for same name
    public static final String EXTRA_CHECKBOX_DATA = "com.example.multiscreen1.extra.CHECKBOX_DATA";//for checkboxes input

    private LinearLayout cookedFoodOptionsLayout, rawFoodOptionsLayout;
    private EditText otherCookedFoodEditText, otherRawFoodEditText, donateCashEditText;
    private CheckBox cookedFoodCheckBox, rawFoodCheckBox, otherCookedFoodCheckBox, otherRawFoodCheckBox, donateCashCheckBox,dalchaval,chapatibhaji,puribhaji,cereals,oil,vegetables;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//         CheckBox checkBox1, checkBox2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_now);

        Button button7 = findViewById(R.id.donatecash);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DONATE_NOW.this, cashdonate.class);
                startActivity(intent);
            }
        });


//<!-- ngo chya name saathi start-->
        textView = findViewById(R.id.ngoNameEditText);
        Intent intent=getIntent();
        String name=intent.getStringExtra(NGO1.EXTRA_NAME1);
        String name1=intent.getStringExtra(NGO2.EXTRA_NAME2);
        String name2=intent.getStringExtra(NGO3.EXTRA_NAME3);
        String name3=intent.getStringExtra(NGO4.EXTRA_NAME4);

textView.setText(name);
textView.setText(name1);
textView.setText(name2);
textView.setText(name3);

//<!-- ngo chya name saathi sampala-->

 //food items name one page to another page start


        cookedFoodOptionsLayout = findViewById(R.id.cookedFoodOptionsLayout);
        rawFoodOptionsLayout = findViewById(R.id.rawFoodOptionsLayout);
        otherCookedFoodEditText = findViewById(R.id.otherCookedFoodEditText);
        otherRawFoodEditText = findViewById(R.id.otherRawFoodEditText);
        donateCashEditText = findViewById(R.id.donateCashEditText);

        rawFoodCheckBox = findViewById(R.id.rawFoodCheckBox);
        otherCookedFoodCheckBox = findViewById(R.id.otherCookedFoodCheckBox);
        otherRawFoodCheckBox = findViewById(R.id.otherRawFoodCheckBox);
        donateCashCheckBox = findViewById(R.id.donateCashCheckBox);








//1st checkbox
        cookedFoodCheckBox = findViewById(R.id.cookedFoodCheckBox);
        cookedFoodCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cookedFoodOptionsLayout.setVisibility(View.VISIBLE);
            } else {
                cookedFoodOptionsLayout.setVisibility(View.GONE);
                otherCookedFoodCheckBox.setChecked(false);
                otherCookedFoodEditText.setVisibility(View.GONE);
            }
        });


//2st checkbox
        rawFoodCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rawFoodOptionsLayout.setVisibility(View.VISIBLE);
            } else {
                rawFoodOptionsLayout.setVisibility(View.GONE);
                otherRawFoodCheckBox.setChecked(false);
                otherRawFoodEditText.setVisibility(View.GONE);
            }
        });
//dusre  checkboxes inside cooked food
        otherCookedFoodCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                otherCookedFoodEditText.setVisibility(View.VISIBLE);
            } else {
                otherCookedFoodEditText.setVisibility(View.GONE);
            }
        });

//dusre  checkboxes inside raw food

        otherRawFoodCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                otherRawFoodEditText.setVisibility(View.VISIBLE);
            } else {
                otherRawFoodEditText.setVisibility(View.GONE);
            }
        });

        donateCashCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                donateCashEditText.setVisibility(View.VISIBLE);
            } else {
                donateCashEditText.setVisibility(View.GONE);
            }
        });
    }
    TextView textview2;

    public void Donatenow2(View view) {
        Intent intent = new Intent(this, Donate_Now2.class);

//        heading same rahanyasathi start
        textview2=findViewById(R.id.ngoNameEditText);
        String nametext=textview2.getText().toString();

        intent.putExtra(EXTRA_NAME,nametext);
        //        heading same rahanyasathi over

        //he khalche names first var declare kelet
        dalchaval=findViewById(R.id.dal_chaval);
        chapatibhaji=findViewById(R.id.chapati_bhaji);
        puribhaji=findViewById(R.id.puri_bhaji);


        cereals=findViewById(R.id.cereals);
        oil=findViewById(R.id.oil);
        vegetables=findViewById(R.id.vegetables);


        StringBuilder checkBoxData = new StringBuilder(); //string builder ha items la append karayach kam karto

        //jar kahich click nahi kela tar
        if(dalchaval.isChecked()||chapatibhaji.isChecked()||puribhaji.isChecked()||(otherCookedFoodCheckBox.isChecked()&&(!TextUtils.isEmpty(otherCookedFoodEditText.getText().toString())))||cereals.isChecked()||oil.isChecked()||vegetables.isChecked()||(otherRawFoodCheckBox.isChecked()&&(!TextUtils.isEmpty(otherRawFoodEditText.getText().toString())))||donateCashCheckBox.isChecked()&&(!TextUtils.isEmpty(donateCashEditText.getText().toString())))
        {
            //cooked food
            if (cookedFoodCheckBox.isChecked()) {
                if (dalchaval.isChecked()) {
                    checkBoxData.append("Dal chaval").append("\n");
                }
                if (chapatibhaji.isChecked()) {
                    checkBoxData.append("Chapati Bhaji").append("\n");
                }
                if (puribhaji.isChecked()) {
                    checkBoxData.append("Puri bhaji").append("\n");
                }
                if (otherCookedFoodCheckBox.isChecked()&&(!TextUtils.isEmpty(otherCookedFoodEditText.getText().toString()))) {
                    checkBoxData.append("Other Cooked Food: ").append(otherCookedFoodEditText.getText().toString()).append("\n");
                }
            }

            if (rawFoodCheckBox.isChecked()) {
                if (cereals.isChecked()) {
                    checkBoxData.append("Cereals").append("\n");
                }
                if (oil.isChecked()) {
                    checkBoxData.append("Oil").append("\n");
                }
                if (vegetables.isChecked()) {
                    checkBoxData.append("Vegetables").append("\n");
                }
                if (otherRawFoodCheckBox.isChecked()&&(!TextUtils.isEmpty(otherRawFoodEditText.getText().toString()))) {
                    checkBoxData.append("Other Raw Food: ").append(otherRawFoodEditText.getText().toString()).append("\n");
                }
            }

            if (donateCashCheckBox.isChecked()&&(!TextUtils.isEmpty(donateCashEditText.getText().toString()))) {
                checkBoxData.append("Donate Cash: ").append(donateCashEditText.getText().toString()).append("Rs").append("\n");
            }


            // Put checkbox data into the intent
            intent.putExtra(EXTRA_CHECKBOX_DATA, checkBoxData.toString());
            startActivity(intent);
        }
//jar kahich click nahi kela tar
        else {
            // Display toast message if no checkbox is checked and no other fields have values
            Toast.makeText(this, "Please select at least one item", Toast.LENGTH_SHORT).show();
        }
    }

    }






















