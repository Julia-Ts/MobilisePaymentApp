package com.mobilise.payment.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mobilise.payment.R
import com.mobilise.payment.data.model.TransactionData
import com.mobilise.payment.viewmodel.CardPaymentViewModel

@Composable
fun CardPaymentScreen(
    viewModel: CardPaymentViewModel
) {
    val transactionData by rememberSaveable { viewModel.transactionData }
    val isLoading by rememberSaveable { viewModel.isLoading }
    val error by rememberSaveable { viewModel.error }

    val modifier = Modifier
        .fillMaxSize()
        .padding(
            top = 16.dp,
            start = 8.dp,
            bottom = 8.dp,
            end = 8.dp
        )

    Box(modifier = modifier) {
        if (transactionData?.isSuccessful == true) {
            PaymentSuccessfulView(
                modifier = modifier
            ) {
                viewModel.restartPaymentProcess()
            }
        } else {
            transactionData?.let {
                TransactionOverview(
                    transactionData = it,
                    modifier = modifier
                )
                CardDetails(
                    viewModel = viewModel,
                    modifier = modifier
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primaryVariant
                )
            }
        }
        if (error.isNotBlank()) {
            Text(
                error,
                color = MaterialTheme.colors.error,
                modifier = modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun TransactionOverview(
    transactionData: TransactionData,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(bottom = 32.dp)) {
        Column(modifier = modifier.align(Alignment.TopCenter)) {
            Text(
                text = "You are paying for: ${transactionData.transactionTitle}"
            )
            Text(
                text = "Transaction amount: $${transactionData.moneyAmount}"
            )
        }
    }
}

@Composable
fun CardDetails(
    viewModel: CardPaymentViewModel,
    modifier: Modifier = Modifier
) {
    val isCardDataValid = remember { mutableStateOf(false) }
    Box(modifier = modifier.padding(top = 16.dp)) {
        CardInfoView(
            isCardDataValid,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(top = 32.dp)
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = {
                    viewModel.makePayment()
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                enabled = isCardDataValid.value
            ) {
                Text(text = stringResource(R.string.pay))
            }
            Row {
                val isChecked = remember { mutableStateOf(false) }
                Checkbox(
                    checked = isChecked.value, onCheckedChange = {
                        isChecked.value = it
                        viewModel.makePaymentFailOrNot(it)
                    },
                    colors = CheckboxDefaults.colors(MaterialTheme.colors.error)
                )
                Text(
                    stringResource(R.string.make_payment_fail),
                    modifier = Modifier.align(CenterVertically),
                    color = Color.Gray
                )
            }
        }
    }
}
