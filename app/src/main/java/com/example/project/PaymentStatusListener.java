package com.example.project;

import dev.shreyaspatil.easyupipayment.model.TransactionDetails;

public interface PaymentStatusListener {
    void onTransactionCompleted(TransactionDetails transactionDetails);

}
