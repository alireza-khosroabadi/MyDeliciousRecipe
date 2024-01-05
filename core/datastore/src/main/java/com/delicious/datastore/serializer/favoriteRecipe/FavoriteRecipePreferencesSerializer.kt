package com.delicious.datastore.serializer.favoriteRecipe

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.delicious.datastore.model.favoriteRecipe.FavoriteRecipeListPreferences
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object FavoriteRecipeListPreferencesSerializer : Serializer<FavoriteRecipeListPreferences> {
    override val defaultValue: FavoriteRecipeListPreferences
        get() = FavoriteRecipeListPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FavoriteRecipeListPreferences {
        try {
            return FavoriteRecipeListPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: FavoriteRecipeListPreferences,
        output: OutputStream,
    ) {
        t.writeTo(output)
    }
}
