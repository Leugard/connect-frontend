package com.luna.connect.ui.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import com.luna.connect.databinding.ActivitySplashBinding
import kotlin.random.Random
import com.luna.connect.R
import com.luna.connect.ui.auth.OnboardingActivity
import com.luna.connect.ui.auth.WelcomeActivity
import com.luna.connect.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val userViewModel: UserViewModel by viewModels()

    private val animationDuration = 800L
    private val splashDisplayTime = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        startAnimations()
        navigateToNextScreen()

        userViewModel.fetchUser()
    }

    private fun startAnimations() {
        animateFloatingElements()

        Handler(Looper.getMainLooper()).postDelayed({
            animateLogo()
        }, 300)

        Handler(Looper.getMainLooper()).postDelayed({
            animateAppName()
        }, 700)

        Handler(Looper.getMainLooper()).postDelayed({
            animateTagline()
        }, 1100)

        Handler(Looper.getMainLooper()).postDelayed({
            animateLoadingIndicator()
        }, 1500)

        Handler(Looper.getMainLooper()).postDelayed({
            animateVersionText()
        }, 1800)
    }

    private fun animateLogo() {
        val scaleX = ObjectAnimator.ofFloat(binding.logoContainer, "scaleX", 0.5f, 1.1f, 1.0f)
        val scaleY = ObjectAnimator.ofFloat(binding.logoContainer, "scaleY", 0.5f, 1.1f, 1.0f)
        val alpha = ObjectAnimator.ofFloat(binding.logoContainer, "alpha", 0f, 1f)
        val translationY =
            ObjectAnimator.ofFloat(binding.logoContainer, "translationY", 50f, -10f, 0f)
        val rotation = ObjectAnimator.ofFloat(binding.logoContainer, "rotation", -5f, 5f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY, alpha, translationY, rotation)
        animatorSet.duration = animationDuration
        animatorSet.interpolator = OvershootInterpolator(1.2f)
        animatorSet.start()

        Handler(Looper.getMainLooper()).postDelayed({
            addPulseEffect(binding.logoContainer)
        }, animationDuration)
    }

    private fun animateAppName() {
        val alpha = ObjectAnimator.ofFloat(binding.title, "alpha", 0f, 1f)
        val translationY = ObjectAnimator.ofFloat(binding.title, "translationY", 30f, 0f)
        val scaleX = ObjectAnimator.ofFloat(binding.title, "scaleX", 0.8f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.title, "scaleY", 0.8f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(alpha, translationY, scaleX, scaleY)
        animatorSet.duration = animationDuration
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.start()

    }

    private fun animateTagline() {
        val alpha = ObjectAnimator.ofFloat(binding.subtitle, "alpha", 0f, 1f)
        val translationY = ObjectAnimator.ofFloat(binding.subtitle, "translationY", 20f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(alpha, translationY)
        animatorSet.duration = 600L
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.start()
    }

    private fun animateLoadingIndicator() {
        val alpha = ObjectAnimator.ofFloat(binding.loading, "alpha", 0f, 1f)
        val scaleX = ObjectAnimator.ofFloat(binding.loading, "scaleX", 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.loading, "scaleY", 0f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(alpha, scaleX, scaleY)
        animatorSet.duration = 500L
        animatorSet.interpolator = OvershootInterpolator()
        animatorSet.start()
    }

    private fun animateVersionText() {
        val alpha = ObjectAnimator.ofFloat(binding.version, "alpha", 0f, 0.7f)
        val translationY = ObjectAnimator.ofFloat(binding.version, "translationY", 20f, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(alpha, translationY)
        animatorSet.duration = 400L
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.start()
    }

    private fun animateFloatingElements() {
        animateFloatingCircle(binding.floatingCircle1, 2000L, 0.3f)
        animateFloatingCircle(binding.floatingCircle2, 2500L, 0.2f)
        animateFloatingCircle(binding.floatingCircle3, 1800L, 0.25f)
    }

    private fun animateFloatingCircle(circle: View, duration: Long, maxAlpha: Float) {
        val translateX = ObjectAnimator.ofFloat(
            circle, "translationX",
            -20f + Random.nextFloat() * 40f,
            -20f + Random.nextFloat() * 40f
        )
        val translateY = ObjectAnimator.ofFloat(
            circle, "translationY",
            -30f + Random.nextFloat() * 60f,
            -30f + Random.nextFloat() * 60f
        )
        val alpha = ObjectAnimator.ofFloat(circle, "alpha", 0f, maxAlpha, 0f)
        val scaleX = ObjectAnimator.ofFloat(circle, "scaleX", 0.5f, 1.2f, 0.8f)
        val scaleY = ObjectAnimator.ofFloat(circle, "scaleY", 0.5f, 1.2f, 0.8f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(translateX, translateY, alpha, scaleX, scaleY)
        animatorSet.duration = duration
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.addListener(onEnd = {
            // Restart animation for continuous floating effect
            Handler(Looper.getMainLooper()).postDelayed({
                if (!isFinishing) {
                    animateFloatingCircle(circle, duration, maxAlpha)
                }
            }, Random.nextLong(200, 800))
        })
        animatorSet.start()
    }

    private fun addPulseEffect(view: View) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.05f, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.05f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.duration = 1000L
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.start()

        // Repeat pulse effect
        Handler(Looper.getMainLooper()).postDelayed({
            if (!isFinishing) {
                addPulseEffect(view)
            }
        }, 2000)
    }

    private fun navigateToNextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            userViewModel.user.observe(this) { result ->
                result.onSuccess { user ->
                    if (user.is_onboarded) {
                        // TODO: navigate to home
                    } else {
                        startActivity(Intent(this, OnboardingActivity::class.java))
                    }
                }.onFailure {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                }
            }

            // Custom transition animation
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, splashDisplayTime)
    }
}