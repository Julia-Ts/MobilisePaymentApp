package com.mobilise.payment.data

import android.util.Log
import com.mobilise.payment.data.model.TransactionData
import com.mobilise.payment.util.StateData
import kotlinx.coroutines.delay

//TODO: implement storing and updating Transaction in db instead of a mock
object TransactionRepository {

    private const val MOCK_TRANSACTION_MONEY_AMOUNT = 255.50F
    private const val MOCK_TRANSACTION_TITLE = "Item To Purchase"

    //Simulating retrieving transaction data
    suspend fun getCurrentTransactionData(): StateData<TransactionData> {
        delay(2000L)
        val transaction: TransactionData
        try {
            transaction = TransactionData(
                transactionTitle = MOCK_TRANSACTION_TITLE,
                moneyAmount = MOCK_TRANSACTION_MONEY_AMOUNT,
                isSuccessful = false
            )
        } catch (e: Exception) {
            return StateData.Error("Transaction data is not found")
        }
        return StateData.Success(transaction)
    }

    suspend fun makePayment(): StateData<TransactionData> {
        val transaction: StateData<TransactionData>
        try {
            transaction = getCurrentTransactionData()
        } catch (e: Exception) {
            Log.e(this.javaClass.toString(), e.message ?: "Payment failed")
            return StateData.Error("Payment failed. Please, try again")
        }
        if (transaction is StateData.Success) {
            transaction.data?.isSuccessful = true
        }
        return transaction
    }

    //For demonstration purposes
    suspend fun makePaymentReturnError(): StateData<TransactionData> {
        delay(1000L)
        return StateData.Error("Payment failed. Please, try again")
    }

}