package com.omersungur.artbooktesting_android.di

import com.omersungur.artbooktesting_android.data.repository.ArtRepositoryImpl
import com.omersungur.artbooktesting_android.domain.repository.ArtRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindArtRepository(
        artRepositoryImpl: ArtRepositoryImpl
    ): ArtRepository
}