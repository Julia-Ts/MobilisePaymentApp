package com.mobilise.payment.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilise.payment.data.TransactionRepository
import com.mobilise.payment.data.model.TransactionData
import com.mobilise.payment.util.StateData
import kotlinx.coroutines.launch

class CardPaymentViewModel(
    private val transactionRepo: TransactionRepository
) : ViewModel() {

    val transactionData = mutableStateOf<TransactionData?>(null)
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf("")
    private val makePaymentFailOrNot = mutableStateOf(false)

    init {
        getCurrentTransactionData()
    }

    private fun getCurrentTransactionData() {
        viewModelScope.launch() {
            isLoading.value = true
            val data = transactionRepo.getCurrentTransactionData()
            when (data) {
                is StateData.Success -> {
                    transactionData.value = data.data
                    isLoading.value = false
                    error.value = ""
                }
                is StateData.Error -> {
                    error.value = data.message ?: "Something went wrong"
                    isLoading.value = false
                }
                is StateData.Loading -> isLoading.value = true
            }
        }
    }

    fun makePayment() {
        viewModelScope.launch() {
            isLoading.value = true
            val data = if (makePaymentFailOrNot.value) {//easy way to simulate failed payment
                transactionRepo.makePaymentReturnError()
            } else {
                transactionRepo.makePayment()
            }
            when (data) {
                is StateData.Success -> {
                    transactionData.value = data.data
                    isLoading.value = false
                    error.value = ""
                }
                is StateData.Error -> {
                    error.value = data.message ?: "Something went wrong"
                    isLoading.value = false
                }
                is StateData.Loading -> isLoading.value = true
            }
        }
    }

    //Simulation of failed transaction
    fun makePaymentFailOrNot(shouldPaymentBeFailed: Boolean) {
        makePaymentFailOrNot.value = shouldPaymentBeFailed
    }

    fun makePaymentReturnError() {
        viewModelScope.launch() {
            isLoading.value = true
            val data = transactionRepo.makePaymentReturnError()
            when (data) {
                is StateData.Success -> {
                    transactionData.value = data.data
                    isLoading.value = false
                    error.value = ""
                }
                is StateData.Error -> {
                    error.value = data.message ?: "Something went wrong"
                    isLoading.value = false
                }
                is StateData.Loading -> isLoading.value = true
            }
        }
    }

    fun restartPaymentProcess() {
        getCurrentTransactionData()
    }

}