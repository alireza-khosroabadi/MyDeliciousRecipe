package com.delicious.database.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = true,
)
abstract class RecipesDatabase : RoomDatabase() {

}
