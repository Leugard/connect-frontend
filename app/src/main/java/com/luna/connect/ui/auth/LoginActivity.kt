package com.luna.connect.ui.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.textfield.TextInputLayout
import com.luna.connect.R
import com.luna.connect.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.forgotPassword.setOnClickListener {
            // TODO: Implement forgot password
        }

        binding.loginBtn.setOnClickListener {
            // TODO: Implement login
        }

        binding.google.setOnClickListener {
            // TODO: implement google login
        }

        binding.goToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}