package com.luna.connect.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luna.connect.data.api.ApiService
import com.luna.connect.data.local.TokenManager
import com.luna.connect.data.model.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val api: ApiService,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _user = MutableLiveData<Result<UserResponse>>()
    val user: LiveData<Result<UserResponse>> = _user

    fun fetchUser() {
        val token = tokenManager.getAccessToken()
        Log.d("UserViewModel", "Token: $token")
        Log.d("UserViewModel", "Ga ada token")

        if (token.isNullOrEmpty()) {
            _user.value = Result.failure(Exception("Access token missing"))
            return
        }

        viewModelScope.launch {
            try {
                val response = api.getMe()
                if (response.isSuccessful && response.body()?.status == "success") {
                    _user.value = Result.success(response.body()!!.data!!)
                } else {
                    val message = response.body()?.message ?: "Unable to fetch user"
                    _user.value = Result.failure(Exception(message))
                }
            } catch (e: Exception) {
                _user.value = Result.failure(e)
            }
        }
    }
}