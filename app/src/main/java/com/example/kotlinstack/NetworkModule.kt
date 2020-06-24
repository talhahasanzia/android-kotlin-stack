package com.example.kotlinstack

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideClient(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory) =
        Retrofit
            .Builder()
            .baseUrl("https://www.google.com")
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    fun provideHttpInterceptor() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()


    @Provides
    fun providesMoshiFactory(moshi: Moshi) = MoshiConverterFactory.create(moshi)

    @Provides
    fun provideMoshi() = Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(Result::class.java, Result::class.java.simpleName)
                .withSubtype(Result.Success::class.java, Result.Success::class.java.simpleName)
                .withSubtype(Result.Failure::class.java, Result.Failure::class.java.simpleName)
        )
        .add(KotlinJsonAdapterFactory())
        .build()
}