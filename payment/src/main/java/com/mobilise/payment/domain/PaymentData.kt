package com.mobilise.payment.domain

/**
 * Assumption: The App supports cards only from certain country/currency
 */
class PaymentData(
    transactionId: Long,
    payerData: PayerData,
    moneyAmount: Float,
    isPaymentSuccessful: Boolean
)