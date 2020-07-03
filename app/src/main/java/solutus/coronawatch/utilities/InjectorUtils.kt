package solutus.coronawatch.utilities

import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.implementation.ArticlesRepository
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.data.reposetory.implementation.VideosYoutubeRepository
import solutus.coronawatch.ui.mainActivity.home.news.listVideos.ListVideosYoutubeViewModelFactory
import solutus.coronawatch.ui.mainActivity.home.novelties.listArticles.NoveltiesViewModelFactory
import solutus.coronawatch.ui.mainActivity.home.videos.listVideos.VideoViewModelFactory

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
            ArticlesRepository(
                ContentApi()
            )
        return NoveltiesViewModelFactory(
            articleRepository
        )
    }

    fun provideListVideoYoutubeViewModelFactory(): ListVideosYoutubeViewModelFactory {
        val videosYoutubeRepository =
            VideosYoutubeRepository(
                ContentApi()
            )
        return ListVideosYoutubeViewModelFactory(
            videosYoutubeRepository
        )
    }
}