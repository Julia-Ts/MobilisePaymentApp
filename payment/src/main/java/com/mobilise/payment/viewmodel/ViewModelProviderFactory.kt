package com.mobilise.payment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobilise.payment.data.TransactionRepository

class ViewModelProviderFactory(
    private val transactionRepo: TransactionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardPaymentViewModel(transactionRepo) as T
    }

}