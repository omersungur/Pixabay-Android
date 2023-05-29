package com.omersungur.artbooktesting_android.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omersungur.artbooktesting_android.domain.model.Art
import com.omersungur.artbooktesting_android.domain.model.ArtImage
import com.omersungur.artbooktesting_android.domain.repository.ArtRepository
import com.omersungur.artbooktesting_android.util.Resource

class FakeArtRepository : ArtRepository {

    private val arts = mutableListOf<Art>()
    private val artsLiveData = MutableLiveData<List<Art>>(arts)

    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshLiveData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshLiveData()
    }

    override fun getArts(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ArtImage> {
        return Resource.success(ArtImage(listOf()))
    }

    private fun refreshLiveData() {
        artsLiveData.postValue(arts)
    }


}