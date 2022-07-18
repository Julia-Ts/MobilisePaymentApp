package com.mobilise.payment.data.model

class TransactionData(
    val transactionId: Long,
    val transactionTitle: String,
    val moneyAmount: Float,
    var isSuccessful: Boolean
)