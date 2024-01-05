package com.delicious.homeDI

import com.delicious.datastore.dataSource.FavoriteRecipeDataSource
import com.delicious.homeData.apiService.HomeApiService
import com.delicious.homeData.repository.homeRepository.DefaultHomeRepository
import com.delicious.homeDomain.repository.homeRepository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {
    @Provides
    fun bindHomeRepository(
        apiService: HomeApiService,
        favoriteRecipeDataSource: FavoriteRecipeDataSource,
    ): HomeRepository = DefaultHomeRepository(apiService, favoriteRecipeDataSource)
}
