package solutus.coronawatch.utilities

import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.reposetory.VideosRepository
import solutus.coronawatch.ui.home.videos.VideosViewModelFactory

object InjectorUtils {

    fun provideVideosViewModelFactory() : VideosViewModelFactory {
        val videosRepository = VideosRepository(CoronaWatchApi())
        return VideosViewModelFactory(videosRepository)
    }
}