package com.damolks.ouxy3.ui.common

import android.view.InputDevice
import android.view.MotionEvent
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.MotionEvents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.damolks.ouxy3.ui.views.SignaturePad
import org.hamcrest.Matcher
import android.view.View

object CustomViewActions {

    fun simulateSignature(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isDisplayed()
            }

            override fun getDescription(): String {
                return "Simule une signature sur le SignaturePad"
            }

            override fun perform(uiController: UiController, view: View) {
                if (view !is SignaturePad) return

                // Points de la signature
                val points = listOf(
                    Pair(0.2f, 0.5f),
                    Pair(0.4f, 0.3f),
                    Pair(0.6f, 0.7f),
                    Pair(0.8f, 0.5f)
                )

                // Coordonnées absolues
                val coordinates = points.map { (x, y) ->
                    Pair(
                        view.width * x,
                        view.height * y
                    )
                }

                // Simulation du touch
                val downTime = System.currentTimeMillis()
                var eventTime = downTime
                val inputDevice = InputDevice.SOURCE_TOUCHSCREEN

                // ACTION_DOWN
                val down = MotionEvents.sendDown(
                    uiController,
                    coordinates[0],
                    inputDevice
                ).down

                // Points intermédiaires
                for (i in 1 until coordinates.size) {
                    eventTime += 100
                    MotionEvents.sendMovement(
                        uiController,
                        down,
                        coordinates[i]
                    )
                    uiController.loopMainThreadForAtLeast(16) // ~60fps
                }

                // ACTION_UP
                eventTime += 100
                MotionEvents.sendUp(
                    uiController,
                    down,
                    coordinates.last()
                )
            }
        }
    }
}