package com.luna.connect.ui.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luna.connect.R
import com.luna.connect.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.edit.setOnClickListener {
            // TODO: Implement crop image
        }

        binding.continueBtn.setOnClickListener {
            // TODO: Implement onboarding
        }
    }
}