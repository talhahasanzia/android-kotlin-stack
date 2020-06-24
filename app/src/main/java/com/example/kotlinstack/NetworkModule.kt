package com.example.kotlinstack

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    // retrofit stuff here
    @Provides
    fun provideClient(okHttpClient: OkHttpClient) = Retrofit
        .Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideHttpInterceptor() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

}