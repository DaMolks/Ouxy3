package com.damolks.ouxy3.ui.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.damolks.ouxy3.R
import com.damolks.ouxy3.core.session.SessionManager
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.inject
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class SplashActivityTest : KoinTest {

    private val sessionManager: SessionManager by inject()
    private lateinit var scenario: ActivityScenario<SplashActivity>

    @Before
    fun setup() {
        sessionManager.clearSession()
        scenario = ActivityScenario.launch(SplashActivity::class.java)
    }

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun splashScreenShowsAnimation() {
        // Vérifier que l'animation Lottie est visible
        onView(withId(R.id.animationView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun newUserNavigatesToOnboarding() {
        // Par défaut, l'onboarding n'est pas complété
        Thread.sleep(2000) // Attendre la fin de l'animation
        
        // Vérifier qu'on arrive sur l'onboarding
        onView(withId(R.id.onboardingContainer))
            .check(matches(isDisplayed()))
    }

    @Test
    fun existingUserNavigatesToMain() {
        // Simuler un utilisateur existant
        sessionManager.setOnboardingCompleted()
        sessionManager.setTechnicianId(1L)
        
        scenario = ActivityScenario.launch(SplashActivity::class.java)
        Thread.sleep(2000) // Attendre la fin de l'animation

        // Vérifier qu'on arrive sur le main
        onView(withId(R.id.mainContainer))
            .check(matches(isDisplayed()))
    }
}