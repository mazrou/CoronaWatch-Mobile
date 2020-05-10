package solutus.coronawatch.utilities

import solutus.coronawatch.data.network.ContentApi
import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.reposetory.ContentRepository
import solutus.coronawatch.data.reposetory.VideosRepository
import solutus.coronawatch.factory.VideoViewModelFactory

object InjectorUtils {

    fun provideVideosViewModelFactory() : VideoViewModelFactory {
        val contentRepository = ContentRepository(ContentApi.invoke())
        return VideoViewModelFactory(
                contentRepository
        )
    }
}