package com.damolks.ouxy3.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.damolks.ouxy3.R
import com.damolks.ouxy3.databinding.ActivitySplashBinding
import com.damolks.ouxy3.ui.main.MainActivity
import com.damolks.ouxy3.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
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
        binding.logoAnimation.setAnimation(R.raw.splash_animation)
        binding.logoAnimation.playAnimation()
        
        // Lancer la vérification après l'animation
        binding.logoAnimation.addAnimatorUpdateListener { valueAnimator ->
            if (valueAnimator.animatedFraction >= 1.0f) {
                viewModel.checkFirstLaunch()
            }
        }
    }

    private fun observeNavigation() {
        viewModel.navigationEvent.observe(this) { destination ->
            when (destination) {
                SplashViewModel.NavigationDestination.ONBOARDING -> startOnboarding()
                SplashViewModel.NavigationDestination.MAIN -> startMain()
            }
        }
    }

    private fun startOnboarding() {
        lifecycleScope.launch {
            delay(500) // Court délai pour s'assurer que l'animation est terminée
            startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
            finish()
        }
    }

    private fun startMain() {
        lifecycleScope.launch {
            delay(500) // Court délai pour s'assurer que l'animation est terminée
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}