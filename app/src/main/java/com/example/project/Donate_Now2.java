package com.example.project;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Donate_Now2 extends AppCompatActivity {

    TextView textview;
    private String name;
    private String fooditems;

    TextView textView;
    EditText editTextDate, editTextTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_now2);
        TextView textview2;

        //intent ghetala
        textview=findViewById(R.id.ngoNameEditText);
        Intent intent=getIntent();
        name=intent.getStringExtra(DONATE_NOW.EXTRA_NAME); // Assign value to class-level variable
        textview.setText(name);
        //intent close

        textview2=findViewById(R.id.items_from_1);
        fooditems=intent.getStringExtra(DONATE_NOW.EXTRA_CHECKBOX_DATA);
        textview2.setText(fooditems);

        editTextDate = findViewById(R.id.edit_text_date);
        editTextTime = findViewById(R.id.edit_text_time);
        calendar = Calendar.getInstance();

        Button button;
        button=findViewById(R.id.sampla);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jar time ani date enter nay kela tar
                if(!TextUtils.isEmpty(editTextDate.getText().toString())&&!TextUtils.isEmpty(editTextTime.getText()))
                {
                    Toast.makeText(Donate_Now2.this, " THANK YOU FOR DONATING \n Within One hour You have a Call from NGO", Toast.LENGTH_LONG).show();
                    sendDonationNotification();
                    button.setEnabled(false);
                }
                //jar date ani time enter keli nahi tar
                else {

                    Toast.makeText(Donate_Now2.this, "Please select Time and Date", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showDatePickerDialog(View view) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // method to show date dialoge picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update EditText with selected date
                        editTextDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);

        // Show DatePickerDialog
        datePickerDialog.show();
    }

    // Method to show TimePickerDialog
    public void showTimePickerDialog(View view) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editTextTime.setText(String.format("%02d:%02d", hourOfDay, minute));// Update EditText with selected time
                    }
                }, hour, minute, true);
        timePickerDialog.show();// Show TimePickerDialog
    }


    //declare
    private void sendDonationNotification() {
        String title = name+"\n"; //ngo cha name
//horizontali arrange karanyasathi list
        String nam = fooditems;
        String[] items = nam.split("\n");
        String result = "";
        for (int i = 0; i < items.length; i++) {
            String trimmedItem = items[i].trim();
            result += trimmedItem;
            if (i < items.length - 1) {
                result += ",";
            }
        }
 //horizontali arrange karnyasathi sampala


        String message = "  \nThank You for Donating!...\nWithin One hour you Receive a  call from NGO\n\nYour Donations:"+result;
        NOTIFICATION.createNotification(this, title, message);
    }
}
