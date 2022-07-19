package com.mobilise.paymentapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.mobilise.payment.data.TransactionRepository
import com.mobilise.payment.ui.CardPaymentScreen
import com.mobilise.payment.viewmodel.CardPaymentViewModel
import com.mobilise.payment.viewmodel.ViewModelProviderFactory
import com.mobilise.paymentapp.ui.theme.PaymentAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //TODO: Use DI if App grows bigger
                    val repo = TransactionRepository
                    val vmfactory = ViewModelProviderFactory(repo)
                    val vm =
                        ViewModelProvider(this, vmfactory).get(CardPaymentViewModel::class.java)
                    CardPaymentScreen(vm)
                }
            }
        }
    }
}