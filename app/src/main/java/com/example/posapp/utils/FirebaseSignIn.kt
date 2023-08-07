package com.example.posapp.utils

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.IntentSenderRequest
import com.example.posapp.MainActivity
import com.example.posapp.R
import com.example.posapp.data.LoginModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class FirebaseSignIn(
    private val context: Context, private val oneTapClient: SignInClient,
    private val client: GoogleSignInClient
) {
    private val auth = Firebase.auth

    fun signIn(onStartActivityResult: (Intent) -> Unit): PendingIntent {
        val res = try {
            client
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }
        val data = PendingIntent.getActivities(
            context, 100,
            arrayOf(res?.signInIntent),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        onStartActivityResult.invoke(client.signInIntent)
        return data
    }

    fun getSignInUser(): LoginModel? {
        return auth.currentUser?.let {
            LoginModel(
                it.uid, it.displayName ?: "", it.email ?: "",
                it.photoUrl?.toString()
            )
        }
    }

    suspend fun signOut() {
        try {
            /*oneTapClient.signOut().await()*/
            client.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
        }
    }

    suspend fun getSignInResult(intent: Intent): LoginModel? {
        val credential = GoogleSignIn.getSignedInAccountFromIntent(intent).result.idToken
        val googleAccount = GoogleAuthProvider.getCredential(credential, null)
        return try {
            val user = auth.signInWithCredential(googleAccount).await().user
            user?.let {
                LoginModel(
                    it.uid, it.displayName ?: "", it.email ?: "",
                    it.photoUrl?.toString()
                )
            }
        } catch (e: java.lang.Exception) {
            println(e.message)
            if (e is CancellationException) throw e
            null
        }
    }

    private fun setupSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            GoogleIdTokenRequestOptions.builder().setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.webclient_id)).build()
        ).setAutoSelectEnabled(true).build()
    }
}