package com.damolks.ouxy3.ui.navigation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.damolks.ouxy3.R
import com.damolks.ouxy3.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun testHomeToSettingsNavigation() {
        // Vérifier que l'écran d'accueil est affiché initialement
        onView(withId(R.id.welcomeCard))
            .check(matches(isDisplayed()))

        // Naviguer vers les paramètres
        onView(withId(R.id.settings))
            .perform(click())

        // Vérifier que l'écran des paramètres est affiché
        onView(withId(R.id.settingsScreen))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationBackStack() {
        // Aller aux sites puis au marketplace
        onView(withId(R.id.sites))
            .perform(click())
        onView(withId(R.id.marketplace))
            .perform(click())

        // Appuyer sur retour
        pressBack()

        // Vérifier qu'on revient aux sites
        onView(withId(R.id.sitesList))
            .check(matches(isDisplayed()))

        // Appuyer sur retour encore
        pressBack()

        // Vérifier qu'on revient à l'accueil
        onView(withId(R.id.welcomeCard))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testDeepLinkNavigation() {
        // Aller directement aux sites
        onView(withId(R.id.sites))
            .perform(click())

        // Ouvrir le détail d'un site
        onView(withId(R.id.sitesList))
            .perform(click())

        // Vérifier qu'on est sur la page de détail
        onView(withId(R.id.siteDetail))
            .check(matches(isDisplayed()))

        // Vérifier que le retour fonctionne
        pressBack()
        onView(withId(R.id.sitesList))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testBottomNavigationStatePersistence() {
        // Aller aux sites
        onView(withId(R.id.sites))
            .perform(click())

        // Aller aux paramètres
        onView(withId(R.id.settings))
            .perform(click())

        // Revenir aux sites
        onView(withId(R.id.sites))
            .perform(click())

        // Vérifier que l'état des sites est préservé
        onView(withId(R.id.sitesList))
            .check(matches(isDisplayed()))
    }
}