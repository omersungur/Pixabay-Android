package com.omersungur.artbooktesting_android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.omersungur.artbooktesting_android.getOrAwaitValueTest
import com.omersungur.artbooktesting_android.presentation.base_view_model.ArtViewModel
import com.omersungur.artbooktesting_android.repo.FakeArtRepository
import com.omersungur.artbooktesting_android.util.Status
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule() // We want to run all code in main thread

    private lateinit var artViewModel: ArtViewModel

    @Before
    fun setup() {

        artViewModel = ArtViewModel(FakeArtRepository())
    }


    @Test
    fun `insert art without year returns error`() {
        artViewModel.makeArt("Mona Lisa","Da Vinci","")

        val value = artViewModel.insertArtMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert art without name returns error`() {
        artViewModel.makeArt("","Da Vinci","1500")

        val value = artViewModel.insertArtMessage.getOrAwaitValueTest() // We don't want Live Data.

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artistName returns error`() {
        artViewModel.makeArt("Mona Lisa","","1500")

        val value = artViewModel.insertArtMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}