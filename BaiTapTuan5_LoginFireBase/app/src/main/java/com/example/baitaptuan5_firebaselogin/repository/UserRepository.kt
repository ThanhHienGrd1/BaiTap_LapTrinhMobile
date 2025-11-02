package com.example.baitaptuan5_firebaselogin.repository

import com.example.baitaptuan5_firebaselogin.model.User

class UserRepository {
    private var currentUser: User? = null
    private var userList = mutableListOf<User>(
        User (email = "12345@", password = "12345", name = "Tran Thanh Hien")
    )



    fun register(email: String, password: String, name:String): Boolean{
        if(userList.any{it.email==email}) return false
        val user = User(email,password,name)
        userList.add(user)
        return true
    }

    fun login(email: String, password: String): User? {
        val user = userList.find{it.email==email && it.password==password}
        currentUser = user
        return user
    }

    fun logout() {
        currentUser = null
    }

    fun getCurrentUser():User? {
        return currentUser
    }

}