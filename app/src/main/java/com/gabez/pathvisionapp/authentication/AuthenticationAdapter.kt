package com.gabez.pathvisionapp.authentication

import com.gabez.pathvisionapp.authentication.entities.UserObj
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AuthenticationAdapter(
    private val userHolder: CurrentUserHolder,
    private val fDatabase: FirebaseDatabase,
    private val authErrorHolder: AuthErrorHolder
) {

    //TODO: Add authentication error an enum class

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var isInDb: Boolean = false

    init {
        if (auth.currentUser != null) {
            getUserFromDb(auth.currentUser!!.uid)
        } else userHolder.setCurrentUser(null)
    }

    suspend fun registerUser(email: String, login: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                addUserToDb(createUser(auth.currentUser!!.uid, email, login))
                authErrorHolder.setAuthError(null)
            }
            .addOnCanceledListener {
                GlobalScope.launch(Dispatchers.IO) { isUserInDb(email) }.invokeOnCompletion {
                    if(isInDb) authErrorHolder.setAuthError("User with this email already exists!")
                    else authErrorHolder.setAuthError("Unknown error. Try again later...")
                }
            }
    }

    suspend fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                getUserFromDb(auth.currentUser!!.uid)
                authErrorHolder.setAuthError(null)
            }
            .addOnCanceledListener {
                GlobalScope.launch(Dispatchers.IO) { isUserInDb(email) }.invokeOnCompletion {
                    if(isInDb) authErrorHolder.setAuthError("Wrong password!")
                    else authErrorHolder.setAuthError("Unknown error. Try again later...")
                }
            }
    }

    suspend fun logoutUser(){
        auth.signOut()
        userHolder.setCurrentUser(null)
    }

    suspend fun deleteUser(){
        val uid = auth.currentUser!!.uid

        auth.currentUser!!.delete()
            .addOnCompleteListener {
            fDatabase.reference.child("users").child(uid).removeValue().addOnCompleteListener { userHolder.setCurrentUser(null) } }
            .addOnCanceledListener { authErrorHolder.setAuthError("Unknown error. Try again later...") }
    }

    private fun addUserToDb(user: UserObj) {
        fDatabase.reference.child(user.uid).setValue(user).addOnCompleteListener {
            userHolder.setCurrentUser(user)
        }
    }

    private fun getUserFromDb(uid: String) {
        fDatabase.reference.child("users").child(uid).get().addOnCompleteListener { task ->
            userHolder.setCurrentUser(task.result!!.getValue(UserObj::class.java))
        }
    }

    private fun createUser(uid: String, email: String, login: String): UserObj =
        UserObj(uid, email, login, ArrayList())

    private suspend fun isUserInDb(email: String){
        fDatabase.reference.child("users").get().addOnCompleteListener { task ->
            for(user in task.result!!.children){
                val userObj = user.getValue(UserObj::class.java)
                if(userObj!!.email == email) isInDb = true
            }
        }
    }
}