package com.example.baitaptuan4_otpemail1.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.baitaptuan4_otpemail1.ui.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel : ViewModel() {

    private val _user = MutableStateFlow(User())

    // Public chỉ để đọc toàn bộ user
    val user: StateFlow<User> get() = _user

    //Getter
    fun getEmail(): String = _user.value.email
    fun getOTP(): String = _user.value.otp
    fun getPassword(): String = _user.value.password

    //Setter
    fun setEmail(email: String) {
        _user.value = _user.value.copy(email = email)
    }

    fun setOTP(otp: String) {
        _user.value = _user.value.copy(otp = otp)
    }

    fun setPassword(password: String) {
        _user.value = _user.value.copy(password = password)
    }

    fun resetUser() {
        _user.value = User()
    }
}