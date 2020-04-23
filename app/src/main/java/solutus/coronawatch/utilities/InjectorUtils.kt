package solutus.coronawatch.utilities

import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.reposetory.VideosRepository
import solutus.coronawatch.factory.VideoViewModelFactory

object InjectorUtils {

    fun provideVideosViewModelFactory() : VideoViewModelFactory {
        val videosRepository = VideosRepository(CoronaWatchApi())
        return VideoViewModelFactory(
            videosRepository
        )
    }
}