package solutus.coronawatch.ui.home.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import solutus.coronawatch.data.reposetory.VideosRepository

/**
 *  This class is for passing  the VideosRepository to the VideoFragment
 *  when we create the instance of the VideoViewModel
 */

class VideosViewModelFactory(
    private val videosRepository: VideosRepository
   ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideosViewModel(videosRepository) as T
    }

}