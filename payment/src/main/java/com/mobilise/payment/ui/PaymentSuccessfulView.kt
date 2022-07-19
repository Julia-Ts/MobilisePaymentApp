package com.mobilise.payment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PaymentSuccessfulView(
    modifier: Modifier = Modifier,
    onPaymentSuccessfulGoBackBtnClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Payment was successful. Thank you for your purchase",
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Icon(
            imageVector = Icons.Default.CheckCircle,
            tint = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .size(176.dp)
                .padding(16.dp)
                .align(Alignment.Center),
            contentDescription = null
        )
        Button(
            onClick = { onPaymentSuccessfulGoBackBtnClick.invoke() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Go back"
            )
        }
    }
}