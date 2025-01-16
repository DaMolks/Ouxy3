package com.damolks.ouxy3.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.damolks.ouxy3.databinding.ActivitySplashBinding
import com.damolks.ouxy3.ui.main.MainActivity
import com.damolks.ouxy3.ui.onboarding.OnboardingActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAnimation()
        observeNavigation()
    }

    private fun setupAnimation() {
        // TODO: Implementer l'animation du logo
    }

    private fun observeNavigation() {
        viewModel.navigationEvent.observe(this) { destination ->
            when (destination) {
                NavigationDestination.ONBOARDING -> startOnboarding()
                NavigationDestination.MAIN -> startMain()
            }
        }
    }

    private fun startOnboarding() {
        startActivity(Intent(this, OnboardingActivity::class.java))
        finish()
    }

    private fun startMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}