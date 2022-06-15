package com.indocyber.newsapplicationapi.hilt_module

import android.app.Application
import com.indocyber.api_service.RetrofitClient
import com.indocyber.api_service.service.EverythingService
import com.indocyber.api_service.service.SourceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiService {
    @Provides
    @Singleton
    fun provideRetrofit(application: Application) = RetrofitClient.getClient(application)

    @Provides
    @Singleton
    fun provideSourceService(retrofit: Retrofit) = retrofit.create(SourceService::class.java)

    @Provides
    @Singleton
    fun provideEverythingService(retrofit: Retrofit) = retrofit.create(EverythingService::class.java)
}