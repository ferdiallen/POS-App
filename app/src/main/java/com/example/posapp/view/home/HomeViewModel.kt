package com.example.posapp.view.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.posapp.data.LoginModel
import com.example.posapp.utils.FirebaseSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseSignIn: FirebaseSignIn
) : ViewModel() {
    var currentUserData by mutableStateOf<LoginModel?>(null)
        private set

    init {
        currentUserData = firebaseSignIn.getSignInUser()
    }

}