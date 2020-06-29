package com.example.kotlinstack.di

import com.example.kotlinstack.data.remote.ApiHelper
import com.example.kotlinstack.data.remote.DefaultApiHelper
import com.example.kotlinstack.data.remote.WeatherApiService
import com.example.kotlinstack.data.repos.DefaultWeatherRepo
import com.example.kotlinstack.data.repos.WeatherRepo
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {

    @Provides
    fun provideMainRepo(apiHelper: ApiHelper) =
        DefaultWeatherRepo(apiHelper)

    @Provides
    fun providesMainApiService(moshi: Moshi, weatherApiService: WeatherApiService): ApiHelper =
        DefaultApiHelper(moshi, weatherApiService)

    @Provides
    fun provideMainApiService(retrofit: Retrofit) = retrofit.create(WeatherApiService::class.java)

}

@Module
@InstallIn(ActivityComponent::class)
abstract class HomeBindings {
    @Binds
    abstract fun bindMainRepo(impl: DefaultWeatherRepo): WeatherRepo

}