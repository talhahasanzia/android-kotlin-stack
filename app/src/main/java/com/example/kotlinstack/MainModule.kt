package com.example.kotlinstack

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideMainRepo(apiHelper: ApiHelper) = DefaultMainRepo(apiHelper)

    @Provides
    fun providesMainApiService(mainApiService: MainApiService) = ApiHelper(mainApiService)

    @Provides
    fun provideMainApiService(retrofit: Retrofit) = retrofit.create(MainApiService::class.java)

}

@Module
@InstallIn(ActivityComponent::class)
abstract class MainBindings {
    @Binds
    abstract fun bindMainRepo(impl: DefaultMainRepo): MainRepo

}