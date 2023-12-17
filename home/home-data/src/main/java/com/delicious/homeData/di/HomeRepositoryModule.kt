package com.delicious.homeData.di

import com.delicious.homeData.apiService.HomeApiService
import com.delicious.homeData.repository.homeRepository.DefaultHomeRepository
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object HomeRepositoryModule {
//
//    @Provides
//    fun bindHomeRepository(apiService: HomeApiService): HomeRepository =
//        DefaultHomeRepository(apiService)
//}