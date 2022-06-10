package com.indocyber.newsapplicationapi.module

import com.indocyber.api_service.service.EverythingService
import com.indocyber.api_service.service.SourceService
import com.indocyber.api_service.usecase.CategoryUseCase
import com.indocyber.api_service.usecase.EverythingUseCase
import com.indocyber.api_service.usecase.SourceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideCategoryUseCase() = CategoryUseCase()

    @Provides
    fun provideSourceUseCase(sourceService: SourceService) = SourceUseCase(sourceService)

    @Provides
    fun provideEverythingUseCase(everythingService: EverythingService) = EverythingUseCase(everythingService)
}