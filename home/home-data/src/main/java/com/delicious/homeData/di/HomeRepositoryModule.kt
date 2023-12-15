package com.delicious.homeData.di

import com.delicious.homeData.apiService.HomeApiService
import com.delicious.homeData.repository.homeRepository.DefaultHomeRepository
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Provides
    fun provideHomeRepository(homeApiService: HomeApiService) =
        DefaultHomeRepository(homeApiService)
}