package com.soten.sotenshopclient.di

import com.google.firebase.storage.FirebaseStorage
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.db.dao.BasketDao
import com.soten.sotenshopclient.data.db.dao.LikedDao
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.firebase.FirebaseRepository
import com.soten.sotenshopclient.data.repository.firebase.FirebaseRepositoryImpl
import com.soten.sotenshopclient.data.repository.paging.PagingRepository
import com.soten.sotenshopclient.data.repository.paging.PagingRepositoryImpl
import com.soten.sotenshopclient.data.repository.product.basket.ProductBasketRepository
import com.soten.sotenshopclient.data.repository.product.basket.ProductBasketRepositoryImpl
import com.soten.sotenshopclient.data.repository.product.liked.ProductLikedRepository
import com.soten.sotenshopclient.data.repository.product.liked.ProductLikedRepositoryImpl
import com.soten.sotenshopclient.data.repository.shopping.ShoppingRepository
import com.soten.sotenshopclient.data.repository.shopping.ShoppingRepositoryImpl
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

    @Provides
    @Singleton
    fun provideProductLikedRepository(likedDao: LikedDao): ProductLikedRepository {
        return ProductLikedRepositoryImpl(likedDao)
    }

    @Provides
    @Singleton
    fun provideProductBasketRepository(basketDao: BasketDao): ProductBasketRepository {
        return ProductBasketRepositoryImpl(basketDao)
    }

}