package com.damolks.ouxy3.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.damolks.ouxy3.R
import com.damolks.ouxy3.core.session.SessionManager
import com.damolks.ouxy3.databinding.ActivitySplashBinding
import com.damolks.ouxy3.ui.main.MainActivity
import com.damolks.ouxy3.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val sessionManager: SessionManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAnimation()
    }

    private fun setupAnimation() {
        binding.animationView.setAnimation(R.raw.splash_anim)
        binding.animationView.addAnimatorListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationStart(animation: android.animation.Animator) {}
            override fun onAnimationCancel(animation: android.animation.Animator) {}
            override fun onAnimationRepeat(animation: android.animation.Animator) {}
            override fun onAnimationEnd(animation: android.animation.Animator) {
                navigateToNextScreen()
            }
        })
    }

    private fun navigateToNextScreen() {
        lifecycleScope.launch {
            // Délai minimum pour l'animation
            delay(1500)

            val intent = if (sessionManager.isOnboardingCompleted()) {
                Intent(this@SplashActivity, MainActivity::class.java)
            } else {
                Intent(this@SplashActivity, OnboardingActivity::class.java)
            }

            startActivity(intent)
            finish()
            // Animation de transition
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}