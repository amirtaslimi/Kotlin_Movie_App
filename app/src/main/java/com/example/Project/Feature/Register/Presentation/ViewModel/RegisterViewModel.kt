package com.example.Project.Feature.Register.Presentation.ViewModel


import android.content.SharedPreferences
import android.util.Patterns
import androidx.lifecycle.*
import com.example.Project.ApiResponse
import com.example.Project.Feature.Register.Domain.Repository.RegisterRepository
import com.example.Project.RegisterRequest
import com.example.Project.hilt.RegisterPrefs
import com.example.Project.shared_component.data.Constants
import com.example.Project.utils.extensions.putBoolean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: RegisterRepository,
    @RegisterPrefs private val registerSharedPreferences: SharedPreferences
) : ViewModel() {

    private val _registerResult = MutableLiveData<Result<ApiResponse>>()
    val registerResult: LiveData<Result<ApiResponse>> get() = _registerResult

    fun registerUser(name: String, studentNumber: String, email: String, password: String) {

        if (name.isBlank()) {
            _registerResult.postValue(Result.failure(Throwable("Name cannot be empty")))
            return
        }

        if (studentNumber.isBlank()) {
            _registerResult.postValue(Result.failure(Throwable("Student number cannot be empty")))
            return
        }

        if (password.isBlank() || password.length < 6) {
            _registerResult.postValue(Result.failure(Throwable("Password must be at least 6 characters long")))
            return
        }

        if (!isValidEmail(email)) {
            _registerResult.postValue(Result.failure(Throwable("Invalid email format")))
            return
        }

        val md5Password = md5(password)

        viewModelScope.launch {
            val request = RegisterRequest(name, studentNumber, email, md5Password)
            val result = userRepository.registerUser(request)
            if (result.isSuccess) {
                saveRegisterToken()
            }
            _registerResult.postValue(result)
        }
    }

    private fun saveRegisterToken() {
        registerSharedPreferences.putBoolean(Constants.SHARED_PREFERENCE_KEY_REGISTER_TOKEN, true)
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}

