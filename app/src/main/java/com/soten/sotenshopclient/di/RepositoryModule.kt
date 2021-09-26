package com.soten.sotenshopclient.di

import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.repository.ShoppingRepository
import com.soten.sotenshopclient.data.repository.ShoppingRepositoryImpl
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

}