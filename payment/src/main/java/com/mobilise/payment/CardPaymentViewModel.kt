package com.mobilise.payment

import androidx.lifecycle.ViewModel
import com.mobilise.payment.data.TransactionRepository
import com.mobilise.payment.data.model.TransactionData
import com.mobilise.payment.util.StateData

class CardPaymentViewModel(
    private val transactionRepo: TransactionRepository
) : ViewModel() {

    suspend fun getTransactionData(transactionId: Long): StateData<TransactionData> {
        return transactionRepo.getTransactionData(transactionId)
    }

    suspend fun makePayment(transactionId: Long): StateData<TransactionData> {
        return transactionRepo.makePayment(transactionId)
    }

    suspend fun makePaymentReturnError(transactionId: Long): StateData<TransactionData> {
        return transactionRepo.makePaymentReturnError(transactionId)
    }

}