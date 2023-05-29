package com.omersungur.artbooktesting_android.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtEntity(
    val artName: String,
    val artistName: String,
    val artYear: Int,
    val imageUrl: String,
    @PrimaryKey val id: Int? = null
)