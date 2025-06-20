package com.luna.connect.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luna.connect.R
import com.luna.connect.data.api.ApiClient
import com.luna.connect.data.api.ApiService
import com.luna.connect.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val api: ApiService by lazy { ApiClient.create(this).create(ApiService::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.forgotPassword.setOnClickListener {
            // TODO: Implement forgot password
        }

        binding.registerBtn.setOnClickListener {
            val username = binding.usernameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            register(username, email, password)
        }

        binding.google.setOnClickListener {
            // TODO: implement google login
        }

        binding.goToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun register(username:String, email:String, password:String){

    }
}