package com.example.posapp.di

import android.content.Context
import com.example.posapp.utils.FirebaseSignIn
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
class Module {


    @Provides
    @Singleton
    fun provideOneTapClient(@ApplicationContext context: Context) : SignInClient {
        return Identity.getSignInClient(context)
    }

    @Provides
    @Singleton
    fun providesFirebaseSignIn(@ApplicationContext context: Context, oneTap: SignInClient) : FirebaseSignIn {
        return FirebaseSignIn(context, oneTap)
    }
}