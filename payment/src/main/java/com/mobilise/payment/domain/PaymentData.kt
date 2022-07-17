package com.mobilise.payment.domain

class PaymentData(
    transactionId: Long,
    payerData: PayerData,
    moneyAmount: Float,
    isPaymentSuccessful: Boolean
)