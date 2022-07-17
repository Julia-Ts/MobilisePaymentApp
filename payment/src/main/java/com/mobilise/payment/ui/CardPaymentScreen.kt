package com.mobilise.payment.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardPaymentScreen(
    transactionId: Long
//    viewModel: CardPaymentViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val isCardDataValid = remember { mutableStateOf(false) }
        Text(
            text = "Is form valid: ${isCardDataValid.value}",
            modifier = Modifier.align(Alignment.TopCenter)
        )
        CardInfoView(
            isCardDataValid,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(top = 32.dp)
        )
        Button(
            onClick = {
                /*TODO*/
//                delay(1000L)
                println("Payment is done")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.5f),
            enabled = isCardDataValid.value
        ) {
            Text(text = "Pay")
        }
    }

}