package com.mobilise.paymentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mobilise.payment.CardPaymentScreen
import com.mobilise.payment.CardPaymentViewModel
import com.mobilise.paymentapp.ui.theme.MobilisePaymentAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobilisePaymentAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    //Mocked values:
    val mockTransactionId = 1L
    //TODO: Use DI if App grows bigger
    val paymentViewModel = CardPaymentViewModel()
    CardPaymentScreen(mockTransactionId, paymentViewModel)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MobilisePaymentAppTheme {
        Content()
    }
}