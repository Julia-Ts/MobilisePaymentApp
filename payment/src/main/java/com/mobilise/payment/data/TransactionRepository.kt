package com.mobilise.payment.data

import android.util.Log
import com.mobilise.payment.data.model.TransactionData
import com.mobilise.payment.util.StateData
import kotlinx.coroutines.delay
import kotlin.random.Random

//TODO: implement storing and updating Transaction in db instead of a mock
object TransactionRepository {

    private lateinit var currentTransaction: TransactionData

    //Simulating retrieving transaction data
    suspend fun getTransactionData(id: Long): StateData<TransactionData> {
        delay(2000L)
        try {
            currentTransaction = TransactionData(
                transactionId = 1L,
                transactionTitle = listOf(
                    "Item To Purchase",
                    "Expensive Thing",
                    "Necessary Stuff",
                    "Desired Product"
                ).random(),
                moneyAmount = Random(5).nextDouble(9.99, 999.99).toFloat(),
                isSuccessful = false
            )
        } catch (e: Exception) {
            return StateData.Error("Transaction $id is not found")
        }
        return StateData.Success(currentTransaction)
    }

    suspend fun makePayment(transactionId: Long): StateData<TransactionData> {
        try {
            setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e(this.javaClass.toString(), e.message ?: "Payment failed")
            return StateData.Error("Payment failed. Please, try again")
        }
        return StateData.Success(currentTransaction)
    }

    //For demonstration purposes
    suspend fun makePaymentReturnError(transactionId: Long): StateData<TransactionData> {
        setTransactionFailed()
        return StateData.Error("Payment failed. Please, try again")
    }

    //Simulating successful payment process
    private suspend fun setTransactionSuccessful() {
        delay(2000L)
        currentTransaction.isSuccessful = true
    }

    //Simulating failed payment process
    private suspend fun setTransactionFailed() {
        delay(2000L)
        currentTransaction.isSuccessful = false
    }

}