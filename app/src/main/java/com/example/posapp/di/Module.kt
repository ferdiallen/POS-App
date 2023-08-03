package com.example.posapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.posapp.data.database.ProductDatabase
import com.example.posapp.repository.Repository
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
    fun provideOneTapClient(@ApplicationContext context: Context): SignInClient {
        return Identity.getSignInClient(context)
    }

    @Provides
    @Singleton
    fun providesFirebaseSignIn(
        @ApplicationContext context: Context,
        oneTap: SignInClient
    ): FirebaseSignIn {
        return FirebaseSignIn(context, oneTap)
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