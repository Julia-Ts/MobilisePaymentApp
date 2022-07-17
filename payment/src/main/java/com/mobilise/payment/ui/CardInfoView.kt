package com.mobilise.payment.ui

import android.content.Context
import android.content.ContextWrapper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.braintreepayments.cardform.view.CardForm

@Composable
fun CardInfoView(
    isCardDataValid: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AndroidView(modifier = Modifier.fillMaxSize(),
            factory = { context ->
                CardForm(context).apply {
                    cardRequired(true)
                    expirationRequired(true)
                    cvvRequired(true)
                    cardholderName(CardForm.FIELD_REQUIRED)
                    maskCvv(true)
                    postalCodeRequired(true)
                    mobileNumberRequired(true)
                    actionLabel("Card information")
                    setup(context.findActivity())

                    // Warning: this is for development purposes only and should never be done outside of this app.
                    // Failure to set FLAG_SECURE exposes your app to screenshots allowing other apps to steal card information.
                    context.findActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                }
            },
            update = { view ->
                view.setOnCardFormValidListener { isFormValid ->
                    isCardDataValid.value = isFormValid
                }
                view.setOnCardFormSubmitListener {
                    view.closeSoftKeyboard()
                }
            })
    }
}

fun Context.findActivity(): AppCompatActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) return context
        context = context.baseContext
    }
    throw IllegalStateException("No AppCompatActivity has been found")
}