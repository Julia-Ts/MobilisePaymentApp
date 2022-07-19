package com.mobilise.payment.data.model

import java.io.Serializable

class TransactionData(
    val transactionTitle: String,
    val moneyAmount: Float,
    var isSuccessful: Boolean
) : Serializable