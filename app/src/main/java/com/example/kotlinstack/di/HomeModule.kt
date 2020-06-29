package com.example.kotlinstack.di

import com.example.kotlinstack.data.remote.WeatherApiService
import com.example.kotlinstack.data.repos.DefaultWeatherRepo
import com.example.kotlinstack.data.repos.WeatherRepo
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
    fun provideMainApiService(retrofit: Retrofit): WeatherApiService = retrofit.create(WeatherApiService::class.java)


}

@Module
@InstallIn(ActivityComponent::class)
abstract class HomeBindings {
    @Binds
    abstract fun bindMainRepo(impl: DefaultWeatherRepo): WeatherRepo

}