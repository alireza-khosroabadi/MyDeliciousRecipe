package com.delicious.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipeListPreferences
import com.delicious.datastore.serializer.favoriteRecipe.FavoriteRecipeListPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun provideFavoriteRecipeListPreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<FavoriteRecipeListPreferences> =
        DataStoreFactory.create(
            serializer = FavoriteRecipeListPreferencesSerializer,
            produceFile = { context.dataStoreFile("favorite_recipe_prefs.pb") },
            corruptionHandler =
                ReplaceFileCorruptionHandler(
                    produceNewData = { FavoriteRecipeListPreferences.getDefaultInstance() },
                ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        )
}
