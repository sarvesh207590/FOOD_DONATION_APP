package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dev.shreyaspatil.easyupipayment.EasyUpiPayment;
import dev.shreyaspatil.easyupipayment.exception.AppNotFoundException;
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener;
import dev.shreyaspatil.easyupipayment.model.TransactionDetails;

public class cashdonate extends AppCompatActivity implements PaymentStatusListener, cashdonate1 {

    // initializing variables for our edit text and button.
    private EditText amountEdt, upiEdt, nameEdt, descEdt;
    private TextView transactionDetailsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashdonate);

        // initializing all our variables.
        amountEdt = findViewById(R.id.idEdtAmount);
        upiEdt = findViewById(R.id.idEdtUpi);
        nameEdt = findViewById(R.id.idEdtName);
        descEdt = findViewById(R.id.idEdtDescription);
        Button makePaymentBtn = findViewById(R.id.idBtnMakePayment);
        transactionDetailsTV = findViewById(R.id.idTVTransactionDetails);

        // on below line we are adding click listener for our payment button.
        makePaymentBtn.setOnClickListener(v -> {
            // on below line we are getting data from our edit text.
            String amount = amountEdt.getText().toString();
            String upi = upiEdt.getText().toString();
            String name = nameEdt.getText().toString();
            String desc = descEdt.getText().toString();
            // on below line we are validating our text field.
            // if the edit text is not empty then
            // we are calling method to make payment.
            if (TextUtils.isEmpty(amount) || TextUtils.isEmpty(upi) || TextUtils.isEmpty(name) || TextUtils.isEmpty(desc)) {
                Toast.makeText(cashdonate.this, "Please enter all the details..", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    // Displaying a toast message when the transaction is initiated.
                    Toast.makeText(cashdonate.this, "Transaction is being processed...", Toast.LENGTH_SHORT).show();
                    makePayment(amount, upi, name, desc);
                    // Transitioning to the main activity after the transaction is initiated.
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    // Finishing the current activity to prevent going back to it using the back button.
                    finish();
                } catch (AppNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    private void makePayment(String amount, String upi, String name, String desc) throws AppNotFoundException {
        // on below line we are getting date and then we are setting this date as transaction id.
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String transcId = df.format(c);

        // on below line we are calling an easy payment method and passing
        // all parameters to it such as upi id,name, description and others.
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder(this)
                // on below line we are adding upi id.
                .setPayeeVpa(upi)
                // on below line we are setting name to which we are making payment.
                .setPayeeName(name)
                // on below line we are passing transaction id.
                .setTransactionId(transcId)
                // on below line we are passing transaction ref id.
                .setTransactionRefId(transcId)
                // on below line we are adding description to payment.
                .setDescription(desc)
                // on below line we are passing amount which is being paid.
                .setAmount(amount)
                // on below line we are calling a build method to build this ui.
                .build();
        // on below line we are calling a start
        // payment method to start a payment.
        easyUpiPayment.startPayment();
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        easyUpiPayment.setPaymentStatusListener(this);
    }

    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        // on below line we are getting details about transaction when completed.
        String transcDetails = transactionDetails.getClass() + "\n" + "Transaction ID : " + transactionDetails.getTransactionId();
        transactionDetailsTV.setVisibility(View.VISIBLE);
        // on below line we are setting details to our text view.
        transactionDetailsTV.setText(transcDetails);
    }

    @Override
    public void onTransactionSuccess() {
        // this method is called when transaction is successful and we are displaying a toast message.
        Toast.makeText(this, "Transaction successfully completed..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSubmitted() {
        // this method is called when transaction is done
        // but it may be successful or failure.
        Log.e("TAG", "TRANSACTION SUBMIT");
    }

    @Override
    public void onTransactionFailed() {
        // this method is called when transaction is failure.
        Toast.makeText(this, "Failed to complete transaction", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCancelled() {
        // this method is called when transaction is cancelled.
        Toast.makeText(this, "Transaction cancelled..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAppNotFound() {
        // this method is called when the users device is not having any app installed for making payment.
        Toast.makeText(this, "No app found for making transaction..", Toast.LENGTH_SHORT).show();
    }
}
