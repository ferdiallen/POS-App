package com.example.posapp.view.profile

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
class ProfileViewModel @Inject constructor(
    private val firebaseSignIn: FirebaseSignIn
) : ViewModel() {

    var currentUserData by mutableStateOf<LoginModel?>(null)
        private set

    init {
        currentUserData = firebaseSignIn.getSignInUser()
    }

    fun signOutUser(popBack: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        firebaseSignIn.signOut()
        withContext(Dispatchers.Main) {
            popBack.invoke()
        }
    }

}