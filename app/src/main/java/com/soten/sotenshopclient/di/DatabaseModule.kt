package com.soten.sotenshopclient.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.soten.sotenshopclient.data.db.ShoppingDataBase
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val SHARED_PREFERENCE_NAME = "SHARED PREFERENCE"
    private const val DB_NAME = "product.db"

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun providePreferenceManager(sharedPreferences: SharedPreferences) =
        SharedPreferenceManager(sharedPreferences)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShoppingDataBase::class.java, DB_NAME).build()

    @Provides
    fun provideLikedDao(shoppingDataBase: ShoppingDataBase) = shoppingDataBase.LikedDao()

    @Provides
    fun provideBasketDao(shoppingDataBase: ShoppingDataBase) = shoppingDataBase.basketDao()

}