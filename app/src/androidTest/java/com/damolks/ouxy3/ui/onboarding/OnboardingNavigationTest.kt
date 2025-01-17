package com.damolks.ouxy3.ui.onboarding

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.damolks.ouxy3.R
import com.damolks.ouxy3.ui.common.CustomViewActions.simulateSignature
import com.damolks.ouxy3.ui.common.CustomViewAssertions.hasSignature
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnboardingNavigationTest {
    private lateinit var scenario: ActivityScenario<OnboardingActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(OnboardingActivity::class.java)
    }

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun testCompleteOnboardingFlow() {
        // Vérification de l'écran initial
        onView(withId(R.id.technicianProfileTitle))
            .check(matches(isDisplayed()))

        // Remplissage du formulaire technicien
        fillTechnicianForm()

        // Navigation vers la signature
        onView(withId(R.id.nextButton))
            .perform(click())

        // Vérification de l'écran signature
        onView(withId(R.id.signatureTitle))
            .check(matches(isDisplayed()))

        // Création de la signature
        createSignature()

        // Navigation vers la configuration des sites
        onView(withId(R.id.nextButton))
            .check(matches(isEnabled()))
            .perform(click())

        // Configuration des sites
        configureSites()

        // Finalisation de l'onboarding
        onView(withId(R.id.finishButton))
            .perform(click())

        // Vérification de la redirection vers MainActivity
        onView(withId(R.id.mainContainer))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testFormValidation() {
        // Le bouton suivant devrait être désactivé initialement
        onView(withId(R.id.nextButton))
            .check(matches(not(isEnabled())))

        // Remplissage partiel du formulaire
        onView(withId(R.id.firstNameEdit))
            .perform(typeText("John"), closeSoftKeyboard())

        // Le bouton devrait toujours être désactivé
        onView(withId(R.id.nextButton))
            .check(matches(not(isEnabled())))

        // Compléter le formulaire
        fillTechnicianForm()

        // Le bouton devrait être activé
        onView(withId(R.id.nextButton))
            .check(matches(isEnabled()))
    }

    @Test
    fun testSignatureValidation() {
        // Aller à l'écran signature
        fillTechnicianForm()
        onView(withId(R.id.nextButton)).perform(click())

        // Le bouton suivant devrait être désactivé sans signature
        onView(withId(R.id.nextButton))
            .check(matches(not(isEnabled())))

        // Créer une signature
        createSignature()

        // Le bouton devrait être activé
        onView(withId(R.id.nextButton))
            .check(matches(isEnabled()))

        // Effacer la signature
        onView(withId(R.id.clearButton)).perform(click())

        // Le bouton devrait être désactivé à nouveau
        onView(withId(R.id.nextButton))
            .check(matches(not(isEnabled())))
    }

    private fun fillTechnicianForm() {
        onView(withId(R.id.firstNameEdit))
            .perform(typeText("John"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEdit))
            .perform(typeText("Doe"), closeSoftKeyboard())
        onView(withId(R.id.matriculeEdit))
            .perform(typeText("T123"), closeSoftKeyboard())
        onView(withId(R.id.sectorEdit))
            .perform(typeText("Nord"), closeSoftKeyboard())
        onView(withId(R.id.teamLeaderEdit))
            .perform(typeText("Jane Smith"), closeSoftKeyboard())
    }

    private fun createSignature() {
        onView(withId(R.id.signaturePad))
            .perform(simulateSignature())
            .check(matches(hasSignature()))
    }

    private fun configureSites() {
        onView(withId(R.id.addSiteButton))
            .perform(click())

        onView(withId(R.id.siteNameEdit))
            .perform(typeText("Site Test"), closeSoftKeyboard())
        onView(withId(R.id.siteAddressEdit))
            .perform(typeText("123 Test St"), closeSoftKeyboard())

        onView(withId(R.id.saveButton))
            .perform(click())

        // Vérifier que le site a été ajouté
        onView(withId(R.id.sitesList))
            .check(matches(hasMinimumChildCount(1)))
    }
}