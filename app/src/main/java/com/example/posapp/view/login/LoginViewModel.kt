package com.example.posapp.view.login

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posapp.data.LoginModel
import com.example.posapp.utils.FirebaseSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val client: FirebaseSignIn
) : ViewModel() {
    var loginState by mutableStateOf<LoginModel?>(null)
        private set

    init {
        loginState = client.getSignInUser()
    }

    fun signInWithGoogle(onDataResult:(Intent)->Unit): PendingIntent {
        return client.signIn(onStartActivityResult = onDataResult::invoke)
    }

    fun signInResult(intent: Intent) = viewModelScope.launch(Dispatchers.IO) {
        val res = client.getSignInResult(intent)

        res?.let {
            withContext(Dispatchers.Main){
                loginState = LoginModel(it.userId, it.username, it.email)
            }
        }
    }
}