package solutus.coronawatch.utilities

import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.implementation.ArticleRepository
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.ui.mainActivity.home.novelties.NoveltiesViewModelFactory
import solutus.coronawatch.ui.mainActivity.home.videos.VideoViewModelFactory

object InjectorUtils {

    fun provideVideosViewModelFactory() : VideoViewModelFactory {
        val contentRepository =
            ContentRepository(
                ContentApi()
            )
        return VideoViewModelFactory(
            contentRepository
        )
    }

    fun provideNoveltiesViewModelFactory(): NoveltiesViewModelFactory {
        val articleRepository =
            ArticleRepository(
                ContentApi()
            )
        return NoveltiesViewModelFactory(
            articleRepository
        )
    }
}