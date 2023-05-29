package com.omersungur.artbooktesting_android.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.omersungur.artbooktesting_android.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database : ArtDatabase

    private lateinit var dao : ArtDao

    @Before
    fun setup() {
        /*
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),ArtDatabase::class.java)
            .allowMainThreadQueries() //this is a test case, we don't want other thread pools
            .build()

         */
        hiltRule.inject()
        dao = database.dao
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertArtTesting() = runTest {
        val exampleArt = ArtEntity("Mona Lisa","Da Vinci",1700,"test.com", id = 1)
        dao.insertArt(exampleArt)

        val list = dao.getArts().getOrAwaitValue()
        assertThat(list).contains(exampleArt)

    }

    @Test
    fun deleteArtTesting() = runTest {
        val exampleArt = ArtEntity("Mona Lisa","Da Vinci",1700,"test.com",id = 1)
        dao.insertArt(exampleArt)
        dao.deleteArt(exampleArt)

        val list = dao.getArts().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)

    }

}