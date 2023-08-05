package com.example.posapp.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.IntentSenderRequest
import com.example.posapp.R
import com.example.posapp.data.LoginModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
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

    suspend fun signIn(): IntentSender? {
        val res = try {
            /*oneTapClient.beginSignIn(setupSignInRequest()).await()*/
            client
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }
        return PendingIntent.getActivities(
            context, ComponentActivity.RESULT_OK,
            arrayOf(res?.signInIntent),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        ).intentSender
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
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
        }
    }

    suspend fun getSignInResult(intent: Intent): LoginModel? {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleAccount = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleAccount).await().user
            user?.let {
                LoginModel(
                    it.uid, it.displayName ?: "", it.email ?: "",
                    it.photoUrl?.toString()
                )
            }
        } catch (e: java.lang.Exception) {
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