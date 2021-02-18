package com.gabez.pathvisionapp.authentication.authentication.authLogic

interface AuthenticationAdapter {
    fun deleteUser()
    fun loginUser(email: String, password: String)
    fun logoutUser()
    fun registerUser(email: String, login: String, password: String)
}