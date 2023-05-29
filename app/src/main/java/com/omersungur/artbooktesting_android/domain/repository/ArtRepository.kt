package com.omersungur.artbooktesting_android.domain.repository

import androidx.lifecycle.LiveData
import com.omersungur.artbooktesting_android.domain.model.Art
import com.omersungur.artbooktesting_android.domain.model.ArtImage
import com.omersungur.artbooktesting_android.util.Resource

interface ArtRepository {

    suspend fun insertArt(art: Art)
    suspend fun deleteArt(art: Art)
    fun getArts(): LiveData<List<Art>>
    suspend fun searchImage(imageString: String): Resource<ArtImage>
}