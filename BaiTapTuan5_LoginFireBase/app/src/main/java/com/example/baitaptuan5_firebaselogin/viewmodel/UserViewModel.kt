package com.example.baitaptuan5_firebaselogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baitaptuan5_firebaselogin.model.User
import com.example.baitaptuan5_firebaselogin.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repo = UserRepository()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _isLogined = MutableStateFlow(false)
    val isLogined = _isLogined.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            when {
                email.isBlank() -> {
                    _message.value = "Vui lòng nhập Email!"
                    return@launch
                }

                password.isBlank() -> {
                    _message.value = "Vui lòng nhập mật khẩu!"
                    return@launch
                }

                !email.contains("@") -> {
                    _message.value = "Email không hợp lệ!"
                    return@launch
                }
            }
            val user = repo.login(email, password)
            if (user != null) {
                _currentUser.value = user
                _isLogined.value = true
                _message.value = "Đăng nhập thành công!"
            }
        }
    }

    fun clearmessage() {
        _message.value = null
    }

    // ✅ THÊM: cho MainActivity / Login_Screen gọi để set message
    fun setMessage(msg: String) {
        _message.value = msg
    }
    fun loginWithGoogle(idToken: String) {
        // Không dùng viewModelScope.launch vì Firebase API callback-based;
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = FirebaseAuth.getInstance().currentUser
                    _currentUser.value = User(
                        email = firebaseUser?.email ?: "",
                        name = firebaseUser?.displayName ?: "",
                        avatarUrl = firebaseUser?.photoUrl?.toString()
                    )
                    _isLogined.value = true
                    _message.value = "Đăng nhập Google thành công!"
                } else {
                    _message.value = "Đăng nhập Google thất bại!"
                }
            }
    }

    fun register(
        email: String,
        password: String,
        name: String,
        onSuccess: () -> Unit,
        onFail: () -> Unit
    ) {
        viewModelScope.launch {
            val success = repo.register(email, password, name)
            if (success) onSuccess() else onFail()
        }
    }

    fun logout() {
        repo.logout()
        _isLogined.value = false
        _currentUser.value = null
    }

    fun checkLoginStatus() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            _currentUser.value = User(
                email = firebaseUser.email ?: "",
                name = firebaseUser.displayName ?: "",
                avatarUrl = firebaseUser.photoUrl?.toString()
            )
            _isLogined.value = true
        } else {
            _isLogined.value = false
            _currentUser.value = null
        }
    }

}
