package com.omersungur.artbooktesting_android.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.omersungur.artbooktesting_android.presentation.art_detail_fragment.ArtDetailFragment
import com.omersungur.artbooktesting_android.presentation.art_fragment.ArtFragment
import com.omersungur.artbooktesting_android.presentation.art_fragment.ArtRecyclerAdapter
import com.omersungur.artbooktesting_android.presentation.image_api_fragment.ImageApiFragment
import com.omersungur.artbooktesting_android.presentation.image_api_fragment.ImageApiRecyclerAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val imageRecyclerAdapter: ImageApiRecyclerAdapter,
    private val glide : RequestManager,
    private val artRecyclerAdapter: ArtRecyclerAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            ArtDetailFragment::class.java.name -> ArtDetailFragment(glide)
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}