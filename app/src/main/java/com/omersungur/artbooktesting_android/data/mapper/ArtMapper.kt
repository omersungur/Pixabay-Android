package com.omersungur.artbooktesting_android.data.mapper

import com.omersungur.artbooktesting_android.data.local.ArtEntity
import com.omersungur.artbooktesting_android.data.remote.dto.ArtImagesDTO
import com.omersungur.artbooktesting_android.domain.model.Art
import com.omersungur.artbooktesting_android.domain.model.ArtImage

fun ArtEntity.toArt(): Art {
    return Art(
        artName = artName,
        artistName = artistName,
        artYear = artYear,
        imageUrl = imageUrl
    )
}

fun Art.toArtEntity(): ArtEntity {
    return ArtEntity(
        artName = artName,
        artistName = artistName,
        artYear = artYear,
        imageUrl = imageUrl
    )
}

fun ArtImagesDTO.toArtImage(): ArtImage {
    return ArtImage(
        artImageUrl = hits.map { it.webformatURL }
    )
}

fun List<ArtEntity>.toArtList(): List<Art> {
    return map { it.toArt() }
}
