package com.omersungur.artbooktesting_android.data.remote

import com.omersungur.artbooktesting_android.data.remote.dto.ArtImagesDTO
import com.omersungur.artbooktesting_android.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun getImages(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = API_KEY
    ): Response<ArtImagesDTO>
}