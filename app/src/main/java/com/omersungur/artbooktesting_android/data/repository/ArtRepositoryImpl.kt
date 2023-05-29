package com.omersungur.artbooktesting_android.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.omersungur.artbooktesting_android.data.local.ArtDatabase
import com.omersungur.artbooktesting_android.data.mapper.toArtEntity
import com.omersungur.artbooktesting_android.data.mapper.toArtImage
import com.omersungur.artbooktesting_android.data.mapper.toArtList
import com.omersungur.artbooktesting_android.data.remote.PixabayAPI
import com.omersungur.artbooktesting_android.domain.model.Art
import com.omersungur.artbooktesting_android.domain.model.ArtImage
import com.omersungur.artbooktesting_android.domain.repository.ArtRepository
import com.omersungur.artbooktesting_android.util.Resource
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    artDatabase: ArtDatabase,
    private val pixabayAPI: PixabayAPI,
) : ArtRepository {

    private val dao = artDatabase.dao

    override suspend fun insertArt(art: Art) {
        dao.insertArt(art.toArtEntity())
    }

    override suspend fun deleteArt(art: Art) {
        dao.deleteArt(art.toArtEntity())
    }

    override fun getArts(): LiveData<List<Art>> {
        val results = dao.getArts().map { arts ->
            arts.toArtList()
        }
        return results
    }

    override suspend fun searchImage(imageString: String): Resource<ArtImage> {
        return try {
            val result = pixabayAPI.getImages(imageString)
            if(result.isSuccessful) {
                result.body()?.let {
                    return@let Resource.success(it.toArtImage())
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error("Error",null)
        }
    }
}