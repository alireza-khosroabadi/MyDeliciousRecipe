package com.delicious.database.di

import android.content.Context
import androidx.room.Room
import com.delicious.database.database.RecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): RecipesDatabase = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        "recipes-database",
    ).build()
}
