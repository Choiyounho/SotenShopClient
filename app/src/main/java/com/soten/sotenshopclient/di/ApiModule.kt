package com.soten.sotenshopclient.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.soten.sotenshopclient.BuildConfig
import com.soten.sotenshopclient.data.api.IamPortApi
import com.soten.sotenshopclient.data.api.ShoppingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = BuildConfig.MY_IP_ADDRESS

    @Provides
    @Singleton
    fun provideShoppingApi(): ShoppingApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShoppingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideIamPortApi(): IamPortApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IamPortApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage() = Firebase.storage

}
