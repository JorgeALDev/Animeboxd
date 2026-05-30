package com.jorge.anicatalog.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AnimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AniCatalogDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    companion object {
        @Volatile
        private var INSTANCE: AniCatalogDatabase? = null

        fun getInstance(context: Context): AniCatalogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AniCatalogDatabase::class.java,
                    "anicatalog.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}