package com.omersungur.artbooktesting_android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ArtEntity::class],
    version = 1
)
abstract class ArtDatabase: RoomDatabase() {

    abstract val dao: ArtDao
}