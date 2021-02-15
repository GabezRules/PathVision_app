package com.gabez.authentication.authentication

import com.gabez.authentication.authentication.entities.UserObj
import com.gabez.authentication.authentication.statusHolders.AuthErrorHolder
import com.gabez.authentication.authentication.statusHolders.AuthLoadingHolder
import com.gabez.authentication.authentication.statusHolders.CurrentUserHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthenticationAdapter(
    private val userHolder: CurrentUserHolder,
    private val fDatabase: FirebaseDatabase,
    private val authErrorHolder: AuthErrorHolder,
    private val authLoadingHolder: AuthLoadingHolder,
    private val compare: PostLoginCompare
) {

    //TODO: Add authentication error an enum class

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        fDatabase.setPersistenceEnabled(true)

        if (auth.currentUser != null) {
            getUserFromDb(auth.currentUser!!.uid)
        } else userHolder.setCurrentUser(null)
    }

    fun registerUser(email: String, login: String, password: String) {
        authLoadingHolder.setAuthenticationState(true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                GlobalScope.launch(Dispatchers.IO) {
                    addUserToDb(createUser(auth.currentUser!!.uid, email, login))
                    authErrorHolder.setAuthError(null)
                    authLoadingHolder.setAuthenticationState(false)
                }.invokeOnCompletion {

                }
            }
            .addOnCanceledListener {
                GlobalScope.launch(Dispatchers.IO) { setupAuthErrorFromDb(email) }.invokeOnCompletion { authLoadingHolder.setAuthenticationState(false) }
            }
    }

    fun loginUser(email: String, password: String) {
        authLoadingHolder.setAuthenticationState(true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                val user = auth.currentUser
                if(user!=null){
                    var dataMatch: Boolean = false

                    GlobalScope.launch (Dispatchers.IO) {
                        dataMatch = compare.areTheSame()
                    }.invokeOnCompletion {
                        getUserFromDb(auth.currentUser!!.uid)
                        authErrorHolder.setAuthError(null)
                        authLoadingHolder.setAuthenticationState(false)
                    }

                }else {
                    GlobalScope.launch(Dispatchers.IO) { setupAuthErrorFromDb(email) }
                        .invokeOnCompletion { authLoadingHolder.setAuthenticationState(false) }
                }

            }
            .addOnCanceledListener {
                GlobalScope.launch(Dispatchers.IO) { setupAuthErrorFromDb(email) }.invokeOnCompletion { authLoadingHolder.setAuthenticationState(false) }
            }
    }

    fun logoutUser(){
        auth.signOut()
        userHolder.setCurrentUser(null)
    }

    fun deleteUser(){
        val uid = auth.currentUser!!.uid

        auth.currentUser!!.delete()
            .addOnCompleteListener {
            fDatabase.reference.child("users").child(uid).removeValue().addOnCompleteListener { userHolder.setCurrentUser(null) } }
            .addOnCanceledListener { authErrorHolder.setAuthError("Unknown error. Try again later...") }
    }

    private fun addUserToDb(user: UserObj) {
        fDatabase.reference.child("users").child(user.uid).setValue(user).addOnCompleteListener {
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

    private fun setupAuthErrorFromDb(email: String){
        var isInDb: Boolean = false
        fDatabase.reference.child("users").get().addOnCompleteListener { task ->
            if(task.result != null){
                for(user in task.result!!.children){
                    val userObj = user.getValue(UserObj::class.java)
                    if(userObj!!.email == email) isInDb = true
                }
            }

            if(isInDb) authErrorHolder.setAuthError("Wrong password!")
            else authErrorHolder.setAuthError("Unknown error. Try again later...")
        }
    }
}