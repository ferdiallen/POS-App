package com.example.posapp.di

import android.content.Context
import android.content.Intent
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.posapp.R
import com.example.posapp.data.database.ProductDatabase
import com.example.posapp.repository.Repository
import com.example.posapp.utils.FirebaseSignIn
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
    fun provideOneTapClient(@ApplicationContext context: Context): SignInClient {
        return Identity.getSignInClient(context)
    }

    @Provides
    @Singleton
    fun providesOldWaysFirebaseSignIn(@ApplicationContext context: Context): GoogleSignInClient {
        val options = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestEmail()
            .requestId()
            .requestProfile()
            .requestIdToken(context.getString(R.string.webclient_id))
            .build()
        return GoogleSignIn.getClient(context, options)
    }

    @Provides
    @Singleton
    fun providesFirebaseSignIn(
        @ApplicationContext context: Context,
        oneTap: SignInClient,
        signInClient: GoogleSignInClient
    ): FirebaseSignIn {
        return FirebaseSignIn(context, oneTap, signInClient)
    }

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context, ProductDatabase::class.java,
            "db_data"
        ).build()
    }

    @Provides
    @Singleton
    fun providesRepository(db: ProductDatabase): Repository {
        return Repository(db.dao())
    }
}