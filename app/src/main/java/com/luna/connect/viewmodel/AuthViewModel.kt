package com.luna.connect.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luna.connect.data.api.ApiService
import com.luna.connect.data.model.ApiResponse
import com.luna.connect.data.model.LoginRequest
import com.luna.connect.data.model.MessageResponse
import com.luna.connect.data.model.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val api: ApiService) : ViewModel() {
    private val _registerResult = MutableLiveData<Result<MessageResponse>>()
    val registerResult: LiveData<Result<MessageResponse>> = _registerResult

    private val _loginResult = MutableLiveData<Result<MessageResponse>>()
    val loginResult: LiveData<Result<MessageResponse>> = _loginResult

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            try {
                val response = api.register(request)
                handleRegisterResponse(response)
            } catch (e: Exception) {
                _registerResult.value = Result.failure(e)
            }
        }
    }

    fun register(request: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = api.login(request)
                handleLoginResponse(response)
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }

    private fun handleRegisterResponse(response: Response<ApiResponse<MessageResponse>>) {
        if(response.isSuccessful && response.body()?.status == "success") {
            _registerResult.value = Result.success(response.body()!!.data!!)
        }else{
            val error = Exception(response.body()?.message ?: "Registration failed")
            _registerResult.value = Result.failure(error)
        }
    }

    private fun handleLoginResponse(response: Response<ApiResponse<MessageResponse>>) {
        if(response.isSuccessful && response.body()?.status == "success") {
            _loginResult.value = Result.success(response.body()!!.data!!)
        }else{
            val error = Exception(response.body()?.message ?: "Registration failed")
            _loginResult.value = Result.failure(error)
        }
    }
}