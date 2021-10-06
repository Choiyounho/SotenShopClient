package com.soten.sotenshopclient.di

import com.google.firebase.storage.FirebaseStorage
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideShoppingRepository(shoppingApi: ShoppingApi): ShoppingRepository {
        return ShoppingRepositoryImpl(shoppingApi)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        storage: FirebaseStorage,
        sharedPreferenceManager: SharedPreferenceManager,
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(storage, sharedPreferenceManager)
    }

    @Provides
    @Singleton
    fun providePagingRepository(shoppingApi: ShoppingApi): PagingRepository {
        return PagingRepositoryImpl(shoppingApi)
    }

}