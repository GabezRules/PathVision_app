package com.gabez.pathvisionapp.data.gateways

interface AuthenticationAdapter {
    fun deleteUser()
    fun loginUser(email: String, password: String)
    fun logoutUser()
    fun registerUser(email: String, login: String, password: String)
}