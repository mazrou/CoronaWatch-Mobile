package solutus.coronawatch.utilities

import solutus.coronawatch.data.network.CoronaWatchApi
import solutus.coronawatch.data.reposetory.VideosRepository
import solutus.coronawatch.ui.user.fragment.content.view.ViewContentViewModel
import solutus.coronawatch.ui.user.fragment.content.view.ViewContentViewModelFactory


object InjectorUtils {

    fun provideVideosViewModelFactory() : ViewContentViewModelFactory {
        val videosRepository = VideosRepository(CoronaWatchApi())
        return ViewContentViewModelFactory(videosRepository)
    }
}