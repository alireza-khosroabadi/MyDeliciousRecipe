package com.delicious.favoriteDI

import com.delicious.datastore.dataSource.FavoriteRecipeDataSource
import com.delicious.favoriteData.repository.DefaultFavoriteRecipeRepository
import com.delicious.favoriteDomain.repository.FavoriteRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoriteRecipeRepositoryModule {
    @Provides
    fun provideFavoriteRecipeRepository(dataSource: FavoriteRecipeDataSource): FavoriteRecipeRepository =
        DefaultFavoriteRecipeRepository(dataSource)
}
