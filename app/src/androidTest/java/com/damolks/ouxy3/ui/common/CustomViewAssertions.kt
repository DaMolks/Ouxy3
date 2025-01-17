package com.damolks.ouxy3.ui.common

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import com.damolks.ouxy3.ui.views.SignaturePad

object CustomViewAssertions {

    fun hasSignature(): ViewAssertion {
        return ViewAssertion { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            if (view !is SignaturePad) {
                throw NoMatchingViewException.Builder()
                    .withViewDescription("avec SignaturePad")
                    .withRootView(view)
                    .build()
            }

            if (view.isEmpty()) {
                throw AssertionError("Le SignaturePad ne contient pas de signature")
            }
        }
    }
}